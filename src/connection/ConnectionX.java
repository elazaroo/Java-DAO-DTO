package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionX {
	
	private static ConnectionX instance = null;
    private static Connection conn;
    
    private ConnectionX () {
        String host = "127.0.0.1"; // Or localhost
        String user = "root";
        String pass = "";
        String dtbs = "sakila";

        try{
            Class.forName("com.mysql.jdbc.Driver"); // Initialize the driver
            String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?" + "user=" + user + "&password=" + pass;
        
            conn = DriverManager.getConnection(newConnectionURL);
        }catch (Exception e) {
            System.out.println("Error when opening the connection: " + e.getMessage());
        }
    }
    
    public static ConnectionX getInstance(){
        if (instance == null) instance = new ConnectionX();
            return instance;
        }
    
    public Connection getConnection (){
        return conn;
    }
    
    public void closeConnection() {
        try {
            conn.close();
        }catch (Exception e) {
            System.out.println("Error closing the connection: " + e.getMessage());
        }
    }
}
