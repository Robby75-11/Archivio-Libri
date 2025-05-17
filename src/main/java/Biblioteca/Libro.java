package Biblioteca;

//Classe che rappresenta un libro, estendendo la classe astratta ElementoCatalogo.
public class Libro extends ElementoCatalogo {
    private  String autore;
    private  String genere;

   // Costruttore della classe Libro.
   // Chiama il costruttore della superclasse (ElementoCatalogo) e inizializza gli attributi specifici.
    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }
    //Restituisce l'autore del libro.
    public String getAutore() {
        return autore;
    }

    //Restituisce il genere del libro.
    public String getGenere() {
        return genere;
    }

    @Override
    public String getDettagliSpecifici() {
        return "Autore: " + autore + ", Genere: " + genere;
            }

}
