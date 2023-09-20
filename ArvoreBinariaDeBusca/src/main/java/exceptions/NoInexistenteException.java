package exceptions;

public class NoInexistenteException extends Exception {

    public NoInexistenteException() {
        super("Nó Inexistente na Árvore");
    }

    public NoInexistenteException(String message) {
        super(message);
    }
}
