package com.wfm.servidor;

import java.io.*;
import java.net.*;

public class Servidor {
	
	//args[0] = porta
	//args[1] = arquivo //
	//args[2] = length
	private int port = 1024;
	private int megas = 32;

	public Servidor(int port, int megas) {
		this.port = port;
		this.megas = megas;
	}

	public void enviaArquivo(String nomeArquivoParaDownload){
        // Cria um socket para escutar as conexões na porta 12345
        try(
			ServerSocket servidor = new ServerSocket(this.port);
		) {
			System.out.println("Servidor iniciado!");
			while (true) {
				// Aceita uma conexão do cliente
				Socket socket = servidor.accept();
				System.out.println("Conexão estabelecida com o cliente: " + socket.getInetAddress().getHostAddress());

				leArquivo(nomeArquivoParaDownload, socket);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
    }

	public void leArquivo(String nomeArquivoParaDownload, Socket socket){
		try(
			FileInputStream streamEntradaLeituraParaEnvio = new FileInputStream(nomeArquivoParaDownload);  // Cria uma stream para enviar o arquivo
		) {
			enviaArquivoSocket(socket, streamEntradaLeituraParaEnvio);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void enviaArquivoSocket(Socket socket, FileInputStream streamEntradaLeituraParaEnvio) {
		try(OutputStream saidaSocket = socket.getOutputStream();) {
			byte[] buffer = new byte[1024*1024*1024*this.megas];
			int bytesLidos;
			while ((bytesLidos = streamEntradaLeituraParaEnvio.read(buffer)) > 0) {
				saidaSocket.write(buffer, 0, bytesLidos);            	
			
				System.out.println("Arquivo enviado com sucesso!");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
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
