package associados;

public class AssociadoRemido extends Associado{
	private long dataRemissao;
	
	public long getDataRemissao() {
		return this.dataRemissao;
	}
	
	public void setRemissao(long remissao) {
		this.dataRemissao = remissao;
	}
	
	public AssociadoRemido(int num, String nome, String telefone, long nasc, long entrada,long remissao){
		super(num, nome, telefone, nasc, entrada);
		this.dataRemissao = remissao;
	}
	

}
