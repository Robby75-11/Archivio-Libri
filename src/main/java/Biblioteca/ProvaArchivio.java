package Biblioteca;

import java.util.Scanner;

public class ProvaArchivio {

    public static void main(String[] args) {

        Archivio archivio = new Archivio();


        // Blocco try-catch per precaricare alcuni elementi all'avvio (opzionale)
        try {
            archivio.aggiungiElemento(new Libro("978-8891992110", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy"));
            archivio.aggiungiElemento(new Libro("978-0545583002", "Harry Potter e la pietra filosofale", 1997, 300, "J.K. Rowling", "Fantasy"));
            archivio.aggiungiElemento(new Rivista("1122-3344", "National Geographic", 2023, 150, "MENSILE"));
        } catch (IllegalArgumentException e) {
            System.err.println("Errore durante il precaricamento: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in); //Crea uno Scanner per leggere l'input dell'utente dalla console
        int scelta; // Variabile per memorizzare la scelta dell'utente dal menu

        do {
            System.out.println("\n--- MENU ARCHIVIO BIBLIOGRAFICO ---");
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi elemento per ISBN");
            System.out.println("4. Ricerca per anno pubblicazione");
            System.out.println("5. Ricerca libri per autore");
            System.out.println("6. Aggiorna elemento per ISBN");
            System.out.println("7. Stampa statistiche ");
            System.out.println("8. Visualizza tutti i libri e le riviste con statistiche");
            System.out.println("0. Esci");
            System.out.println("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine();


            // Blocco try-catch per gestire eventuali eccezioni (es. ISBN non trovato)

            try {
                switch (scelta) {
                    case 1:
                        System.out.println("Vuoi aggiungere un Libro (L) o una Rivista (R)?");
                        String tipo = scanner.nextLine().toUpperCase();
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Anno pubblicazione: ");
                        int anno = scanner.nextInt();
                        System.out.print("Numero pagine: ");
                        int pagine = scanner.nextInt();
                        scanner.nextLine(); // Consuma la newline
                        if (tipo.equals("L")) {
                            System.out.print("Autore: ");
                            String autore = scanner.nextLine();
                            System.out.print("Genere: ");
                            String genere = scanner.nextLine();
                            archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
                            System.out.println("Libro aggiunto.");
                        } else if (tipo.equals("R")) {
                            System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                            String periodicita = scanner.nextLine().toUpperCase();
                            archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
                            System.out.println("Rivista aggiunta.");
                        } else {
                            System.out.println("Tipo non valido.");
                        }
                        break;
                    case 2:
                        System.out.print("Inserisci l' ISBN da cercare: ");
                        String isbnRicerca = scanner.nextLine();
                        ElementoCatalogo elementoTrovato = archivio.ricercaPerISBN(isbnRicerca);
                        System.out.println("Elemento trovato: " + elementoTrovato);
                        break;
                    case 3:
                        System.out.print("Inserisci l' ISBN dell'elemento da rimuovere: ");
                        String isbnRimuovi = scanner.nextLine();
                        archivio.rimuoviElemento(isbnRimuovi);
                        System.out.println("Elemento con ISBN " + isbnRimuovi + " rimosso (se presente).");
                        break;
                    case 4:
                        System.out.print("Inserisci l'anno di pubblicazione da cercare: ");
                        int annoRicerca = scanner.nextInt();
                        scanner.nextLine(); // Consuma la newline
                        System.out.println("Elementi pubblicati nel " + annoRicerca + ":");
                        archivio.ricercaPerAnnoPubblicazione(annoRicerca).forEach(System.out::println);
                        break;
                    case 5:
                        System.out.print("Inserisci l'autore da cercare: ");
                        String autoreRicerca = scanner.nextLine();
                        System.out.println("Libri di " + autoreRicerca + ":");
                        archivio.ricercaPerAutore(autoreRicerca).forEach(System.out::println);
                        break;
                    case 6:
                        System.out.print("Inserisci l' ISBN dell'elemento da aggiornare: ");
                        String isbnAggiorna = scanner.nextLine();
                        try {
                            ElementoCatalogo elementoEsistente = archivio.ricercaPerISBN(isbnAggiorna);
                            System.out.println("Dettagli attuali: " + elementoEsistente);
                            System.out.println("Inserisci i nuovi dettagli:");
                            System.out.print("Nuovo Titolo (" + elementoEsistente.getTitolo() + "): ");
                            String nuovoTitolo = scanner.nextLine();
                            System.out.print("Nuovo Anno Pubblicazione (" + elementoEsistente.getAnnoPubblicazione() + "): ");
                            int nuovoAnno = scanner.nextInt();
                            System.out.print("Nuovo Numero Pagine (" + elementoEsistente.getNumeroPagine() + "): ");
                            int nuovoPagine = scanner.nextInt();
                            scanner.nextLine(); // Consuma la newline

                            ElementoCatalogo nuovoElemento;
                            if (elementoEsistente instanceof Libro) {
                                System.out.print("Nuovo Autore (" + ((Libro) elementoEsistente).getAutore() + "): ");
                                String nuovoAutore = scanner.nextLine();
                                System.out.print("Nuovo Genere (" + ((Libro) elementoEsistente).getGenere() + "): ");
                                String nuovoGenere = scanner.nextLine();
                                nuovoElemento = new Libro(isbnAggiorna, nuovoTitolo, nuovoAnno, nuovoPagine, nuovoAutore, nuovoGenere);
                            } else {
                                System.out.print("Nuova Periodicità (" + ((Rivista) elementoEsistente).getPeriodicita() + "): ");
                                String nuovaPeriodicita = scanner.nextLine().toUpperCase();
                                nuovoElemento = new Rivista(isbnAggiorna, nuovoTitolo, nuovoAnno, nuovoPagine, nuovaPeriodicita);
                            }
                            archivio.aggiornaElemento(isbnAggiorna, nuovoElemento);
                            System.out.println("Elemento aggiornato.");

                        } catch (ISBNNotFoundException e) {
                            System.err.println("Errore: " + e.getMessage());
                        }
                        break;
                    case 7:
                        archivio.stampaStatistiche();
                        break;
                    case 8:
                        visualizzaLibriRivisteConStatistiche(archivio);
                        break;
                    case 0:
                        System.out.println("Uscita dal programma.");
                        break;
                    default:
                        System.out.println("Scelta non valida.");
                }
            } catch (ISBNNotFoundException e) {
                System.err.println("Errore: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Errore: " + e.getMessage());
            }
        } while (scelta != 0);

        scanner.close();

    }
        //Metodo per visualizzare elenco di tutti i libri e riviste con statistiche
        public static void visualizzaLibriRivisteConStatistiche(Archivio archivio){
            System.out.println("\n--- Elenco di tutti i Libri ---");
            archivio.getElementiCatalogo().stream()
                    .filter(elemento -> elemento instanceof Libro)
                    .forEach(System.out::println);
            System.out.println("-----------------------------");

            System.out.println("\n--- Elenco di tutte le Riviste ---");
            archivio.getElementiCatalogo().stream()
                    .filter(elemento -> elemento instanceof Rivista)
                    .forEach(System.out::println);
            System.out.println("-----------------------------");



        }

       }


