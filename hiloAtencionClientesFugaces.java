import java.net.*;
import java.util.*;
import java.io.*;


public class hiloAtencionClientesFugaces extends Thread {
	
	DatagramSocket datagramsocket;
	String archivo;
	DatagramPacket paquete;
	
	hiloAtencionClientesFugaces(DatagramSocket datagramsocket, String archivo, DatagramPacket paquete){
		this.datagramsocket = datagramsocket;
		this.archivo= archivo;
		this.paquete= paquete;
	}

	
	
	public void run(){

	
		try{
		//creacion del socket 
		
		 byte[] outDataBytes = new byte[1024];
		 int outDataLength= outDataBytes.length;
		
		 
		
		InetAddress clientAddress = paquete.getAddress();
		
		int clientPort = paquete.getPort();
		
	    String proverbio= hiloEscuchaCF.file.readLine();
		
		outDataBytes= proverbio.getBytes();  //convierto texto a bytes
		outDataLength= outDataBytes.length;
		
	    DatagramPacket outDatagramPacket = new DatagramPacket(outDataBytes,outDataLength,clientAddress,clientPort);
		
		datagramsocket.send(outDatagramPacket); //envia respuesta al cliente emisor 
		
		}catch (Exception e){
			System.out.println("error:"+e.getMessage());
 
		    }
		}
		
		
		
	}
	
	

