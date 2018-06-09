package associacao;

import java.util.ArrayList;

import associados.*;
import exceptions.*;
import taxas.*;
import reuniao.*; 

public class Associacao {
	private int numero;
	private String nome;
	RepositorioDeAssociados associados;
	RepositorioDeTaxas taxas;
	RepositorioDeReuniao reunioes;

	// Retorna o n�mero da Associa��o
	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int num) {
		this.numero = num;
	}
	
	// Retorna o nome da Associa��o
	public String getNome() {
		return this.nome;
	}
	
	// Muda o nome
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Reuniao> getReunioes(){
		return reunioes.getReunioes();
	}
	
	public ArrayList<Associado> getAssociados(){
		return associados.getAssociados();
	}
	
	public ArrayList<Taxa> getTaxas(){
		return taxas.getTaxas();
	}

	public void pesquisaAssociado(int num) throws AssociadoNaoExistente, AssociadoJaExistente {
		associados.pesquisaAssociado(num);
	}

	public void adicionarAssociado(Associado a) throws ValorInvalido {
		associados.adicionarAssociado(a);
	}
	
	public void pesquisarReuniao(long r) throws ReuniaoJaExistente, ReuniaoNaoExistente {
		reunioes.pesquisarReuniao(r);
	}
	
	public void addReuniao(Reuniao r) throws ValorInvalido {
		reunioes.addReuniao(r);
	}
	
	public void pesquisarTaxa(Taxa tax) throws TaxaNaoExistente, TaxaJaExistente {
		taxas.pesquisarTaxa(tax);
	}
	
	public void addTaxa(Taxa tax) throws ValorInvalido{
		taxas.adicionarTaxa(tax);
	}
	
	public void pesquisarVigencia(int num) throws TaxaJaExistente, TaxaNaoExistente {
		taxas.pesquisarVigencia(num);
	}
	
	public double totalTaxas(int vigen) {
		return taxas.somaTaxas(vigen);
	}
	
	public void pesquisarFrequencia(int numAssociado, long data) throws ReuniaoNaoExistente, FrequenciaJaRegistrada {
		reunioes.pesquisarFrequencia(numAssociado, data);
	}
	
	public void addFrequencia( Associado a, long data) throws FrequenciaIncompativel, ReuniaoNaoExistente {
		reunioes.addFrequencia( a, data);
	}
	
	public Associado getAssociado(int num) throws AssociadoNaoExistente {
		return associados.getAssociado(num);
	}
	
	public int reunioesTotais() throws ReuniaoNaoExistente {
		return reunioes.reunioesTotais();
	}

	public int presenca(int numAssociado, long dataInicio, long dataFim) {
		return reunioes.presencaAssociado(numAssociado, dataInicio, dataFim);
	}
	
	public void registrarPagamento(int vigencia,String taxa, double valor, long data, int numAssociado) throws ValorInvalido, TaxaNaoExistente, AssociadoNaoExistente, AssociadoJaRemido {
		taxas.registrarPagamento(vigencia, taxa, valor, data, associados.getAssociado(numAssociado));
	}
	
	public double totalTaxas(String taxa,int numAssociado, long inicio, long fim, int vigencia) throws TaxaNaoExistente {
		return  taxas.totalPago(taxa, numAssociado, inicio, fim, vigencia);
	}
	
	// M�todo construtor da classe
	public Associacao(int num, String nome) {
		this.numero = num;
		this.nome = nome;
		associados = new RepositorioDeAssociados();
		reunioes = new RepositorioDeReuniao();
		taxas = new RepositorioDeTaxas();
	}




}
