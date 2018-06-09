package associados;

public class Associado {
	private int numero;
	private String nome;
	private String telefone;
	private long nasc;
	private long dataEntrada;
	
	public int getNumero() {
		return this.numero;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEndereco() {
		return this.telefone;
	}
	
	public long getNascimento() {
		return this.nasc;
	}
	
	public long getDataEntrada() {
		return this.dataEntrada;
	}

	
	public void setEndereco(String telefone) {
		this.telefone = telefone;
	}
	

	public Associado(int num, String nome, String telefone, long nasc, long entrada){
		this.numero = num;
		this.nome = nome;
		this.telefone = telefone;
		this.nasc = nasc;
		this.dataEntrada = entrada;
	}
}
