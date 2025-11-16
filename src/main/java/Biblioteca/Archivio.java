package Biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Archivio {
     private List<ElementoCatalogo> elementiCatalogo;// Lista privata per memorizzare gli elementi del catalogo (Libri e Riviste)

     public Archivio() {
         this.elementiCatalogo = new ArrayList<>();

     }

    /**
     * Aggiunge un elemento (Libro o Rivista) al catalogo.
     * @param elemento L'elemento da aggiungere (istanza di ElementoCatalogo).
     * @throws IllegalArgumentException Se un elemento con lo stesso ISBN è già presente nel catalogo.
     */
    public void aggiungiElemento(ElementoCatalogo elemento) throws IllegalArgumentException {
        if (elementiCatalogo.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            throw new IllegalArgumentException("Elemento con ISBN " + elemento.getIsbn() + " già presente nel catalogo.");
        }
        elementiCatalogo.add(elemento);
    }

    // Ricerca tramite ISBN

    public ElementoCatalogo ricercaPerISBN(String isbn) throws ISBNNotFoundException {
        return elementiCatalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ISBNNotFoundException("Elemento con ISBN " + isbn + " non trovato."));
    }

    public void rimuoviElemento(String isbn) {
        elementiCatalogo.removeIf(e -> e.getIsbn().equals(isbn));
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        return elementiCatalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<Libro> ricercaPerAutore(String autore) {
        return elementiCatalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws ISBNNotFoundException {
        int index = -1;
        for (int i = 0; i < elementiCatalogo.size(); i++) {
            if (elementiCatalogo.get(i).getIsbn().equals(isbn)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new ISBNNotFoundException("Elemento con ISBN " + isbn + " non trovato per l'aggiornamento.");
        }
        // Manteniamo l' 'ISBN' originale
        nuovoElemento = aggiornaIsbnSeNecessario(elementiCatalogo.get(index).getIsbn(), nuovoElemento);
        elementiCatalogo.set(index, nuovoElemento);
    }



    private ElementoCatalogo aggiornaIsbnSeNecessario(String vecchioIsbn, ElementoCatalogo nuovoElemento) {
        if (!vecchioIsbn.equals(nuovoElemento.getIsbn())) {
            // Potresti decidere se permettere o meno il cambio di ISBN durante l'aggiornamento
            System.out.println("Attenzione: L'ISBN dell'elemento è stato modificato.");
            // In una logica più restrittiva, potresti lanciare un'eccezione
        }
        return nuovoElemento;
    }

    /**
     * Calcola e stampa le statistiche del catalogo, inclusi il numero di libri,
     * il numero di riviste, l'elemento con più pagine e la media delle pagine.
     */

    public void stampaStatistiche() {
        long numeroLibri = elementiCatalogo.stream().filter(e -> e instanceof Libro).count();
        long numeroRiviste = elementiCatalogo.stream().filter(e -> e instanceof Rivista).count();

        Optional<ElementoCatalogo> elementoConPiuPagine = elementiCatalogo.stream()
                .max((e1, e2) -> Integer.compare(e1.getNumeroPagine(), e2.getNumeroPagine()));

        double mediaPagine = elementiCatalogo.stream()
                .mapToInt(ElementoCatalogo::getNumeroPagine)
                .average()
                .orElse(0);

        System.out.println("--- Statistiche dell'Archivio ---");
        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);
        if (elementoConPiuPagine.isPresent()) {
            System.out.println("Elemento con più pagine: " + elementoConPiuPagine.get().getTitolo() + " (" + elementoConPiuPagine.get().getNumeroPagine() + " pagine)");
        } else {
            System.out.println("Nessun elemento nell'archivio.");
        }
        System.out.println("Media delle pagine di tutti gli elementi: " + String.format("%.2f", mediaPagine));
        System.out.println("-----------------------------");
    }

         // Restituisce la lista completa degli elementi del catalogo.
        public List<ElementoCatalogo> getElementiCatalogo() {
            return elementiCatalogo;




    }

}
