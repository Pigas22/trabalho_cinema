package models;

public class Venda {
    private int idVenda;
    private String nomeCliente;
    private int assento;
    private String formaPagamento;
    private Secao secao;
    
    public Venda(int idVenda, String nomeCliente, int assento, String formaPagamento, Secao secao) {
        this.idVenda = idVenda;
        this.nomeCliente = nomeCliente;
        this.assento = assento;
        this.formaPagamento = formaPagamento;
        this.secao = secao;
    }

    public Venda(String nomeCliente, int assento, String formaPagamento, Secao secao) {
        this.nomeCliente = nomeCliente;
        this.assento = assento;
        this.formaPagamento = formaPagamento;
        this.secao = secao;
    }

    public int getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getAssento() {
        return this.assento;
    }
    public void setAssento(int assento) {
        this.assento = assento;
    }

    public String getFormaPagamento() {
        return this.formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public Secao getSecao() {
        return this.secao;
    }
    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public String toString() {
        return "Venda [" + "\n" + 
               "    idVenda = " + idVenda + ",\n" +  
               "    nomeCliente = " + nomeCliente + ",\n" + 
               "    assento = " + assento + ",\n" + 
               "    formaPagamento = " + formaPagamento + ",\n" + 
               "    idSecao = " + secao.getIdSecao() + ",\n" + 
               "]";
    }
    
}
