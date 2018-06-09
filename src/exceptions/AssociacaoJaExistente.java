package exceptions;

public class AssociacaoJaExistente extends Exception {
	public AssociacaoJaExistente(){
		super("Associação já cadastrada.");
    }
}
