public class Player {

    private String name; 
    private int games; 
    private int wins; 

    public Player(String name, int games, int wins) {
        this.name = name; 
        this.games = games; 
        this.wins = wins; 
    } 
    public String getName() {
        return name; 
    } 
    public void setName(String name) { 
        this.name = name;
    } 
    public int getGames() { 
        return games; 
    } 
    public void setGames(int games) { 
        this.games = games; 
    } 
    public int getWins() { 
        return wins; 
    } 
    public void setWins(int wins) { 
        this.wins = wins; 
    }

    /*
    @Override 
    public String toString() {
        return "Player [name=" + name + ", games=" + games + ", wins=" + wins + "]"; 
    }*/
    @Override
    public String toString() {
        return name+","+games+","+wins; 
    }


}