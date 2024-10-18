package models;

public class Venda {
    private String nome_cliente;
    private String assento;
    private String forma_pagamento;
    private Secao secao;
    
    public Venda(String nome_cliente, String assento, String forma_pagamento, Secao secao) {
        this.nome_cliente = nome_cliente;
        this.assento = assento;
        this.forma_pagamento = forma_pagamento;
        this.secao = secao;
    }
    public String getNome_cliente() {
        return this.nome_cliente;
    }
    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }
    public String getAssento() {
        return this.assento;
    }
    public void setAssento(String assento) {
        this.assento = assento;
    }
    public String getForma_pagamento() {
        return this.forma_pagamento;
    }
    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }
    public Secao getSecao() {
        return this.secao;
    }
    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    
}
