/**
 * rock_scissor_paper
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Rock_scissor_paper {

    public static void main(String[] args) {
        

        try {
            int numLines = 0;
            File myFile = new File("game.csv");
            Scanner myReader = new Scanner(myFile);
            /*ArrayList<String> cars = new ArrayList<String>();
            ArrayList<String> games = new ArrayList<String>();
            ArrayList<String> wins = new ArrayList<String>();*/

            List<Player> players = new ArrayList<Player>();

            while (myReader.hasNextLine()) {
                numLines++;
                
                if(numLines > 1000){
                    myReader.close();
                    throw new ArithmeticException("1000 line limit exceeded");
                }
                if(numLines == 1){
                    myReader.nextLine();    
                }
                String data = myReader.next();
                int countSemicolon = data.length() - data.replace(";", "").length();
                if(countSemicolon != 3){
                    myReader.close();
                    throw new ArithmeticException("wrong data");
                }else{
                    String[] arrayOfRow = data.split("[;]", 0);
                    int numL = 0;
                    
                    for(String delRow: arrayOfRow) {
                        if(numL < 2){                   
                            Player player = createPlayer(delRow);
                            int player2 = userExist(delRow,players);
                            
                            if (player2 == -1 ) {
                                players.add(player);
                                
                            }else{
                                Player player_update_index = players.get(player2);
                                Player player_update = updateGames(player_update_index);
                                players.remove(player_update_index);
                                players.add(player_update);

                            }
                        }
                        
                        if(numL == 2){
                            int playerWins = userExist(delRow,players);
                            Player player_update_index = players.get(playerWins);
                            Player player_update = updateWin(player_update_index);
                            players.remove(player_update_index);
                            players.add(player_update);
                            
                        }
                        numL++; 
                    }
                }
            }
            /*
            List<Player> playersSortedByName = players;

            playersSortedByName.sort((Player o1, Player o2)->o1.getName().compareTo(o2.getName()));
            
            System.out.println("playersSortedByName:");
            for (Player b : playersSortedByName) {
                System.out.println(b);
            }
            
            List<Player> playersSuccessRatio = players;
            playersSuccessRatio.removeIf( wins -> wins.getWins() == 0);

            playersSuccessRatio.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return o2.getWins() / o2.getGames() - o1.getWins() / o1.getGames();
                }
            });

            System.out.println("playersSuccessRatio:");
            for (Player b : playersSuccessRatio) {
                System.out.println(b);
            }
            */

            /*for (Player b : players) {
                System.out.println(b);
            }*/

            createHtml(players);
            myReader.close();
            
       
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
           
        } catch (Exception e1) {
            System.out.println("An error occurred.");
            e1.printStackTrace();
    
        }

    }

    public static Player updateGames(Player hrac){
        int gam = hrac.getGames();
        hrac.setGames(gam+1);
        String name = hrac.getName();
        int games = hrac.getGames();
        int wins = hrac.getWins();
        return new Player(name, games, wins); 
    }

    public static Player updateWin(Player hrac){
        int win = hrac.getWins();
        hrac.setWins(win+1);
        String name = hrac.getName();
        int games = hrac.getGames();
        int wins = hrac.getWins();
        return new Player(name, games, wins); 
    }

    public static int userExist(String name, List<Player> customers) {

        for (Player customer : customers) {
            if (customer.getName().equals(name)) {
                return customers.indexOf(customer);
            }
        }
        return -1;
    }

    private static Player createPlayer(String data) {
        String name = data;
        int games = 1;
        int wins = 0;
        return new Player(name, games, wins); 
    }

    public static void createHtml(List<Player> playersUnSort){
        File f = new File("result.html");
        try {
            List<Player> playersSortedByName = new ArrayList<Player>();
            List<Player> playersSuccessRatio = new ArrayList<Player>();

            playersSortedByName.addAll(playersUnSort);
            playersSuccessRatio.addAll(playersUnSort);

            playersSortedByName.sort((Player o1, Player o2)->o1.getName().compareTo(o2.getName()));
            
            //playersSuccessRatio.removeIf( wins -> wins.getWins() == 0);
            /*playersSuccessRatio.sort(new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    if(o1.getWins() == 0){
                        return 0;
                    }else if(o2.getWins() == 0){
                        return 0;
                    }else{
                        return o2.getWins() / o2.getGames() - o1.getWins() / o1.getGames();
                    }
                }
            });*/
            playersSuccessRatio.sort((Player s1, Player s2)->(int)(((float)s2.getWins() / (float)s2.getGames())*100)-(int)(((float)s1.getWins() / (float)s1.getGames())*100));

            StringBuilder stringBuilderRatio = new StringBuilder();
            StringBuilder stringBuilderPlayer = new StringBuilder();


            for (Player b : playersSuccessRatio) {
                int ratio = (int)(((float)b.getWins() / (float)b.getGames())*100);
                String row = 
                            "<div class=\"divRow\">"+
                                "<div class=\"divCell\">"+b.getName()+"</div>"+
                                "<div class=\"divCell\">"+ratio+"</div>"+
                            "</div>";   
                            stringBuilderRatio.append(row);
            }

            String tableOfSuccessRatio =  
            "<div class=\"divTable divTableRatio\">"+
                "<div class=\"headRow\">"+
                    "<div class=\"divCell\">Player name</div>"+
                    "<div class=\"divCell\">Success ratio</div>"+
                "</div>"+
                stringBuilderRatio.toString()+
            "</div>";



            for (Player p : playersSortedByName) {
                int ratio = (int)(((float)p.getWins() / (float)p.getGames())*100);
                String row = 
                            "<div class=\"divRow\">"+
                                "<div class=\"divCell\">"+p.getName()+"</div>"+
                                "<div class=\"divCell\">"+p.getWins()+"</div>"+
                                "<div class=\"divCell\">"+p.getGames()+"</div>"+
                                "<div class=\"divCell\">"+ratio+"</div>"+
                            "</div>";   
                            stringBuilderPlayer.append(row);
            }

            String tableOfPlayer =  
            "<div class=\"divTable divTablePlayer\">"+
                "<div class=\"headRow\">"+
                    "<div class=\"divCell\">Player name</div>"+
                    "<div class=\"divCell\">Wins</div>"+
                    "<div class=\"divCell\">Games played</div>"+
                    "<div class=\"divCell\">Success ratio</div>"+
                "</div>"+
                stringBuilderPlayer.toString()+
            "</div>";

            
            

            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            String htmlHead = "<title>Result rock scissor paper</title><link rel='stylesheet' href='style.css'><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
            String htmlBody = "<h1>Results game</h1><h2>Leaderboard:</h2>"+tableOfSuccessRatio+"<h2>Player list:</h2>"+tableOfPlayer;
            String htmlPage = "<!DOCTYPE html><html><head>" + htmlHead + "</head><body>" + htmlBody + "</body></html>";
            bw.write(htmlPage);
            bw.close(); 
        } catch (IOException e) {
            System.out.println("Error create html");
            e.printStackTrace();
        }

    }
        
}