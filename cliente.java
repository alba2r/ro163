import java.io.*;
import java.net.*;
import java.util.*;

public class cliente {

	public static void main(String[] args){
		
		String tipo_cliente;
		DatagramSocket cliente_udp;
		Socket cliente_tcp;

		String enviar = "Hola";
		String proverbio;
		
		byte inDatabytes[] = new byte[1024];
		int inDataLength =1024;
		String inDataString;
		
		try{
			
			InetAddress host = InetAddress.getByName(args[1]);

			int puerto= Integer.parseInt(args[2]);
			tipo_cliente = args[0];
			
			if(tipo_cliente.equals("-tcp")){
				System.out.println("Conectado cliente a servidor.");
				//se crea socket para cliente persistente
				cliente_tcp = new Socket(host,puerto);
				//creamos la entrada y salida de flujo
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente_tcp.getInputStream()));
				PrintWriter salida = new PrintWriter(cliente_tcp.getOutputStream(), true);
				
				while(true){
					//lee el proverbio que le env�a la clase hilo 
					proverbio = entrada.readLine();
					String mensaje = entrada.readLine();
					//imprime porverbio por pantalla y espera que se pulse una tecla
					System.out.println("Proverbio: " + proverbio);
					System.out.println(mensaje);
					Scanner sc = new Scanner(System.in);
					String teclado = sc.nextLine();
					salida.println(teclado);
 
					Thread.sleep(1000);
				
				 } 
				
				
				
			}else if(tipo_cliente.equals("-udp")){
				
				//creación del socket
				cliente_udp = new DatagramSocket(puerto);
				
				byte[] saludo = enviar.getBytes();
				
				//genero paquete
				DatagramPacket inDatagramPacket = new DatagramPacket(inDatabytes, inDataLength);
			    DatagramPacket outDatagramPacket = new DatagramPacket(saludo, saludo.length,host,puerto);
				
			    //envío paquete de petición
			    cliente_udp.send(outDatagramPacket); 
			    
			   //cliente recibe la respuesta del servidor
			    cliente_udp.receive(inDatagramPacket); 
			    inDatabytes= inDatagramPacket.getData();
			    inDataString = new String(inDatabytes,0,inDataLength);
				System.out.println(inDataString);
				
				cliente_udp.close();
				
				
			}else{
	
			}
			
		}catch(Exception e){
			System.out.println("error en cliente:"+e.getMessage());
		}
	}
}
