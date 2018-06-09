package taxas;

public class FolhaDePagamento {
	private int numAssociado;
	private double valorPago;
	private long data;
//	private double restante;
	
	public int getNumAssociado() {
		return this.numAssociado;
	}
	
	public double getValorPago() {
		return this.valorPago;
	}
	
	public long getData() {
		return data;
	}
	
/*	public void atualizaRestante(double pagamento) {
		restante = restante - pagamento;
	}*/
	
	public FolhaDePagamento(int numAssociado,double valor,long data) {
		this.numAssociado = numAssociado;
		this.data = data;
		this.valorPago = valor;
//		this.restante = valor;
	}
	
}
