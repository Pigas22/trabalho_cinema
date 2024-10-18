package models;

public class Endereco {
    private int idEndereco;
    private int numero;
    private String rua;

    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(int idEndereco, int numero, String rua, String bairro, String cidade, String uf) {
        this.idEndereco = idEndereco;
        this.numero = numero;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(int idEndereco, int numero, String rua, String cidade, String uf) {
        this.idEndereco = idEndereco;
        this.numero = numero;
        this.rua = rua;
        this.bairro = null;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(int numero, String rua, String bairro, String cidade, String uf) {
        this.numero = numero;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco(int numero, String rua, String cidade, String uf) {
        this.numero = numero;
        this.rua = rua;
        this.bairro = null;
        this.cidade = cidade;
        this.uf = uf;
    }
    
    public int getIdEndereco() {
        return this.idEndereco;
    }
    public void setIdEndereco (int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getNumero() {
        return this.numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return this.rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return this.bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return this.uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    public String toString() {
        return "Endereco [" + "\n" + 
               "    idEndereco = " + idEndereco + ",\n" +  
               "    numero = " + numero + ",\n" + 
               "    rua = " + rua + ",\n" + 
               "    bairro = " + bairro + ",\n" + 
               "    cidade = " + cidade + ",\n" + 
               "    uf = " + uf + ",\n" + 
               "]";
    }
}
