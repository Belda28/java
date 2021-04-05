/*import java.sql.*;  


        
class MysqlCon{  
    public static void main(String args[]){  
        MysqlCon dataSource = new MysqlDataSource();
dataSource.setUser("scott");
dataSource.setPassword("tiger");
dataSource.setServerName("myDBHost.example.org");

Connection conn = dataSource.getConnection();
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT ID FROM USERS");

rs.close();
stmt.close();
conn.close();
        

    }  
}  */