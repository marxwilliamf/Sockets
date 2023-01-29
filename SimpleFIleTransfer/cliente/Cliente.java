import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {

    	//args[0] = host
    	//args[1] = port
        //args[2] = arquivo
		//args[3] = length
    	String host = "localhost";
		if(args[0]!=null && !args[0].isEmpty()){
			host = args[0];
		}
		
		int port = 1024;
		if(args[1]!=null && Integer.parseInt(args[1]) >= 1){
			port = Integer.parseInt(args[1]);
		}
		String argNomeArquivoParaTranferir = null;
		if(args[2]!=null && !args[2].isEmpty()){
			argNomeArquivoParaTranferir = args[2];
		}
		int megas = 32;
		if(args[3]!=null && Integer.parseInt(args[3]) >= 1){
			megas = Integer.parseInt(args[3]);
		}
		
        System.out.println("Conexão estabelecida com o servidor!");
        
        try(
			Socket socket = new Socket(host, port);
			InputStream entradaArquivoRecebimento = socket.getInputStream(); // Cria uma stream para receber o arquivo
	        FileOutputStream outputStreamFile = new FileOutputStream(argNomeArquivoParaTranferir);
			) {
	        // Recebe o arquivo do servidor
	        byte[] buffer = new byte[1024*1024*1024*megas];
	        int bytesLidos;
	        while ((bytesLidos = entradaArquivoRecebimento.read(buffer)) > 0) {
	        	outputStreamFile.write(buffer, 0, bytesLidos);
	        }
	        
	        System.out.println("Arquivo baixado com sucesso");
        } catch(IOException e) {	
        	e.printStackTrace();
        }
        
        System.out.println("Arquivo recebido com sucesso!");
    }
}