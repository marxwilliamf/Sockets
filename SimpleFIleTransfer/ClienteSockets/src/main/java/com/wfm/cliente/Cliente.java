package com.wfm.cliente;

import java.io.*;
import java.net.*;

public class Cliente {

	private String host = "localhost";
	private int port = 1024;
	private int megas = 32;
	File arquivo;

	public Cliente(String host, int port, int megas) {
		this.host = host;
		this.port = port;
		this.megas = megas;
	}

	public void baixaArquivo(String nomeArquivoBaixar){
		try(
			Socket socket = new Socket(this.host, this.port);
			InputStream fonteArquivoSocket = socket.getInputStream(); // Cria uma stream para receber o arquivo
		) {
			if(fonteArquivoSocket!=null){
				System.out.println("inputSocket conectado");
			} else {
				System.out.println("Input Socket Desconectado");
			}
			System.out.println("Conexão estabelecida com o servidor!");
			this.arquivo = criaEVerificaEscritaArquivo(nomeArquivoBaixar);
			if(this.arquivo != null){
				salvaArquivo(fonteArquivoSocket);
				System.out.println("Arquivo baixado com sucesso");
			} else{
				System.out.println("Erro ao baixar arquivo");
			}
		} catch(IOException e) {
			System.out.println("Erro ao baixar arquivo");	
			e.printStackTrace();
		}
	}
	public File criaEVerificaEscritaArquivo(String nomeArquivoBaixar){
		File arq;
		try{
			arq = new File(nomeArquivoBaixar);
			// if(arq.canWrite()){
			 	return arq;
			// } else{
			// 	System.out.println("Arquivo nao pode ser escrito.");
			// }
		} catch(NullPointerException e){
			System.out.println("O arquivo não pode ser escrito. Verifique as permissões do arquivo.");
			e.printStackTrace();
		}
		return null;
}

	public void salvaArquivo(InputStream fonteArquivoSocket) {
		try (FileOutputStream outputStreamFile = new FileOutputStream(this.arquivo)) {
			System.out.println("Salva Arquivo");
			// Recebe o arquivo do servidor
			byte[] buffer = new byte[1024 * 1024 * 1024 * megas];
			int bytesLidos;
			while ((bytesLidos = fonteArquivoSocket.read(buffer)) > 0) {
				System.out.println("Write ..");
				outputStreamFile.write(buffer, 0, bytesLidos);
			}
		} catch(SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMegas() {
		return megas;
	}

	public void setMegas(int megas) {
		this.megas = megas;
	}

}