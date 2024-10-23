package utils;

import java.io.IOException;


public class MenuFormatter {
    private static int numEspacamentoUni = 16;
    private static String caracteres = "-="; // Mantenha 2 Caracteres

	public static void linha() {
		String msg = "";
		
		for (int i = 0; i < (2*numEspacamentoUni + 1); i++) {
			msg += caracteres;
		}
		
		System.out.println(msg);
	}

    public static String getLinha(String caracteres) {
        String msg = "";
		
		for (int i = 0; i < (2*numEspacamentoUni + 1); i++) {
			msg += caracteres;
		}

        return msg;
    }
	
	public static void titulo(String texto) {
		linha();
		centralizar(texto);
		linha();
	}
    
    public static void centralizar(String texto) {
        String strEspacamento = "";
        int tamanhoEspacamento = numEspacamentoUni * 2 - (texto.length() / 2); // Dobra do Tamanho do Espacamento
        
        for (int i = 0; i < tamanhoEspacamento; i++) {
            strEspacamento += " ";

        }
        System.out.println(strEspacamento + " " +  texto);
    }    

	public static String centralizarItem (String texto) {
		String strEspacamento = "";
		int tamanhoEspacamento = MenuFormatter.numEspacamentoUni - texto.length();
		
		for (int i = 0; i < tamanhoEspacamento; i++) {
			strEspacamento += " ";			
		}

		return strEspacamento + texto;
	} 
	
	public static String centralizarItem (String texto, int tamanho) {
		String strEspacamento = "";
		int tamanhoEspacamento;
		
		if (texto.length() % 2 == 0) {
			tamanhoEspacamento = (tamanho - texto.length()) / 2;
			
		} else {
			texto+= " ";
			tamanhoEspacamento = (tamanho - texto.length()) / 2;
		}	
		
		for (int i = 0; i < tamanhoEspacamento; i++) {
			strEspacamento += " ";			
		}
		
		return strEspacamento + texto + strEspacamento;
	}
	
    public static String criarLinhaTabela (String[] valores) {
        String corpo = "";

        for (int c = 0; c < valores.length; c++) {
            corpo += MenuFormatter.centralizarItem(valores[c]);
        }
        
        return corpo;
    }

    public static String criarLinhaTabela (String[] valores, int tamanho) {
        String corpo = "";

        for (int c = 0; c < valores.length; c++) {
            if (c == 0) {
                corpo += MenuFormatter.centralizarItem(valores[c], tamanho-2);
            } else {
                corpo += MenuFormatter.centralizarItem(valores[c], tamanho);
            }
        }

        return corpo;
    }

    public static String criaTabelaCompleta(String[] colunas, String[] valores) {
        String tabela = "";
        tabela += MenuFormatter.criarLinhaTabela(colunas, numEspacamentoUni-2) 
                + "\n" + MenuFormatter.getLinha("--")
                + "\n" + MenuFormatter.criarLinhaTabela(valores, numEspacamentoUni-2);
        return tabela;
    }

    
    public static void limparTerminal() throws IOException, InterruptedException {
        //Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime();
        }
    }

    public static void msgTerminalINFO(String msg) {
		linha();
    	centralizar("[INFO] - " + msg);
    	linha();
    }

    public static void msgTerminalERROR(String msg) {
		linha();
    	centralizar("[ERRO] - " + msg);
    	linha();
    }

	public static void delay(int tempo) {
		try {
	        Thread.sleep(tempo * 1000);  // transforma o tempo informado em milissegundos
	    } catch (InterruptedException e) {
	        // Trata a exceção caso a thread seja interrompida durante o sleep
	        e.printStackTrace();
	    }
	}

    // Número do Espaçamento Unitário
    public static int getNumEspacamentoUni() {
        return numEspacamentoUni;
    }
    public static void setNumEspacamentoUni(int numEspacamentoUni) {
        MenuFormatter.numEspacamentoUni = numEspacamentoUni;
    }

    // Caracteres
    public static String getCaracteres() {
        return caracteres;
    }
    public static void setCaracteres(String caracteres) {
        MenuFormatter.caracteres = caracteres;
    }

}