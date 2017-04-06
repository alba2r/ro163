import java.net.*;
import java.io.*;
import java.util.*;

public class hilo extends Thread {
	
	static int i=1;
	String recibido; 
	String archivo;
	DataInputStream in;
	DataOutputStream out;

	Socket servidor=new Socket();
	//constructor clase hilo
	hilo(Socket serv, String archivo){
		this.archivo = archivo;
		servidor=serv;
	}
	
	public void run(){
		FileReader f = null;
		try{
			PrintWriter out = new PrintWriter(servidor.getOutputStream(), true);
			try{			
				f = new FileReader(archivo);
				BufferedReader b = new BufferedReader(f);
				String proverbio;
				out.println("Pulse una tecla si desea recibir proverbios.");
				out.println("");
												
				while(true){
					try{
						//lectura de fichero 
						if((proverbio = b.readLine()) != null){					
							out.println(proverbio);
							out.println("Pulse una tecla si desea recibir más proverbios.");
						}else{
							out.println("");
							out.println("Ya no quedan más proverbios.");
							b.close();	
						}						
					}catch(IOException e){										
					}
					Thread.sleep(1000);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
		     }
		
		}catch(SocketException m){
			System.out.println("Error:"+m);
		}catch(Exception e){
			System.out.println("Error:"+e);
		}
	}
}