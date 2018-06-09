package reuniao;

import java.util.ArrayList;

import associados.Associado;
import exceptions.FrequenciaIncompativel;
import exceptions.FrequenciaJaRegistrada;
import exceptions.ReuniaoJaExistente;
import exceptions.ReuniaoNaoExistente;
import exceptions.ValorInvalido;

public class RepositorioDeReuniao {
	ArrayList<Reuniao> reunioes;

	public void pesquisarReuniao(long data) throws ReuniaoJaExistente, ReuniaoNaoExistente {
		for(Reuniao n: reunioes) {
			if(n.getData() == data) {
				throw new ReuniaoJaExistente();
			}
		}
		throw new ReuniaoNaoExistente();
	}

	public void addReuniao(Reuniao r) throws ValorInvalido {
		if(r.getData() <= 0) {
			throw new ValorInvalido("Data");
		}else if(r.getAta() == null || r.getAta() == "") {
			throw new ValorInvalido("Nome");
		}else{
			reunioes.add(r);
		}
	}
	
	public void pesquisarFrequencia(int numAssociado, long data) throws FrequenciaJaRegistrada {
		for(Reuniao n: reunioes) {
			if(n.getFre(numAssociado) == true) {
				throw new FrequenciaJaRegistrada();
			}
		}
	}
	
	public int getReuniao(long data) throws ReuniaoNaoExistente {
		int i = 0;
		for(i = 0; i < reunioes.size(); i++) {
			if(reunioes.get(i).getData() == data) {
				return i;
			}
		}
		throw new ReuniaoNaoExistente();
	}
	
	public void addFrequencia(Associado a, long data) throws FrequenciaIncompativel, ReuniaoNaoExistente{
		if(a.getDataEntrada() > data) {
			throw new FrequenciaIncompativel();
		}else {
			reunioes.get(getReuniao(data)).addFrequencia(a.getNumero());

			
		}
	}
	
	public int reunioesTotais() throws ReuniaoNaoExistente {
		if(reunioes.size() == 0) {
			throw new ReuniaoNaoExistente();
		}else {
			return reunioes.size();
		}
	}
	
	public int presencaAssociado(int numAssociado, long dataInicio, long dataFim) {
		int i;
		int cont = 0;
		for(i = 0; i < reunioes.size(); i++) {
			if(reunioes.get(i).getFre(numAssociado) == true && reunioes.get(i).getData() >= dataInicio && reunioes.get(i).getData() <= dataFim) {
				cont = cont + 1;
			}
		 }
		return cont;
	}
	
	public ArrayList<Reuniao> getReunioes(){
		return reunioes;
	}
	
	public RepositorioDeReuniao() {
		reunioes = new ArrayList<Reuniao>();
	}
}
