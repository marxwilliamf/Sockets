package com.wfm;

import com.wfm.servidor.Servidor;

public class Main {
    public static void main(String[] args) {

    	//args[1] = port
        //args[2] = arquivo
		//args[3] = length	
		int port = 1024;
		if(args.length > 1 && args[1].matches("\\d+")) {
			int temp = Integer.parseInt(args[1]);
			if(temp >= 1){
				port = temp;
			}
		}
		String nomeArquivoParaEnviar = null;
		if(args[2].length() > 2 && !args[2].isEmpty()){
			nomeArquivoParaEnviar = args[2];
		}
		int megas = 32;
		if(args.length > 3 && args[1].matches("\\d+")) {
			int temp = Integer.parseInt(args[3]);
			if(temp >= 1){
				port = temp;
			}
		}

		Servidor servidor = new Servidor(port, megas);
		servidor.enviaArquivo(nomeArquivoParaEnviar);
       
        
    }
}

