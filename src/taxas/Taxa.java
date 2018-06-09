package taxas;

import java.util.ArrayList;

import exceptions.ValorInvalido;

public class Taxa {
	private String nome;
	private int vigencia;
	private double valorAno;
	private int parcelas;
	private boolean administrativa;
//    private	Associacao associacao;
	private ArrayList<FolhaDePagamento> folha;
	
	
	public ArrayList<FolhaDePagamento> getFolha(){
		return folha;
	}
	
	public void atualizaFolha(int i,double valor,long data, int num) throws ValorInvalido {
		double pagamento = taxasPagas(i,num);
		FolhaDePagamento p = new FolhaDePagamento(i, valor, data);
		if(p.getValorPago() < valorAno/parcelas && p.getValorPago() + pagamento < valorAno) {
			throw new ValorInvalido("Pagamento");
		}else {
			folha.add(p);
		}	
	}
	
	public double totalTaxas(int numAssociado, long inicio, long fim, int vigencia) {
		double aux = 0;
		for(FolhaDePagamento p: folha) {
			if(p.getData() >= inicio && p.getData() <= fim && p.getNumAssociado() == numAssociado) {
				aux = aux + p.getValorPago();
			}
		}
		return aux;
	}
	
	public double taxasPagas(int numAssociado, int vigencia) {
		double aux = 0;
		for(FolhaDePagamento p: folha) {
			if(p.getNumAssociado() == numAssociado) {
				aux = aux + p.getValorPago();
			}
		}
		return aux;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getVigencia() {
		return this.vigencia;
	}
	
	public double getValor() {
		return this.valorAno;
	}
	
	public int getParcelas() {
		return this.parcelas;
	}
	
	public void setParcelas(int p) {
		this.parcelas = p;
	}
	
	public boolean getTipo() {
		return this.administrativa;
	}

	public Taxa(String nome, int vigen, double valorAno, int parce, boolean adm){
		this.nome = nome;
		this.vigencia = vigen;
		this.valorAno = valorAno;
		this.parcelas = parce;
		this.administrativa = adm;
		folha = new ArrayList<FolhaDePagamento>();
	}
}
