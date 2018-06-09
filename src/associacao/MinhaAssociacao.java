package associacao;

import java.sql.SQLException;

import associados.Associado;
import exceptions.*;
import reuniao.Reuniao;
import taxas.Taxa;

public class MinhaAssociacao implements InterfaceAssociacao {
	RepositorioDeAssociacao repoAssoc;// N�o vai ter mais esse repoassocaicao
	AssociacaoDAO controle = new AssociacaoDAO();

	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		controle.adicionarAssociacao(a);
	}

	public int quantAssoc() {
		return repoAssoc.tamanho();
	}

	public void adicionar(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		try {
			controle.adicionarAssociado(associacao, a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		// repoAssoc.addReuniao(associacao, r);

		try {
			controle.adicionarReuniao(associacao, r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		try {
			controle.adicionarTaxa(associacao, t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente, TaxaNaoExistente {
		return controle.calculaTotalTaxas(numAssociacao, vigencia);

	}

	MinhaAssociacao() {
		repoAssoc = new RepositorioDeAssociacao();
	}

	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada,
			FrequenciaIncompativel {
		try {
			repoAssoc.procurarAssociacao(numAssociacao);
		} catch (AssociacaoJaExistente e) {
			try {
				repoAssoc.pesquisaAssociado(numAssociacao, codigoAssociado);
			} catch (AssociadoJaExistente y) {
				try {
					repoAssoc.pesquisarReuniao(numAssociacao, dataReuniao);
				} catch (ReuniaoJaExistente r) {
					repoAssoc.pesquisarFrequencia(numAssociacao, dataReuniao, codigoAssociado);
					repoAssoc.addFrequencia(codigoAssociado, dataReuniao, numAssociacao);
				}
			}

		}

	}

	public double calcularFrequencia(int codigoAssociado, int numAssociacao, long inicio, long fim)
			throws AssociadoNaoExistente, AssociacaoNaoExistente, ReuniaoNaoExistente {
		double aux = 0;
		try {
			repoAssoc.procurarAssociacao(numAssociacao);
		} catch (AssociacaoJaExistente e) {
			try {
				repoAssoc.pesquisaAssociado(numAssociacao, codigoAssociado);
			} catch (AssociadoJaExistente y) {
				aux = (repoAssoc.presenca(numAssociacao, codigoAssociado, inicio, fim) * 1.0)
						/ repoAssoc.reunioesTotais(numAssociacao);
			}

		}
		return aux;
	}

	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {

		try {
			repoAssoc.procurarAssociacao(numAssociacao);
		} catch (AssociacaoJaExistente e) {
			try {
				repoAssoc.pesquisaAssociado(numAssociacao, numAssociado);
			} catch (AssociadoJaExistente y) {
				repoAssoc.registrarPagamento(numAssociacao, vigencia, taxa, valor, data, numAssociado);
			}
		}

	}

	@Override
	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		double aux = 0;
		try {
			repoAssoc.procurarAssociacao(numAssociacao);
		} catch (AssociacaoJaExistente e) {
			try {
				repoAssoc.pesquisaAssociado(numAssociacao, numAssociado);
			} catch (AssociadoJaExistente y) {
				aux = repoAssoc.totalTaxasAssociado(numAssociacao, nomeTaxa, numAssociado, inicio, fim, vigencia);
			}
		}

		return aux;
	}

	@Override
	public void limparBanco() {
		try {
			controle.limparBanco();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
