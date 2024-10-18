package models;

public class Cinema {
    private int idCinema;
    private String nome;
    private Endereco endereco;

    public Cinema(int idCinema, String nome, Endereco endereco) {
        this.idCinema = idCinema;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getIdCinema() {
        return this.idCinema;
    }
    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
