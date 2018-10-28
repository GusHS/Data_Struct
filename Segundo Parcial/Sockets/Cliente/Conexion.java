import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Conexion {
		
	private Socket socket;
	
	private BufferedReader bufferEntrada;
	private PrintWriter    bufferSalida;
	

	public Socket establecerConexion(){
		try{
			//Establecer conexion con el Server: socket() y connect()
			socket = new Socket(InetAddress.getByName("localhost"),7000);
			//socket = new Socket(InetAddress.getByName("10.0.2.15"),7000);
			bufferEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferSalida  = new PrintWriter(socket.getOutputStream(),true);
			bufferSalida.flush();

		}
		catch(IOException ioe){
			System.out.println("Error: "+ioe);
		}
		return socket;
	}
	
	public void cerrarConexion(){
		try{
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		}
		catch(IOException ioe){
			System.out.println("Error: "+ioe);
		}
	}
	
	public String recibirDatos(){
		String datos="";
		try{
			datos = bufferEntrada.readLine();
		}
		catch(IOException ioe){
			System.out.println("Error: "+ioe);
			datos = "Error: "+ioe;
		}
		return datos;
	}
	
	public void enviarDatos(String mensaje){
		bufferSalida.println(mensaje);
	}
	public static void main(String args[]){
		Conexion conexion = new Conexion();
	}
}