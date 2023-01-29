package com.wfm;

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
			System.out.println("Conexão estabelecida com o servidor!");
			verificaEscritaArquivo(nomeArquivoBaixar);
			salvaArquivo(fonteArquivoSocket);
				
			System.out.println("Arquivo baixado com sucesso");
		} catch(IOException e) {	
			e.printStackTrace();
		}
		System.out.println("Arquivo recebido com sucesso!");
	}
	public void verificaEscritaArquivo(String nomeArquivoBaixar){
		arquivo = new File(nomeArquivoBaixar);
	if (!arquivo.canWrite()) {
		System.out.println("O arquivo não pode ser escrito. Verifique as permissões do arquivo.");
		return;
	}
}

	public void salvaArquivo(InputStream fonteArquivoSocket) {
		try (FileOutputStream outputStreamFile = new FileOutputStream(arquivo)) {
			// Recebe o arquivo do servidor
			byte[] buffer = new byte[1024 * 1024 * 1024 * megas];
			int bytesLidos;
			while ((bytesLidos = fonteArquivoSocket.read(buffer)) > 0) {
				outputStreamFile.write(buffer, 0, bytesLidos);
			}
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
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