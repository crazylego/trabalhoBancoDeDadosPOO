package taxas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.Conexao;
import exceptions.AssociacaoNaoExistente;
import exceptions.TaxaJaExistente;
import exceptions.ValorInvalido;

public class TaxaDAO {
	
	public void adicionarTaxa(int associacao, Taxa t) throws SQLException, AssociacaoNaoExistente, ValorInvalido, TaxaJaExistente {
		Connection conexao = Conexao.getConexao();
		Statement st = conexao.createStatement();
		String comando = "select * from associacao where numero = " + associacao;
		ResultSet rs = st.executeQuery(comando);
		if(rs.next()) {
			comando = "select * from taxa where nome = '" + t.getNome() + "' and vigencia = " + t.getVigencia();
			rs = st.executeQuery(comando);
			
			if(!rs.next()) {
				
				verificaValidade(t);
				 
				comando = "insert into taxa (nome, vigencia, valor, administrativa, associacao, parcelas) values "
						+ "('" + t.getNome() + "'," + t.getVigencia() + "," + t.getValor() + "," + t.getTipo() + "," + associacao +"," + t.getParcelas() +")";
				st.executeUpdate(comando);
				
			}else {
				throw new TaxaJaExistente();
			}
			
		}else {
			throw new AssociacaoNaoExistente();
		}
		
		conexao.close();
		st.close();
	}

	private void verificaValidade(Taxa tax) throws ValorInvalido {
		if (tax.getNome() == "" || tax.getNome() == null) {
			throw new ValorInvalido("Nome");
		} else if (tax.getVigencia() <= 0) {
			throw new ValorInvalido("Vig�ncia");
		} else if (tax.getValor() <= 0) {
			throw new ValorInvalido("Valor Anual");
		} else if (tax.getParcelas() <= 0) {
			throw new ValorInvalido("Parcelas");
		} else if (tax.getTipo() != true && tax.getTipo() != false) {
			throw new ValorInvalido("Tipo de taxa");
		}
		
	}
	
	public double calculaTaxas(int associacao, int vigencia) {
		return 0;
	}
	
}
