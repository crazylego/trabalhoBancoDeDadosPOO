package associacao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import associados.Associado;
import associados.AssociadoDAO;
import conexao.Conexao;
import exceptions.*;
import reuniao.Reuniao;
import reuniao.ReuniaoDAO;
import taxas.Taxa;
import taxas.TaxaDAO;

public class AssociacaoDAO {
	
	AssociadoDAO associados = new AssociadoDAO();
	ReuniaoDAO reunioes = new ReuniaoDAO();
	TaxaDAO taxas = new TaxaDAO();

	public void adicionarAssociacao(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		try {
			Connection conexao = Conexao.getConexao();
			Statement st = conexao.createStatement();
			String comando = "select * from associacao where numero = " + a.getNumero();
			ResultSet rs = st.executeQuery(comando);
			if (!rs.next()) {
				
				verificaValidade(a);
				
				comando = "insert into associacao (numero,nome) value (" + a.getNumero() + ",'" + a.getNome() + "')";
				st.executeUpdate(comando);
				
				conexao.close();
				st.close();
			} else {
				throw new AssociacaoJaExistente();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void verificaValidade(Associacao a) throws ValorInvalido {
		if (a.getNome() == null || a.getNome() == "") {
			throw new ValorInvalido("Nome");
		} else if (a.getNumero() <= 0) {
			throw new ValorInvalido("N�mero");
		}
	}
	
	public void adicionarAssociado(int numAssociacao, Associado a) throws SQLException, AssociacaoNaoExistente, ValorInvalido, AssociadoJaExistente {
		associados.adicionarAssociado(numAssociacao, a);
	}
	
	public void adicionarReuniao(int associacao, Reuniao a) throws SQLException, AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		reunioes.adicionarReuniao(associacao, a);
	}
	
	public void adicionarTaxa(int associacao, Taxa t) throws SQLException, AssociacaoNaoExistente, ValorInvalido, TaxaJaExistente {
		taxas.adicionarTaxa(associacao, t);
	}
	
	public double calculaTotalTaxas(int associacao, int vigencia) {
		return taxas.calculaTaxas(associacao, vigencia);
	}

	public void limparBanco() throws SQLException {
		Connection conexao = Conexao.getConexao();
		Statement st = conexao.createStatement();

		String comando = "delete from associacao";
		st.executeUpdate(comando);

		comando = "delete from associado";
		st.executeUpdate(comando);
		
		comando = "delete from reuniao";
		st.executeUpdate(comando);	
		
		comando = "delete from taxa";
		st.executeUpdate(comando);	
		
		conexao.close();
		st.close();
	}

}
