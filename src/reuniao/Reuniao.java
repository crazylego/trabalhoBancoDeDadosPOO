package reuniao;

import java.util.ArrayList;

public class Reuniao {
	private String ata;
	private long data;
	private ArrayList<Integer> frequencia;
	
	public void setAta(String ata) {
		this.ata = ata;
	}
	
	public String getAta() {
		return ata;
	}
	
	public long getData() {
		return data;
	}
	
	public boolean getFre(int num) {
		for(Integer a: frequencia) {
			if(a == num) {
				return true;
			}
		}
		return false;
	}
	
	public void addFrequencia(Integer a) {
		frequencia.add(a);
	}
	
	public void setFrequencia(Integer a) {
		frequencia.add(a);
	}
	
	
	public Reuniao(long data, String ata) {
		this.data = data;
		this.ata = ata;
		frequencia = new ArrayList<Integer>();
	}
}
