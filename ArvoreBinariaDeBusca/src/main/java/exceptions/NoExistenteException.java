package exceptions;

public class NoExistenteException extends Exception {

    public NoExistenteException() {
        super("O nó já está presente na Árvore!");
    }

    public NoExistenteException(String message) {
        super(message);
    }
}
