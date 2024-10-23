package utils;

import controllers.*;
import models.*;
import reports.Relatorio;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

import java.sql.Timestamp;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);

    public static void imprimirMenu() {
        System.out.println("==================================");
        System.out.println("|          MENU PRINCIPAL        |");
        System.out.println("==================================");
        System.out.println("| 1. Relatório                   |");
        System.out.println("| 2. Inserir Registro            |");
        System.out.println("| 3. Alterar Registro            |");
        System.out.println("| 4. Remover Registro            |");
        System.out.println("| 0. Sair                        |");
        System.out.println("==================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void imprimirMenuInserirRegistro() {
        System.out.println("==================================");
        System.out.println("|         INSERIR REGISTRO       |");
        System.out.println("==================================");
        System.out.println("| 1. Cinema                      |");
        System.out.println("| 2. Endereço                    |");
        System.out.println("| 3. Filme                       |");
        System.out.println("| 4. Sessão                      |");
        System.out.println("| 5. Venda                       |");
        System.out.println("| 6. Voltar                      |");
        System.out.println("==================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void imprimirMenuRemoverRegistro() {
        System.out.println("==================================");
        System.out.println("|         REMOVER REGISTRO       |");
        System.out.println("==================================");
        System.out.println("| 1. Cinema                      |");
        System.out.println("| 2. Endereço                    |");
        System.out.println("| 3. Filme                       |");
        System.out.println("| 4. Sessão                      |");
        System.out.println("| 5. Venda                       |");
        System.out.println("| 6. Voltar                      |");
        System.out.println("==================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void imprimirMenuAlterarRegistro() {
        System.out.println("==================================");
        System.out.println("|         ALTERAR REGISTRO       |");
        System.out.println("==================================");
        System.out.println("| 1. Cinema                      |");
        System.out.println("| 2. Endereço                    |");
        System.out.println("| 3. Filme                       |");
        System.out.println("| 4. Sessão                      |");
        System.out.println("| 5. Venda                       |");
        System.out.println("| 6. Voltar                      |");
        System.out.println("==================================");
        System.out.print("Escolha uma opção: ");
    }

    
    public static void menuInserirEndereco() {

        MenuFormatter.titulo("INSERIR - ENDEREÇO");

        System.out.print("Digite o número: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a rua: ");
        String rua = scanner.nextLine();
        System.out.print("Digite o bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o UF: ");
        String uf = scanner.nextLine();

        EnderecoController.inserirRegistro(new Endereco(numero, rua, bairro, cidade, uf));
        
        MenuFormatter.msgTerminalINFO("Endereço inserido com sucesso!");
    }

    public static void menuInserirCinema() {
        MenuFormatter.titulo("INSERIR - CINEMA");

        System.out.print ("Digite o nome: ");
        String nome = scanner.nextLine();

        List<Endereco> enderecos =  EnderecoController.listarTodosRegistros ();
        if (enderecos.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum endereço disponível. Por favor, insira um endereço antes.");
            return;
        }
        
        for (Endereco endereco : enderecos) {
            System.out.println(endereco.getIdEndereco() + ": " + endereco.getRua() + ", " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getUf());
        }
        
        System.out.print("Escolha um endereço pelo ID: ");
        int idEndereco = scanner.nextInt();
        Endereco enderecoSelecionado = EnderecoController.buscarRegistroPorId(idEndereco);

        if (enderecoSelecionado == null) {
            MenuFormatter.msgTerminalERROR("ID de endereço inválido.");
            return;
        }

        CinemaController.inserirRegistro(new Cinema(nome, enderecoSelecionado));
    }

    public static void menuInserirFilme() {

        MenuFormatter.titulo("INSERIR - FILME");

        System.out.println("Digite o nome do Filme: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o preço do Filme: ");
        Double preco = scanner.nextDouble();

        FilmeController.inserirRegistro(new Filme(nome, preco));

        MenuFormatter.msgTerminalINFO("Filme inserido com sucesso!");

    }

    public static void menuInserirSecao() {
        MenuFormatter.titulo("INSERIR - SECAO");

        List<Cinema> cinemas = CinemaController.listarTodosRegistros();
        if (cinemas.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum cinema disponível. Por favor, insira um cinema antes.");
            return;
        }
    
        List<Filme> filmes = FilmeController.listarTodosRegistros();
        if (filmes.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum filme disponível. Por favor, insira um filme antes.");
            return;
        }
    
        System.out.println("Escolha um cinema pelo ID: ");
        for (Cinema cinema : cinemas) {
            System.out.println(cinema.getIdCinema() + ": " + cinema.getNomeCinema());
        }
    
        int idCinema = scanner.nextInt();
        Cinema cinemaSelecionado = null;
        for (Cinema cinema : cinemas) {
            if (cinema.getIdCinema() == idCinema) {
                cinemaSelecionado = cinema;
            }
        }
    
        if (cinemaSelecionado == null) {
            MenuFormatter.msgTerminalERROR("ID de cinema inválido.");
            return;
        }
    
        System.out.println("Escolha um filme pelo ID: ");
        for (Filme filme : filmes) {
            System.out.println(filme.getIdFilme() + ": " + filme.getNomeFilme());
        }
    
        int idFilme = scanner.nextInt();
        Filme filmeSelecionado = null;
        for (Filme filme : filmes) {
            if (filme.getIdFilme() == idFilme) {
                filmeSelecionado = filme;
            }
        }
    
        if (filmeSelecionado == null) {
            MenuFormatter.msgTerminalERROR("ID de filme inválido.");
            return;
        }
    
        System.out.println("Digite o horário da seção (formato: yyyy-MM-dd HH:mm:ss): ");
        scanner.nextLine(); // Consumir a nova linha
        String horarioStr = scanner.nextLine();
        Timestamp horario = Timestamp.valueOf(horarioStr);

        System.out.println("Digite a quantidade de assentos: ");
        scanner.nextLine();
        int qtd_assento = scanner.nextInt();
    
        //insira no banco de dados
        SecaoController.inserirRegistro(new Secao(horario, cinemaSelecionado, filmeSelecionado, qtd_assento));
    
        MenuFormatter.msgTerminalINFO("Seção inserido com sucesso!");
    }
    
    public static void menuInserirVenda() {
        MenuFormatter.titulo("INSERIR - VENDA");

        List<Secao> secoes = SecaoController.listarTodosRegistros();
        if (secoes.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhuma seção disponível. Por favor, insira uma seção antes.");
            return;
        }

        System.out.println("Escolha uma seção pelo ID: ");
        for (Secao secao : secoes) {
            System.out.println(secao.getIdSecao() + ": " + secao.getFilme().getNomeFilme() + ": " + secao.getHorario() + ": " + secao.getCinema().getEndereco().getCidade());
        }

        int idSecao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Secao secaoSelecionado = null;
        for (Secao secao : secoes) {
            if (secao.getIdSecao() == idSecao) {
                secaoSelecionado = secao;
                break; // Adicionado para sair do loop quando a seção for encontrada
            }
        }

        if (secaoSelecionado == null) {
            MenuFormatter.msgTerminalERROR("ID de seção inválido");
            return; // Adicionado para interromper a execução
        }

        System.out.println("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o assento: ");
        int assento = scanner.nextInt();
        System.out.println("Digite a forma de pagamento: ");
        String formaPagamento = scanner.nextLine();

        VendaController.inserirVenda(new Venda(nome, assento, formaPagamento, secaoSelecionado));
}


    // metodos para remover
    public static void menuRemoverEndereco() {
        MenuFormatter.titulo("REMOVER - ENDEREÇO");

        List<Endereco> enderecos = EnderecoController.listarTodosRegistros();
        if (enderecos.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum endereço disponível. Por favor, insira um endereço antes.");
            return;
        }

        System.out.println("Escolha um Endereço pelo ID: ");
        for (Endereco endereco : enderecos){
            System.out.println(endereco.getIdEndereco() + ": " + endereco.getCidade() + ": " + endereco.getBairro() + ": " + endereco.getCidade());
        }

        int idEndereco = scanner.nextInt();
        Endereco enderecoSelecionado = null;
        for (Endereco endereco : enderecos) {
            if (endereco.getIdEndereco() == idEndereco) {
                enderecoSelecionado = endereco;
            }
        }

        if (enderecoSelecionado == null) {
            MenuFormatter.msgTerminalERROR("ID de endeço inválido.");
            return;
        }

        EnderecoController.excluirRegistro(idEndereco);
    }

    public static void menuRemoverCinema(){

        MenuFormatter.titulo("REMOVER - CINEMA");

        List<Cinema> cinemas = CinemaController.listarTodosRegistros();
        if (cinemas.isEmpty()) {
            MenuFormatter.msgTerminalERROR("Nenhum cinema disponível. Por favor, insira um cinema antes.");
            return;
        }

        System.out.println("Escolha um Cinema pelo ID: ");
        for (Cinema cinema : cinemas){
            System.out.println(cinema.getIdCinema() + ": " + cinema.getNomeCinema());
        }

        int idCinema = scanner.nextInt();
        Cinema cinemaSelecionado = null;
        for (Cinema cinema : cinemas) {
            if (cinema.getIdCinema() == idCinema) {
                cinemaSelecionado = cinema;
            }
        }

        if (cinemaSelecionado == null) {
            MenuFormatter.msgTerminalERROR("ID de cinema inválido.");
            return;
        }

        CinemaController.excluirRegistro(idCinema);

    }

    public static void menuRemoverFilme() {

        MenuFormatter.titulo("REMOVER - FILME");

        List<Filme> filmes = FilmeController.listarTodosRegistros();
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme disponível. Por favor, insira um filme antes.");
            return;
        }
    
        System.out.println("Escolha um Filme pelo ID:");
        for (Filme filme : filmes) {
            System.out.println(filme.getIdFilme() + ": " + filme.getNomeFilme());
        }
    
        int idFilme = scanner.nextInt();
        Filme filmeSelecionado = null;
        for (Filme filme : filmes) {
            if (filme.getIdFilme() == idFilme) {
                filmeSelecionado = filme;
            }
        }
    
        if (filmeSelecionado == null) {
            System.out.println("ID de filme inválido.");
            return;
        }
    
        FilmeController.excluirRegistro(idFilme);
    }

    public static void menuRemoverSecao() {

        MenuFormatter.titulo("REMOVER - SEÇÃO");

        List<Secao> secoes = SecaoController.listarTodosRegistros();
        if (secoes.isEmpty()) {
            System.out.println("Nenhuma sessão disponível. Por favor, insira uma sessão antes.");
            return;
        }
    
        System.out.println("Escolha uma Sessão pelo ID:");
        for (Secao secao : secoes) {
            System.out.println(secao.getIdSecao() + ": " + secao.getCinema().getNomeCinema() + " - " + secao.getFilme().getNomeFilme() + " - " + secao.getHorario());
        }
    
        int idSecao = scanner.nextInt();
        Secao secaoSelecionada = null;
        for (Secao secao : secoes) {
            if (secao.getIdSecao() == idSecao) {
                secaoSelecionada = secao;
            }
        }
    
        if (secaoSelecionada == null) {
            System.out.println("ID de sessão inválido.");
            return;
        }
    
        SecaoController.excluirRegistro(idSecao);
    }

    public static void menuRemoverVenda() {

        MenuFormatter.titulo("REMOVER - VENDA");

        List<Venda> vendas = VendaController.listarTodosRegistros();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda disponível. Por favor, insira uma venda antes.");
            return;
        }
    
        System.out.println("Escolha uma Venda pelo ID:");
        for (Venda venda : vendas) {
            System.out.println(venda.getIdVenda() + ": Cliente: " + venda.getNomeCliente() + ", Sessão: " + venda.getSecao().getHorario() + ", Assento: " + venda.getAssento());
        }
    
        int idVenda = scanner.nextInt();
        Venda vendaSelecionada = null;
        for (Venda venda : vendas) {
            if (venda.getIdVenda() == idVenda) {
                vendaSelecionada = venda;
            }
        }
    
        if (vendaSelecionada == null) {
            System.out.println("ID de venda inválido.");
            return;
        }
    
       VendaController.excluirVenda(idVenda);
    }
    


    // metodos para exibir o Relatorio
    public static void exibirRelatorio() {
        LinkedList<String> cinemaEndereco = Relatorio.listarCinemaEndereco();
        LinkedList<String> informacoes = Relatorio.listarInformacoes();
        LinkedList<String> soma = Relatorio.listarSoma();

        
        System.out.println("=== Cinema e Endereço ===");
        if (cinemaEndereco != null && !cinemaEndereco.isEmpty()) {
            for (String registro : cinemaEndereco) {
                System.out.println(registro);
            }
        } else {
            System.out.println("Nenhum cinema encontrado.");
        }

        
        System.out.println("\n=== Informações ===");
        if (informacoes != null && !informacoes.isEmpty()) {
            for (String registro : informacoes) {
                System.out.println(registro);
            }
        } else {
            System.out.println("Nenhuma informação encontrada.");
        }

    
        System.out.println("\n=== Soma dos Ingressos ===");
        if (soma != null && !soma.isEmpty()) {
            for (String registro : soma) {
                System.out.println(registro);
            }
        } else {
            System.out.println("Nenhuma soma encontrada.");
        }
    }


    // metodos para alterar
    public static void menuAlterarCinema() {
        if (CinemaController.contarRegistros() == 0) {
            MenuFormatter.msgTerminalERROR("Nenhum Cinema disponível.");
            return;
        }

        for (Cinema cinema : CinemaController.listarTodosRegistros()){
            System.out.println("ID: " + cinema.getIdCinema() + ", Nome Cinema: " + cinema.getNomeCinema() + ", ID do Endereço: " 
                            + cinema.getEndereco().getIdEndereco() + ", Cidade: " + cinema.getEndereco().getCidade());
        }
        System.out.print("Escolha o Cinema que deseja alterar pelo ID: ");
        int idEndereco = scanner.nextInt();
        
        Cinema cinemaSelecionado = CinemaController.buscarRegistroPorId(idEndereco);
        if (cinemaSelecionado == null) {
            System.out.println("ID de Cinema inválido.");
            return;
        }

        System.out.print("Nome do Cinema: ");
        cinemaSelecionado.setNomeCinema(scanner.nextLine());

        System.out.print("ID do Endereço: ");
        cinemaSelecionado.setEndereco(EnderecoController.buscarRegistroPorId(scanner.nextInt()));

        if (!EnderecoController.existeRegistro(cinemaSelecionado.getEndereco().getIdEndereco())) {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado.");
            return;
        }
        
        CinemaController.atualizarRegistro(cinemaSelecionado);
    }

    public static void menuAlterarEndereco() {
        if (EnderecoController.contarRegistros() == 0) {
            MenuFormatter.msgTerminalERROR("Nenhum Endereço disponível.");
            return;
        }

        for (Endereco endereco : EnderecoController.listarTodosRegistros()){
            System.out.println("ID: " + endereco.getIdEndereco() + ", Número: " + endereco.getNumero() + ", Rua: " + endereco.getRua() 
                        + ", Bairro: " + endereco.getBairro() + ", Cidade: " + endereco.getCidade() + ", UF: " + endereco.getUf());
        }
        System.out.print("Escolha o Endereço que deseja alterar pelo ID: ");
        int idEndereco = scanner.nextInt();
        
        Endereco enderecoSelecionado = EnderecoController.buscarRegistroPorId(idEndereco);
        if (enderecoSelecionado == null) {
            System.out.println("ID de Endereço inválido.");
            return;
        }

        System.out.print("Número do Endereço: ");
        enderecoSelecionado.setNumero(scanner.nextInt());

        System.out.print("Rua do Endereço: ");
        enderecoSelecionado.setRua(scanner.nextLine());

        System.out.print("Bairro do Endereço: ");
        enderecoSelecionado.setBairro(scanner.nextLine());

        System.out.print("Cidade do Endereço: ");
        enderecoSelecionado.setCidade(scanner.nextLine());

        System.out.print("UF do Endereço: ");
        enderecoSelecionado.setUf(scanner.next());
        
        EnderecoController.atualizarRegistro(enderecoSelecionado);
    }

    public static void menuAlterarFilme() {              
        if (FilmeController.contarRegistros() == 0) {
            MenuFormatter.msgTerminalERROR("Nenhum filme disponível.");
            return;
        }

        for (Filme filme : FilmeController.listarTodosRegistros()){
            System.out.println(filme.getIdFilme() + "  - Nome Filme: " + filme.getNomeFilme() + "  - Preço: " + filme.getPreco());
        }
        System.out.print("Escolha o Filme que deseja alterar pelo ID: ");
        int idFilme = scanner.nextInt();
        
        Filme filmeSelecionado = FilmeController.buscarRegistroPorId(idFilme);
        if (filmeSelecionado == null) {
            System.out.println("ID de Filme inválido.");
            return;
        }

        System.out.print("Nome do Filme: ");
        filmeSelecionado.setNomeFilme(scanner.nextLine());

        System.out.print("Preço do Filme: ");
        filmeSelecionado.setPreco(scanner.nextDouble());

        FilmeController.atualizarRegistro(filmeSelecionado);
    }

    public static void menuAlterarSecao() {
        if (SecaoController.contarRegistros() == 0) {
            MenuFormatter.msgTerminalERROR("Nenhuma Seção disponível.");
            return;
        }

        for (Secao secao : SecaoController.listarTodosRegistros()){
            System.out.println("ID: " + secao.getIdSecao() + ", Horário: " + secao.getHorario() + ", Quantidade Assentos: " + secao.getQtdAssentos()
                            + ", ID Cinema: " + secao.getCinema().getIdCinema() + ", ID Filme: " + secao.getFilme().getIdFilme());
        }
        System.out.print("Escolha a Seção que deseja alterar pelo ID: ");
        int idSecao = scanner.nextInt();
        
        Secao secaoSelecionado = SecaoController.buscarRegistroPorId(idSecao);
        if (secaoSelecionado == null) {
            System.out.println("ID de Seção inválido.");
            return;
        }

        System.out.print("Horario do Filme (formato: yyyy-MM-dd HH:mm:ss): ");
        String horarioStr = scanner.nextLine();
        secaoSelecionado.setHorario(Timestamp.valueOf(horarioStr));

        System.out.print("Quantidade de Assentos: ");
        secaoSelecionado.setQtdAssentos(scanner.nextInt());

        System.out.print("ID do Cinema: ");
        secaoSelecionado.setCinema(CinemaController.buscarRegistroPorId(scanner.nextInt()));

        if (!CinemaController.existeRegistro(secaoSelecionado.getCinema().getIdCinema())) {
            MenuFormatter.msgTerminalERROR("Cinema não encontrado");
            return;
        }

        System.out.print("ID do Filme: ");
        secaoSelecionado.setFilme(FilmeController.buscarRegistroPorId(scanner.nextInt()));

        if (!FilmeController.existeRegistro(secaoSelecionado.getFilme().getIdFilme())) {
            MenuFormatter.msgTerminalERROR("Filme não encontrado.");
            return;
        }

        SecaoController.atualizarRegistro(secaoSelecionado);
    }

    public static void menuAlterarVenda() {
        if (VendaController.contarRegistros() == 0) {
            MenuFormatter.msgTerminalERROR("Nenhuma Venda disponível.");
            return;
        }

        for (Venda venda : VendaController.listarTodosRegistros()){
            System.out.println("ID: " + venda.getIdVenda() + ", Cliente: " + venda.getNomeCliente() + ", Assento: " + venda.getAssento()
                            + ", Forma de Pagamento: " + venda.getFormaPagamento() + ", ID Seção: " + venda.getSecao().getIdSecao());
        }
        System.out.print("Escolha a Venda que deseja alterar pelo ID: ");
        int idVenda = scanner.nextInt();
        
        Venda vendaSelacionada = VendaController.buscarVendaPorId(idVenda);
        if (vendaSelacionada == null) {
            System.out.println("ID de Venda inválido.");
            return;
        }

        System.out.print("Nome do Cliente: ");
        vendaSelacionada.setNomeCliente(scanner.nextLine());

        System.out.print("Assento: ");
        vendaSelacionada.setAssento(scanner.nextInt());

        System.out.print("ID da Seção: ");
        vendaSelacionada.setSecao(SecaoController.buscarRegistroPorId(scanner.nextInt()));

        if (!SecaoController.existeRegistro(vendaSelacionada.getSecao().getIdSecao())) {
            MenuFormatter.msgTerminalERROR("Seção não encontrada");
            return;
        }

        VendaController.atualizarVenda(vendaSelacionada);
    }

    // SplashScreen
    public static void splashScreen() {
        String[] nomeTabelas = {"Endereços", "Cinemas", "Filmes", "Seções", "Vendas"};
        String[] qtdRegistrosTabelas = {
            Integer.toString(EnderecoController.contarRegistros()),
            Integer.toString(CinemaController.contarRegistros()),
            Integer.toString(FilmeController.contarRegistros()),
            Integer.toString(SecaoController.contarRegistros()),
            Integer.toString(VendaController.contarRegistros())
        };

        MenuFormatter.titulo("Venda de Seções");
        MenuFormatter.centralizar(" // Total de Registros Existentes \\\\ ");
        System.out.println(MenuFormatter.criaTabelaCompleta(nomeTabelas, qtdRegistrosTabelas));

        System.out.println("\n");

        System.out.println("Craido por:                      |");
        System.out.println("  Davi Tambara Rodrigues         |    Disciplina:  BANCO DE DADOS");
        System.out.println("  Samuel Eduardo Rocha de Souza  |    Professor:   Howard Roatti");
        System.out.println("  Thiago Holz Coutinho           |");

        System.out.println("");
        MenuFormatter.centralizar("2024/2");

        MenuFormatter.linha();
    }
}
