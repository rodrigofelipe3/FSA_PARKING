
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    
    private static final String USERNAME = "root";
    
    private static final String PASSWORD = "";
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/fsaparking";
    
    static Connection CreateConnectiontoMySQL() throws ClassNotFoundException, SQLException { 
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        
        return connection;
        
    }
     public static void main(String[] args) throws Exception {
        //Recuperar Conexão
        
        Connection con = CreateConnectiontoMySQL();
        
        if (con!= null) { 
            System.out.println("Conexão Obtida com Sucesso");
            con.close();
        }
    }
    
}
