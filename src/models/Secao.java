package models;

import java.time.LocalTime;

public class Secao {
    private LocalTime horario;
    private int quant_assentos;
    private Cinema cinema;
    private Filme filme;
    
    public Secao(LocalTime horario, int quant_assentos, Cinema cinema, Filme filme) {
        this.horario = horario;
        this.quant_assentos = quant_assentos;
        this.cinema = cinema;
        this.filme = filme;
    }
    public LocalTime getHorario() {
        return this.horario;
    }
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
    public int getQuant_assentos() {
        return this.quant_assentos;
    }
    public void setQuant_assentos(int quant_assentos) {
        this.quant_assentos = quant_assentos;
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
}
