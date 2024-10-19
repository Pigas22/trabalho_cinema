package models;

import java.sql.Timestamp;

public class Secao {
    private int idSecao;
    private Timestamp horario;
    private int qtdAssentos;
    private Cinema cinema;
    private Filme filme;
    
    public Secao(int idSecao, Timestamp horario, Cinema cinema, Filme filme, int qtdAssentos) {
        this.idSecao = idSecao;
        this.horario = horario;
        this.qtdAssentos = qtdAssentos;
        this.cinema = cinema;
        this.filme = filme;
    }

    public Secao(Timestamp horario, Cinema cinema, Filme filme, int qtdAssentos) {
        this.horario = horario;
        this.qtdAssentos = qtdAssentos;
        this.cinema = cinema;
        this.filme = filme;
    }

    public int getIdSecao() {
        return idSecao;
    }
    public void setIdSecao(int idSecao) {
        this.idSecao = idSecao;
    }

    public Timestamp getHorario() {
        return this.horario;
    }
    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public int getQtdAssentos() {
        return this.qtdAssentos;
    }
    public void setQtdAssentos(int qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    
    }
    
    public Cinema getCinema() {
        return this.cinema;
    }
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    
    public Filme getFilme() {
        return this.filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public String toString() {
        return "Secao [" + "\n" + 
               "    idSecao = " + idSecao + ",\n" +  
               "    horario = " + horario + ",\n" + 
               "    idCinema = " + cinema.getIdCinema() + ",\n" + 
               "    idFilme = " + filme.getIdFilme() + ",\n" + 
               "    qtdAssentos = " + qtdAssentos + ",\n" + 
               "]";
    }
}
