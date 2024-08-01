package brianpelinku;

import brianpelinku.enums.Status;
import brianpelinku.enums.Tier;
import brianpelinku.esercizio.Cliente;
import brianpelinku.esercizio.Ordine;
import brianpelinku.esercizio.Prodotto;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        Faker f = new Faker(Locale.ITALY);

        // creazione prodotti
        List<Prodotto> prodotti = new ArrayList<>();
        Prodotto prodotto1 = new Prodotto("Avvocato canaglia", "Books", 150);
        Prodotto prodotto2 = new Prodotto("Pannolino", "Baby", 15);
        Prodotto prodotto3 = new Prodotto("Hot Wheels", "Boys", 180);
        Prodotto prodotto4 = new Prodotto("Peppa Pig", "Baby", 10);
        Prodotto prodotto5 = new Prodotto("La ragazza del treno", "Books", 110);
        Prodotto prodotto6 = new Prodotto("Un fuoco che brucia lento", "Books", 90);

        prodotti.add(prodotto1);
        prodotti.add(prodotto2);
        prodotti.add(prodotto3);
        prodotti.add(prodotto4);
        prodotti.add(prodotto5);
        prodotti.add(prodotto6);

        //prodotti.forEach(System.out::println);

        // creazione clienti
        Cliente cliente1 = new Cliente(f.friends().character(), Tier.LIVELLO1);
        Cliente cliente2 = new Cliente(f.friends().character(), Tier.LIVELLO2);
        Cliente cliente3 = new Cliente(f.friends().character(), Tier.LIVELLO2);
        Cliente cliente4 = new Cliente(f.friends().character(), Tier.LIVELLO3);

        List<Cliente> clienti = new ArrayList<>();
        clienti.add(cliente1);
        clienti.add(cliente2);
        clienti.add(cliente3);
        clienti.add(cliente4);

        //clienti.forEach(System.out::println);

        // creazione ordine
        List<Ordine> listaOrdini = new ArrayList<>();
        Ordine ordine1 = new Ordine(Status.SPEDITO, LocalDate.of(2024, 7, 1), LocalDate.now(), Arrays.asList(prodotto1, prodotto2, prodotto3, prodotto6), cliente1);
        Ordine ordine2 = new Ordine(Status.CONSEGNATO, LocalDate.of(2024, 5, 28), LocalDate.of(2024, 5, 31), Arrays.asList(prodotto1, prodotto4), cliente2);
        Ordine ordine3 = new Ordine(Status.CONSEGNATO, LocalDate.of(2024, 4, 28), LocalDate.of(2024, 4, 30), Arrays.asList(prodotto1, prodotto3, prodotto5), cliente4);
        Ordine ordine4 = new Ordine(Status.CONSEGNATO, LocalDate.of(2024, 6, 28), LocalDate.of(2024, 6, 30), Arrays.asList(prodotto2, prodotto4), cliente3);

        listaOrdini.add(ordine1);
        listaOrdini.add(ordine2);
        listaOrdini.add(ordine3);
        listaOrdini.add(ordine4);

        //ordini.forEach(System.out::println);

        // es 1 crea una mappa: <chiave: cliente - valore:lista ordini>
        System.out.println("--------------- Es 1 -------------");
        Map<Cliente, List<Ordine>> listaOrdiniPerCliente = listaOrdini
                .stream()
                .collect(Collectors.groupingBy(Ordine::getCliente));
        listaOrdiniPerCliente.forEach((cliente, ordini) -> {
            System.out.println("Cliente: \n" + cliente.getNomeCliente() + " " + ordini);
        });

        // es 2 crea una mappa: <chiave cliente - valore importo totale degli acquisti>
        System.out.println("---------------- Es 2 ---------------");
        Map<Cliente, Double> importoTotPerCliente = listaOrdini
                .stream()
                .collect(Collectors
                        .groupingBy(Ordine::getCliente, Collectors.summingDouble(ordine -> ordine.getProdotti()
                                .stream().mapToDouble(Prodotto::getPrezzo).sum())));
        importoTotPerCliente.forEach((cliente, totImporto) ->
                System.out.println("Cliente: " + cliente.getNomeCliente() + "\nImporto Totale: " + totImporto));

        // es 3 elenco dei 3 prodotti più costosi
        System.out.println("---------------- Es 3 ---------------");
        List<Prodotto> top3ProdCostosi = prodotti
                .stream()
                .sorted(Comparator.comparingDouble(Prodotto::getPrezzo).reversed())
                .limit(3)
                .toList();
        System.out.println("Elenco dei 3 prodotti più costosi: ");
        top3ProdCostosi.forEach(System.out::println);

        // es 4 media degli importi degli ordini
        System.out.println("---------------- Es 4 ---------------");
        OptionalDouble mediaImportiOrdini = listaOrdini
                .stream()
                .mapToDouble(ordine -> ordine.getProdotti().stream().mapToDouble(Prodotto::getPrezzo).sum())
                .average();
        System.out.println("La media degli importi degli ordini è: " + mediaImportiOrdini);

        // es 5 raggruppa i prodotti per categoria e calcola la somma dei prodotti per ogni categoria
        System.out.println("---------------- Es 5 ---------------");
        Map<String, Double> sommaPrezziPerCategoriaProdotti = prodotti
                .stream()
                .collect(Collectors.groupingBy(Prodotto::getCategoria, Collectors.summingDouble(Prodotto::getPrezzo)));
        sommaPrezziPerCategoriaProdotti.forEach((categoria, totPrezzo) ->
                System.out.println("Categoria: " + categoria + "\nPrezzo totale categoria: " + totPrezzo)
        );
    }
}
