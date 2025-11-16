package Biblioteca;

//Classe che rappresenta Rivista, estendendo la classe astratta ElementoCatalogo.
public class Rivista extends ElementoCatalogo {
    private String periodicita;


    //Costruttore della classe Rivista.
   // Chiama il costruttore della superclasse (ElementoCatalogo) e inizializza gli attributi specifici.
    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public String getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(String periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String getDettagliSpecifici() {
        return "Periodicità: " + periodicita;
    }
}
