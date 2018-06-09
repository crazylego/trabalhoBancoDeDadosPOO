package taxas;

import java.util.ArrayList;

import associados.Associado;
import associados.AssociadoRemido;
import exceptions.AssociadoJaRemido;
import exceptions.TaxaJaExistente;
import exceptions.TaxaNaoExistente;
import exceptions.ValorInvalido;

public class RepositorioDeTaxas {
	ArrayList<Taxa> repoTaxas;

	public Taxa pesquisarTaxa(Taxa tax) throws TaxaNaoExistente, TaxaJaExistente {
		for (Taxa aux : repoTaxas) {
			if (aux.getVigencia() == tax.getVigencia() && aux.getNome().compareTo(tax.getNome()) == 0) {
				throw new TaxaJaExistente();
			}
		}
		throw new TaxaNaoExistente();
	}

	public void adicionarTaxa(Taxa tax) throws ValorInvalido {
		if (tax.getNome() == "" || tax.getNome() == null) {
			throw new ValorInvalido("Nome");
		} else if (tax.getVigencia() == 0) {
			throw new ValorInvalido("Vig�ncia");
		} else if (tax.getValor() == 0) {
			throw new ValorInvalido("Valor Anual");
		} else if (tax.getParcelas() == 0) {
			throw new ValorInvalido("Parcelas");
		} else if (tax.getTipo() != true && tax.getTipo() != false) {
			throw new ValorInvalido("Tipo de taxa");
		} else {
			repoTaxas.add(tax);
		}

	}

	public void pesquisarVigencia(int vigencia) throws TaxaJaExistente, TaxaNaoExistente {
		for (Taxa aux : repoTaxas) {
			if (aux.getVigencia() == vigencia) {
				throw new TaxaJaExistente();
			}
		}
		throw new TaxaNaoExistente();
	}

	//Soma todas a taxas e retorna
	public double somaTaxas(int vigen) {
		double soma = 0;
		for (Taxa aux : repoTaxas) {
			if (aux.getVigencia() == vigen) {
				soma = soma + aux.getValor();
			}
		}
		return soma;
	}

	public int getTaxa(String taxa, int vigencia) throws TaxaNaoExistente {
		int i;
		for(i = 0 ; i < repoTaxas.size() ; i ++) {
			if(repoTaxas.get(i).getNome().compareTo(taxa) == 0 && repoTaxas.get(i).getVigencia() == vigencia) {
				return i;
			}
		}
		throw new TaxaNaoExistente();
	}
	
	public void registrarPagamento(int vigencia,String taxa, double valor, long data, Associado associado) throws ValorInvalido, TaxaNaoExistente, AssociadoJaRemido {
		if(!repoTaxas.get(getTaxa(taxa,vigencia)).getTipo()) {
			repoTaxas.get(getTaxa(taxa,vigencia)).atualizaFolha(associado.getNumero(),valor,data, vigencia);
		}else {
			if(!(associado instanceof AssociadoRemido)) {
				repoTaxas.get(getTaxa(taxa,vigencia)).atualizaFolha(associado.getNumero(),valor,data, vigencia);
			}else {
				throw new AssociadoJaRemido();
			}
		}
		
		
	}
	
	public ArrayList<Taxa> getTaxas(){
		return repoTaxas;
	}
	
	public double totalPago(String taxa,int numAssociado, long inicio, long fim, int vigencia) throws TaxaNaoExistente {
		return repoTaxas.get(getTaxa(taxa,vigencia)).totalTaxas(numAssociado, inicio, fim, vigencia);
	}

	public RepositorioDeTaxas() {
		repoTaxas = new ArrayList<Taxa>();
	}
}
