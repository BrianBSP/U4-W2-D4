package brianpelinku.esercizio;


import brianpelinku.enums.Tier;

import java.util.Random;


public class Cliente {
    private long id;
    private Tier tier;
    private String nomeCliente;

    // costruttori
    public Cliente(String nome, Tier tier) {
        Random random = new Random();
        this.id = random.nextInt(1, 1000);
        this.nomeCliente = nome;
        this.tier = tier;
    }

    // getter e setter
    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public long getId() {
        return id;
    }

    // toString
    @Override
    public String toString() {
        return "\n-----Cliente-----\n{" +
                "id = " + id +
                ", \ntier = " + tier +
                ", \nnome Cliente = '" + nomeCliente + '\'' +
                '}';
    }
}
