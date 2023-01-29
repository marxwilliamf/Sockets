import java.io.*;
import java.net.*;

public class Servidor {
	
	//args[0] = porta
	//args[1] = arquivo //
	//args[2] = length
    public static void main(String[] args) throws IOException {
		int port = 1024;
		if(args[0]!=null && Integer.parseInt(args[0]) >= 1){
			port = Integer.parseInt(args[0]);
		}
		String argNomeArquivoParaTranferir = null;
		if(args[1]!=null && !args[1].isEmpty()){
			argNomeArquivoParaTranferir = args[1];
		}
		int megas = 32;
		if(args[2]!=null && Integer.parseInt(args[2]) >= 1){
			megas = Integer.parseInt(args[2]);
		}

        // Cria um socket para escutar as conexões na porta 12345
        ServerSocket servidor = new ServerSocket(port);
        System.out.println("Servidor iniciado!");

        while (true) {
            // Aceita uma conexão do cliente
            Socket socket = servidor.accept();
            System.out.println("Conexão estabelecida com o cliente: " + socket.getInetAddress().getHostAddress());

            try(
				FileInputStream streamEntradaLeituraParaEnvio = new FileInputStream(argNomeArquivoParaTranferir);  // Cria uma stream para enviar o arquivo
            	OutputStream saidaSocket = socket.getOutputStream();
			) {
	            // Envia o arquivo para o cliente
	            byte[] buffer = new byte[1024*1024*1024*megas];
	            int bytesLidos;
	            while ((bytesLidos = streamEntradaLeituraParaEnvio.read(buffer)) > 0) {
	            	saidaSocket.write(buffer, 0, bytesLidos);            	
	            
	            	System.out.println("Arquivo enviado com sucesso!");
	            }
            } catch(IOException e) {
            	e.printStackTrace();
            }
        }
    }
}
