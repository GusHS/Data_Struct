import javax.swing.*;
import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server extends JFrame
{
	private JTextArea taDatos;
	private JPanel    panel;
	
	private ServerSocket server;
	private Socket       socket;

	private BufferedReader bufferEntrada;
	private PrintWriter    bufferSalida;
	private OutputStream   bufferImagen;

	InputStream inputStream;
	
	private AlbumsADLL albumsAD = new AlbumsADLL();

	public Server()
	{
		super("Server");
		taDatos = new JTextArea(15,20);
		panel   = new JPanel();
		
		panel.setLayout(new GridLayout(1,1));
		panel.add(new JScrollPane(taDatos));
		
		setLayout(new GridLayout(1,1));
		add(panel);
		setSize(300,300);
		setVisible(true);
	}
	
	private void iniciarBuffers()
	{
		try
		{
			bufferEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferSalida  = new PrintWriter(socket.getOutputStream(),true);
			bufferSalida.flush();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}
	}
	
	private void cerrarConexion()
	{
		try
		{
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}
	}
	
	private String recibirDatos()
	{
		String datos="";
		
		try
		{
			datos = bufferEntrada.readLine();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
			datos = "Error: "+ioe;
		}
		
		return datos;
	}
	
	private void enviarDatos(String mensaje)
	{
		bufferSalida.println(mensaje);
	}
	
	private void inicializarServer()
	{
		String transaccion="";
		String mensajeServer="Servidor Inicializado";
		int numeroAlbums, noImagen;
		String strImagen;
		try
		{
			// 1. Iniciar Server
			server = new ServerSocket(7000,5);
			
			while(true)
			{
				taDatos.append("\nEsperando solicitud de conexion de un cliente\n");
				
				// 2. Aceptar peticion de conexion por parte de un Cliente
				socket = server.accept();
				
				// Preparar buffers de entrada y salida para el socket
				iniciarBuffers();
				
				// Dialogo entre el server y el cliente
				// 3. Recibir mensaje del cliente
				transaccion = recibirDatos();
				System.out.println("[ SERVER ] Received: " + transaccion);
				//4. Revisar transaccion
				if (transaccion.equals("getNumeroAlbums")) {
					//Obtener numero de albums.
					numeroAlbums = albumsAD.getNumeroAlbums();	
					//Enviar al cliente el numero de albums
					enviarDatos(""+numeroAlbums);
					System.out.println("Numero de albums :" + numeroAlbums);
				}
				if (transaccion.equals("getImagenAlbum")){
					strImagen = albumsAD.getImage(); //gets first image path.
					enviarDatos(strImagen);
					enviarStreamImagen(strImagen);
					System.out.println("Imagen inicial:" + strImagen);
				}
				if (transaccion.equals("getNextImage")){
					strImagen = albumsAD.nextImg();//gets first image path.
					enviarDatos(strImagen);
					enviarStreamImagen(strImagen);
					System.out.println("Imagen enviada:" + strImagen);
				}
				if (transaccion.equals("getPrevImage")){
					strImagen = albumsAD.prevImg(); //gets first image path.
					enviarDatos(strImagen);
					enviarStreamImagen(strImagen);
					System.out.println("Imagen enviada:" + strImagen);
				}
				if (transaccion.equals("getAll")){
					strImagen = "Prev Image"; //gets first image path.
					enviarDatos(albumsAD.getAll());
				}

				// 5. Cerrar conexion
				cerrarConexion();
				
				// Desplegar Mensajes
				taDatos.append("\nTransaccion: "+transaccion);
			}
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}
	}
	private String enviarStreamImagen(String strImagen){
		int nb = 0;
		byte streamImagen[] = new byte[1024];
		String archivoImagen = strImagen;
		try {
			inputStream = new FileInputStream("img/"+archivoImagen);
			bufferImagen = socket.getOutputStream();

			while ((nb=inputStream.read(streamImagen))>0) {
				bufferImagen.write(streamImagen,0,nb);
			}
		} catch (Exception e) {
			System.out.println("[ Server ] Exception: "+ e);
		}
		return strImagen;
	}
	
	public static void main(String args[])
	{
		Server serverApp = new Server();
		serverApp.inicializarServer();
	}
}