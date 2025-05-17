package Biblioteca;

/**
 * Eccezione personalizzata per indicare che un elemento con l'ISBN specificato non è stato trovato.
 * Estende la classe Exception, quindi è un'eccezione checked (deve essere gestita o dichiarata nel metodo).
 */
public class ISBNNotFoundException extends Exception {

    /**
     * Costruttore della classe ISBNNotFoundException.
     * Chiama il costruttore della superclasse (Exception) passando il messaggio di errore.
     *
     * @param message Il messaggio di errore specifico per questa eccezione.
     */
    public ISBNNotFoundException(String message) {
        super(message);

    }
}
