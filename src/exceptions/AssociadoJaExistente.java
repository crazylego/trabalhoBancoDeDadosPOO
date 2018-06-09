package exceptions;

public class AssociadoJaExistente extends Exception {
	public AssociadoJaExistente() {
		super("Associado já registrado na associação.");
	}
}
