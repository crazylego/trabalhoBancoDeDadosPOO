package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private static Connection conexoes;
	
	private Conexao() {
		
	}
	
	public static Connection getConexao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexoes = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/associacao?useSSL=false", "root", "123456");
			}catch(Exception e) {
				System.out.println(e);
			}
		return conexoes;
	}
}
