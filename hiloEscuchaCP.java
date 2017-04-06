import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.io.IOException;
import java.util.Scanner;


public class hiloEscuchaCP extends Thread {
	
	String arch;
	String port;
	
	hiloEscuchaCP(String puerto, String archivo){
		arch = archivo;
		port = puerto;
	}
	
	public void run(){
	
		//TCP
	
		try{
			
			int puerto = Integer.parseInt(port);
			String archivo = arch;
			ServerSocket escucha = new ServerSocket(puerto);
		
			while(true){
				System.out.println("Esperando nueva conexión TCP...");
				Socket s = escucha.accept();
				System.out.println("Cliente conectado.");
	
				hilo hiloCliente = new hilo(s,archivo);
				hiloCliente.start();		
			}
		}catch(Exception e){
			System.out.println("error:"+e.getMessage());
		}
	}	
}
