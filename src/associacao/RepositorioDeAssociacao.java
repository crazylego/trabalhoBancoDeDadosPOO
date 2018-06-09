package associacao;

import java.util.ArrayList;
import associados.*;
import exceptions.AssociacaoJaExistente;
import exceptions.AssociacaoNaoExistente;
import exceptions.AssociadoJaExistente;
import exceptions.AssociadoJaRemido;
import exceptions.AssociadoNaoExistente;
import exceptions.FrequenciaIncompativel;
import exceptions.FrequenciaJaRegistrada;
import exceptions.ReuniaoJaExistente;
import exceptions.ReuniaoNaoExistente;
import exceptions.TaxaJaExistente;
import exceptions.TaxaNaoExistente;
import exceptions.ValorInvalido;
import reuniao.*;
import taxas.Taxa;

public class RepositorioDeAssociacao {
	ArrayList<Associacao> associa;

	// Procura uma associa��o
	public void procurarAssociacao(int num) throws AssociacaoJaExistente, AssociacaoNaoExistente {
		for (Associacao a : associa) {
			if (a.getNumero() == num) {
				throw new AssociacaoJaExistente();
			}
		}
		throw new AssociacaoNaoExistente();
	}

	// Adiciona uma Associa��o
	public void adicionarAssociacao(Associacao a) throws ValorInvalido {
		if (a.getNome() == null || a.getNome() == "") {
			throw new ValorInvalido("Nome");
		} else if (a.getNumero() == 0) {
			throw new ValorInvalido("N�mero");
		} else {
			associa.add(a); 
		}
	}

	// Retorna a quantidade de associa��es cadastradas ---------METODO
	// ADICIONAL----------------
	public int tamanho() {
		return associa.size();
	}

	public int getAssoc(int num) {
		int i;
		for (i = 0; i < associa.size(); i++) {
			if (associa.get(i).getNumero() == num) {
				return i;
			}
		}
		return -1;
	}

	// Adiciona um associado a uma associa��o
	public void addAssociado(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		try {
			procurarAssociacao(associacao);
		} catch (AssociacaoJaExistente e) {
			try {
				associa.get(getAssoc(associacao)).pesquisaAssociado(a.getNumero());
			} catch (AssociadoNaoExistente x) {
				associa.get(getAssoc(associacao)).adicionarAssociado(a);
			}
		}
	}

	public void addReuniao(int assoc, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		try {
			procurarAssociacao(assoc);
		} catch (AssociacaoJaExistente e) {
			try {
				associa.get(getAssoc(assoc)).pesquisarReuniao(r.getData());	
			} catch (ReuniaoNaoExistente x) {

				associa.get(getAssoc(assoc)).addReuniao(r);
			}
		}
	}
	
	public void addTaxa(int assoc, Taxa tax) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		try {
			procurarAssociacao(assoc);
		} catch(AssociacaoJaExistente e) {
			try {
				associa.get(getAssoc(assoc)).pesquisarTaxa(tax);
			}catch(TaxaNaoExistente y) {
				associa.get(getAssoc(assoc)).addTaxa(tax);
			}
		}
	}
	
	public void pesquisarTaxaVigencia(int assoc, int vigencia) throws TaxaJaExistente, TaxaNaoExistente {
		associa.get(getAssoc(assoc)).pesquisarVigencia(vigencia);
	}
	
	public double totalTaxas(int assoc ,int vigen) {
		return associa.get(getAssoc(assoc)).totalTaxas(vigen);
	}
	
	public void pesquisarReuniao(int numAssoc,long data) throws ReuniaoJaExistente, ReuniaoNaoExistente {
		associa.get(getAssoc(numAssoc)).pesquisarReuniao(data);
	}
	
	public void pesquisarFrequencia(int numAssociacao, long data, int associado) throws ReuniaoNaoExistente, FrequenciaJaRegistrada {
		associa.get(getAssoc(numAssociacao)).pesquisarFrequencia(associado, data);
	}
	
	public void addFrequencia(int numAssociado, long data, int numAssociacao) throws FrequenciaIncompativel, ReuniaoNaoExistente {
		Associado aux;
		try {
			aux = associa.get(getAssoc(numAssociacao)).getAssociado(numAssociado);
		//	System.out.println(aux.getNome());
			associa.get(getAssoc(numAssociacao)).addFrequencia(aux, data);
		}catch(AssociadoNaoExistente e) {
			
		}
	}
	
	public void pesquisaAssociado(int numAssociacao, int numAssociado) throws AssociadoNaoExistente, AssociadoJaExistente {
		associa.get(getAssoc(numAssociacao)).pesquisaAssociado(numAssociado);
	}
	
	public int reunioesTotais(int numAssociacao) throws ReuniaoNaoExistente {
		return associa.get(getAssoc(numAssociacao)).reunioesTotais();
	}
	
	public int presenca(int numAssociacao,int numAssociado, long dataInicio, long dataFim) {
		return associa.get(getAssoc(numAssociacao)).presenca(numAssociado, dataInicio, dataFim);
	}
	
	public void registrarPagamento(int associacao,int vigencia,String taxa, double valor, long data, int numAssociado) throws ValorInvalido, TaxaNaoExistente, AssociadoNaoExistente, AssociadoJaRemido {
		associa.get(getAssoc(associacao)).registrarPagamento(vigencia, taxa, valor, data, numAssociado);
	}
	
	public double totalTaxasAssociado(int numAssoc,String taxa,int numAssociado, long inicio, long fim, int vigencia) throws TaxaNaoExistente {
		return associa.get(getAssoc(numAssoc)).totalTaxas(taxa, numAssociado, inicio, fim, vigencia);
	}

	// M�todo Construtor
	public RepositorioDeAssociacao() {
		associa = new ArrayList<Associacao>();
	}

}
