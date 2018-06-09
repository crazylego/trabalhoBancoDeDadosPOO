package exceptions;

public class TaxaJaExistente extends Exception {

	public TaxaJaExistente() {
		super("Taxa já cadastrada na associação para essa vigência.");
	}
}
