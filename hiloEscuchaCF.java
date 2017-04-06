import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class hiloEscuchaCF extends Thread{
	
	String arch;
	String port;
	

	
	hiloEscuchaCF(String puerto, String archivo){
		arch = archivo;
		port = puerto;
	}
	
	public static BufferedReader file;
	
	public void run(){
	

	
	byte intDataBytes[]= new byte[1000];
	int intDataLength=1000;
	byte outDataBytes[]= new byte[1000];
	int outDataLength=1000;
    
	
	//UDP
	
	try{
		
		FileReader lectura = new FileReader(arch);
		//un buffer que gestiona todas
		BufferedReader file = new BufferedReader(lectura,1024);
		this.file= file;
		
		
		
	
	DatagramSocket datagramSocket =  new DatagramSocket(Integer.parseInt(port));
	DatagramPacket inDatagramPacket = new DatagramPacket(intDataBytes,intDataLength);
	
	
	
	while (true){  
	System.out.println("Esperando paquete UDP...");
	datagramSocket.receive(inDatagramPacket); //recive las peticiones
	hiloAtencionClientesFugaces hilocf = new hiloAtencionClientesFugaces(datagramSocket,arch, inDatagramPacket );
	hilocf.start();
	
	}//fin while
	
	
	
	} catch (Exception e){
		System.out.println("error:"+e.getMessage());

		}
		
  }
	
	

}
