package associados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.Conexao;
import exceptions.AssociacaoNaoExistente;
import exceptions.AssociadoJaExistente;
import exceptions.ValorInvalido;

public class AssociadoDAO {

	// Adicionar um associado
	public void adicionarAssociado(int numAssociacao, Associado a)
			throws SQLException, AssociacaoNaoExistente, ValorInvalido, AssociadoJaExistente {
		Connection conexao = Conexao.getConexao();
		Statement st = conexao.createStatement();

		// Verifica os valores dos atributos
		if (a instanceof AssociadoRemido) {
			verificaValidadeRemido((AssociadoRemido) a);
		} else {
			verificaValidadeNormal(a);
		}

		//Check table associado
		String comando = "select * from associacao where numero = " + numAssociacao;
		ResultSet rs = st.executeQuery(comando);

		if (!rs.next()) {
			throw new AssociacaoNaoExistente();
		} else {
			if (procuraAssociado(numAssociacao, a) == null) {
				
				if (a instanceof AssociadoRemido) {
					AssociadoRemido as = (AssociadoRemido) a;
					comando = "insert into associado (numero,nome,telefone,nascimento,data,remissao,associacao) value ("
							+ as.getNumero() + ",'" + as.getNome() + "','" + as.getEndereco() + "',"
							+ as.getNascimento() + "," + as.getDataEntrada() + "," + as.getDataRemissao() + ","
							+ numAssociacao + ")";
					st.executeUpdate(comando);
				}else{
					comando = "insert into associado (numero,nome,telefone,nascimento,data,associacao) value ("
							+ a.getNumero() + ",'" + a.getNome() + "','" + a.getEndereco() + "',"
							+ a.getNascimento() + "," + a.getDataEntrada() + ","
							+ numAssociacao + ")";
					st.executeUpdate(comando);
				}

/*				comando = "insert into assoctoassoc (numassoc, numassociado) value (" + numAssociacao + ","
						+ a.getNumero() + ")";
				st.executeUpdate(comando);*/
				
			} else {
				throw new AssociadoJaExistente();
			}
		}
		conexao.close();
		st.close();
	}

	public AssociadoDAO() {

	}

	public void verificaValidadeNormal(Associado a) throws ValorInvalido {
		if (a.getNome() == null || a.getNome() == "") {
			throw new ValorInvalido("Nome");
		} else if (a.getNumero() <= 0) {
			throw new ValorInvalido("N�mero");
		} else if (a.getDataEntrada() <= 0) {
			throw new ValorInvalido("Data de Entrada");
		} else if (a.getEndereco() == null || a.getEndereco() == "") {
			throw new ValorInvalido("Telefone");
		} else if (a.getNascimento() <= 0) {
			throw new ValorInvalido("Data de Nascimento");
		}
	}

	public void verificaValidadeRemido(AssociadoRemido a) throws ValorInvalido {
		if (a.getNome() == null || a.getNome() == "") {
			throw new ValorInvalido("Nome");
		} else if (a.getNumero() <= 0) {
			throw new ValorInvalido("N�mero");
		} else if (a.getDataEntrada() <= 0) {
			throw new ValorInvalido("Data de Entrada");
		} else if (a.getEndereco() == null || a.getEndereco() == "") {
			throw new ValorInvalido("Telefone");
		} else if (a.getNascimento() <= 0) {
			throw new ValorInvalido("Data de Nascimento");
		} else if (a.getDataRemissao() <= 0) {
			throw new ValorInvalido("Data de Remiss�o");
		}
	}

	public Associado procuraAssociado(int numAssociacao, Associado a) throws SQLException {

		Connection conexao = Conexao.getConexao();
		Statement st = conexao.createStatement();

		String comando = "select * from associado where associacao = " + numAssociacao;
		ResultSet rs = st.executeQuery(comando);

		while (rs.next()) {
			if(rs.getInt("numero") == a.getNumero()) {
				return a;
			}
		}
		conexao.close();
		st.close();
		return null;
	}
}
