package models;

public class Filme {
    private int idFilme;
    private String nome_filme;
    private int preco;

    public Filme(int idFilme, String nome_filme, int preco) {
        this.idFilme = idFilme;
        this.nome_filme = nome_filme;
        this.preco = preco;
    }

    public int getIdFilme() {
        return this.idFilme;
    }
    public void setIdFilme(int idFilme) {

    }

    public String getNome_filme() {
        return this.nome_filme;
    }
    public void setNome_filme(String nome_filme) {
        this.nome_filme = nome_filme;
    }
    
    public int getPreco() {
        return this.preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
}
