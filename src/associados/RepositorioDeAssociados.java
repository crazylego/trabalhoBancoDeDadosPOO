package associados;

import java.util.ArrayList;
import exceptions.AssociadoJaExistente;
import exceptions.AssociadoNaoExistente;
import exceptions.ValorInvalido;

public class RepositorioDeAssociados {
	ArrayList<Associado> associados;
	
	public Associado pesquisaAssociado(int num) throws AssociadoNaoExistente, AssociadoJaExistente {
		for(Associado a: associados) {
			if(a.getNumero() == num) {
				throw new AssociadoJaExistente();
			}
		}
		throw new AssociadoNaoExistente();
	}
	
	public void adicionarAssociado(Associado a) throws ValorInvalido {
		if(a.getNome() == null || a.getNome() == "") {
			throw new ValorInvalido("Nome");
		}else if(a.getNumero() == 0){
			throw new ValorInvalido("N�mero");
		}else if(a.getDataEntrada() == 0){
			throw new ValorInvalido("Data de Entrada");
		}else if(a.getEndereco() == null || a.getEndereco() == "") {
			throw new ValorInvalido("Telefone");
		}else if(a.getNascimento() == 0) {
			throw new ValorInvalido("Data de Nascimento");
		}else {
			associados.add(a);
		}
		
	}
	
	public Associado getAssociado(int num) throws AssociadoNaoExistente {
		for(Associado a: associados) {
			if(a.getNumero() == num) {
				return a;
			}
		}
		throw new AssociadoNaoExistente();
	}
	
	public ArrayList<Associado> getAssociados(){
		return associados;
	}
	
	public int quantAssociados() {
		return associados.size();
	}
	
	public RepositorioDeAssociados(){
		associados = new ArrayList<Associado>();
	}
}
