package exceptions;

  public class ReuniaoJaExistente extends Exception {
		public ReuniaoJaExistente() {
			super("Reunião já registrada na associação.");
        }
}
