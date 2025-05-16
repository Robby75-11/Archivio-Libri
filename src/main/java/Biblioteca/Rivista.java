package Biblioteca;

public class Rivista extends ElementoCatalogo {
    private String periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public String getPeriodicita() {
        return periodicita;
    }

    @Override
    public String getDettagliSpecifici() {
        return "Periodicità: " + periodicita;
    }
}
