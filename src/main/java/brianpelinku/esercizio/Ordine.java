package brianpelinku.esercizio;

import brianpelinku.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class Ordine {
    private long id;
    private Status status;
    private LocalDate dataOrdine;
    private LocalDate dataSpedizione;
    private List<Prodotto> prodotti;
    private Cliente cliente;

    //costruttori
    public Ordine(Status status, LocalDate dataOrdine, LocalDate dataSpedizione, List<Prodotto> prodotti, Cliente cliente) {
        this.status = status;
        this.dataOrdine = dataOrdine;
        this.dataSpedizione = dataSpedizione;
        this.prodotti = prodotti;
        this.cliente = cliente;
    }


    // getter e setter
    public long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public LocalDate getDataSpedizione() {
        return dataSpedizione;
    }

    public void setDataSpedizione(LocalDate dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // toString
    @Override
    public String toString() {
        return "\n----- Ordine -----\n{" +
                "id = " + id +
                ", \nstatus = " + status +
                ", \ndataOrdine = " + dataOrdine +
                ", \ndataSpedizione = " + dataSpedizione +
                ", \nprodotti = " + prodotti +
                ", \ncliente = " + cliente +
                '}';
    }
}
