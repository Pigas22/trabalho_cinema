package models;

public class Filme {
    private int idFilme;
    private String nomeFilme;
    private double preco;

    public Filme(int idFilme, String nomeFilme, double preco) {
        this.idFilme = idFilme;
        this.nomeFilme = nomeFilme;
        this.preco = preco;
    }
    
    public Filme(String nomeFilme, double preco) {
        this.nomeFilme = nomeFilme;
        this.preco = preco;
    }

    public int getIdFilme() {
        return this.idFilme;
    }
    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getNomeFilme() {
        return this.nomeFilme;
    }
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }
    
    public double getPreco() {
        return this.preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String toString() {
        return "Filema [" + "\n" + 
               "    idEndereco = " + idFilme + ",\n" +  
               "    nomeFilme = " + nomeFilme + ",\n" + 
               "    preco = " + preco + ",\n" + 
               "]";
    }
}
