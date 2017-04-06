
import java.net.*;
import java.io.IOException;
import java.util.Scanner;


public class servidor {
	
	public static void main(String[] args) throws IOException {
	
	try{	
		String puerto = args[0];
		String archivo = args[1];
		
		hiloEscuchaCF hiloescCF = new hiloEscuchaCF(puerto,archivo);
		hiloescCF.start();
		
		hiloEscuchaCP hiloescCP = new hiloEscuchaCP(puerto,archivo);
		hiloescCP.start();
		
	   }
	catch(Exception e){
		   System.out.println("error:"+e.getMessage());
	   }
}

}