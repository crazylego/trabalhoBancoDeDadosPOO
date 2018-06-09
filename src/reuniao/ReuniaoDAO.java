package reuniao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.Conexao;
import exceptions.AssociacaoNaoExistente;
import exceptions.ReuniaoJaExistente;
import exceptions.ValorInvalido;

public class ReuniaoDAO {
	
	public void adicionarReuniao(int associacao, Reuniao a) throws SQLException, AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		Connection conexao = Conexao.getConexao();
		Statement st = conexao.createStatement();
		String comando = "select * from associacao where numero = " + associacao;
		ResultSet rs = st.executeQuery(comando);
		if(rs.next()) {
			comando = "select * from reuniao where data = " + a.getData(); 
			rs = st.executeQuery(comando);
			if(!rs.next()) {
				
				verificaValidade(a);
				
				comando = "insert into reuniao (data,ata) value (" + a.getData() +",'" + a.getAta()+ "')";
				st.executeUpdate(comando);
				
/*				comando = "insert into associacaotoreuniao (associacao,reuniao) values(" + associacao + "," + a.getData() +")";
				st.executeUpdate(comando);*/
				
			}else {
				throw new ReuniaoJaExistente();
			}
		}else {
			throw new AssociacaoNaoExistente();
		}
		
		conexao.close();
		st.close();
	}

	private void verificaValidade(Reuniao r) throws ValorInvalido {
		if(r.getData() <= 0) {
			throw new ValorInvalido("Data");
		}else if(r.getAta() == null || r.getAta() == "") {
			throw new ValorInvalido("Ata");
		}
		
	}
	
	public ReuniaoDAO(){
		
	}
}
