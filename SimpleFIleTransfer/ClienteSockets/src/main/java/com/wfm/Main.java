package com.wfm;

public class Main {
    public static void main(String[] args) {
    	//args[0] = host
    	//args[1] = port
        //args[2] = arquivo
		//args[3] = length
    	String host = "localhost";
		if(args.length > 0 && !args[0].isEmpty()){
			host = args[0];
		}
		
		int port = 1024;
		if(args.length > 1 && args[1].matches("\\d+")) {
			int temp = Integer.parseInt(args[1]);
			if(temp >= 1){
				port = temp;
			}
		}
		String nomeArquivoParaBaixar = null;
		if(args[2].length() > 2 && !args[2].isEmpty()){
			nomeArquivoParaBaixar = args[2];
		}
		int megas = 32;
		if(args.length > 3 && args[1].matches("\\d+")) {
			int temp = Integer.parseInt(args[3]);
			if(temp >= 1){
				port = temp;
			}
		}

		Cliente cliente = new Cliente(host, port, megas);
        cliente.baixaArquivo(nomeArquivoParaBaixar);
    }
}