import javax.swing.*;
import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.LinkedList;

public class Server extends JFrame
{
	private JTextArea taDatos;
	private JPanel    panel;
	
	private ServerSocket server;
	private Socket       socket;
	
	private BufferedReader bufferEntrada;
	private PrintWriter    bufferSalida;
    
    private InputStream inputStream;
    private OutputStream bufferImagen;
    
    private ImagenADLL3 imagenad = new ImagenADLL3();
    
    private LinkedList listaSongs;
	
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
	
    // Envio de imagenes JPG
    void enviarImagenJpg(String nombreAlbum)
    {
        byte streamImagen[] = new byte[1024];
        int nb=0;
        
        try
        {
            inputStream = new FileInputStream("images/"+nombreAlbum+".jpg");
            bufferImagen = socket.getOutputStream();
            
            while((nb=inputStream.read(streamImagen)) > 0)
            {
                bufferImagen.write(streamImagen,0,nb);
            }
        }
        catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }
    }
    
	private void inicializarServer()
	{
		String transaccion="";
        int numeroAlbums=0, numeroImagen,numeroAlbum;
		String nombreAlbum;
        String artista;
		
		try
		{
			// 1. Iniciar Server
			server = new ServerSocket(7000,5);
			
			while(true)
			{
				taDatos.append("\nEsperando peticion de conexion de un cliente...\n");
				
				// 2. Aceptar peticion de conexion por parte de un Cliente
				socket = server.accept();
				
				// Preparar buffers de entrada y salida para el socket
				iniciarBuffers();
				
				// Dialogo entre el server y el cliente
				// 3. Recibir la transaccion a realizar del cliente
				transaccion = recibirDatos();
				
                // 4. Checar transaccion que se desea
                if(transaccion.equals("getNumeroAlbums"))
                {
                    // Obtener el numero de albums con ImagenADLL
                    numeroAlbums = imagenad.getNumeroAlbums();
                    
                    // Enviar datos al cliente
                    enviarDatos(""+numeroAlbums);
                    
                    System.out.println("No. de Albums: "+numeroAlbums);
                }
				
                if(transaccion.equals("crearCatalogo"))
                {
                    // Crear la LinkedList de albums en AD
                    imagenad.crearCatalogo();
                    
                    // obtner el No. de albums en la lista
                    numeroAlbums = imagenad.getNumeroAlbums();
                    
                    // Enviar el dato al cliente
                    enviarDatos(""+numeroAlbums);
                }
                
                if(transaccion.equals("crearAlbums"))
                {
                    // Obtener el artista para generar la LL
                    artista = recibirDatos();
                    
                    // Crear la LinkedList de albums en AD
                    imagenad.crearAlbums(artista);
                    
                    // obtner el No. de albums en la lista
                    numeroAlbums = imagenad.getNumeroAlbums();
                    
                    // Enviar el dato al cliente
                    enviarDatos(""+numeroAlbums);
                }
                
                if(transaccion.equals("getImagenAlbum"))
                {
                    // Recibir numero de album a enviar
                    numeroImagen = Integer.parseInt(recibirDatos());
                    
                    // Obtener el nombre del album a taves de ImagenADLL
                    nombreAlbum = imagenad.getImagenAlbum(numeroImagen);
                    
                    // Enviar nombre del album al cliente
                    enviarDatos(nombreAlbum);
                    enviarImagenJpg(nombreAlbum);
                    
                    System.out.println("Album: "+nombreAlbum);
                    
                }
                
                if(transaccion.equals("obtenerSongs"))
                {
                	numeroAlbum = Integer.parseInt(recibirDatos());
                	
                	listaSongs = imagenad.obtenerListaSongs(numeroAlbum);
                	
                	int numSongs = listaSongs.size();
                	enviarDatos(""+numSongs);
                	
                	int i = 0;
                	while(i<numSongs)
                	{
                		enviarDatos((String)listaSongs.get(i));
                		i++;
                	}
                }
                
				// 5. Cerrar conexion
				cerrarConexion();
				
				// Desplegar Mensajes
				taDatos.append("\nTransaccion: "+transaccion);
                System.out.println("No. Albums: "+numeroAlbums);
			}
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}
	}
	
	public static void main(String args[])
	{
		Server serverApp = new Server();
		serverApp.inicializarServer();
	}
}