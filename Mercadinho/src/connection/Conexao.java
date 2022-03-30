package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
	private static Connection connection;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/dbmercadinho";
	private static final String USUARIO = "root";
	private static final String SENHA = "Douglas123@";

	private Conexao() {
	}

	public static synchronized Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USUARIO, SENHA);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Erro ao carregar o driver de conexao\n" + e);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "no foi possivel estabalecer conexao com o banco de dados\n" + e);
			}
		}
		JOptionPane.showMessageDialog(null, "conexao realizada com sucesso!");
		return connection;
	}

	public static void closeConnection(Connection con){
        
            try {
                if(con != null){
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
       public static void closeConnection(Connection con, PreparedStatement stmt){
        
           closeConnection(con);
           
          
            try {
              
                if(stmt != null){
                    stmt.close();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
}