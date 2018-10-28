import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;

import java.util.Vector;

import java.net.Socket;


public class ImagenGUI extends JFrame implements ActionListener
{
	// Atributos, variables de clase o variables de instancia
    private JButton bNext, bPrev;
    
    //private ImagenAD imagenad = new ImagenAD();
    //private ImagenADLL imagenad = new ImagenADLL();
    private Conexion conexion = new Conexion();
    
    //private ImageIcon imageAlbums[];
    
    private int contadorImagen=-1;
    private int numeroImagenes;
    
    private byte streamImagen[];
    private OutputStream archivoImagen;
    private InputStream bufferImagen;
    
    private ImageIcon imagen;
    private File f;
    
    private Socket socket;
  
    //private JPanel panel1, panel2, panel3;
    
    // Nuevos atributos de JPanels y JButtons para el JFrame
    private JPanel panelOpcionArtista, panelPrevNext, panelAlbums, panelSongs, panelPlayStop, panelPrincipal;
	private JButton bArtista, bPlay, bStop;
	private JTextField tfArtista;
	
	private Vector vectorSongs;
	private JList listaSongs;
    
    // Constructor y Metodos o servicios
    public ImagenGUI()
    {
        super("Albums Images LList");
        
        // 1. Crear objetos de las variables de instancia o de clase
        bPrev  = new JButton("<");
        bNext  = new JButton(">");
        
        panelOpcionArtista  = new JPanel();
        panelPrevNext  = new JPanel();
        panelAlbums    = new JPanel();
        panelSongs     = new JPanel();
        panelPlayStop  = new JPanel();
        panelPrincipal = new JPanel();
        
        tfArtista = new JTextField();
        bArtista  = new JButton("Search");
        bPlay     = new JButton("Play");
        bStop     = new JButton("Stop");
        
        // 2. Definir Layouts de los panels
        panelOpcionArtista.setLayout(new GridLayout(1,3));
        panelPrevNext.setLayout(new FlowLayout());
        //panelPrevNext.setLayout(new GridLayout(1,1));
        panelAlbums.setLayout(new GridLayout(1,1));
        panelSongs.setLayout(new GridLayout(1,1));
        panelPlayStop.setLayout(new FlowLayout());
        panelPrincipal.setLayout(new BorderLayout(4,4));
        
        // 3. Adicionar addActionListener a los botones y poner boton bPrev no activo
        bPrev.addActionListener(this);
        bNext.addActionListener(this);
        bPrev.setEnabled(false);
        bNext.setEnabled(false);
        
        bArtista.addActionListener(this);
        bPlay.addActionListener(this);
        bStop.addActionListener(this);
        
        // 4. Obtener el arreglo de ImageIcons de los albums y el numero de albums
        //imageAlbums = imagead.obtenerImagenesAlbums();
        numeroImagenes = getNumeroAlbums();
        
        // 5. Adicionar a los panels los objetos correspondientes
        panelOpcionArtista.add(new JLabel("Artista: "));
        panelOpcionArtista.add(tfArtista);
        panelOpcionArtista.add(bArtista);
        
        panelPrevNext.add(bPrev);
        panelPrevNext.add(bNext);
        panelAlbums.add(new JLabel("   Imagen Albums"));
        panelSongs.add(new JLabel("Songs List"));
        
        panelPlayStop.add(new JLabel("Now Playing: NO SONG"));
        panelPlayStop.add(bPlay);
        panelPlayStop.add(bStop);
        
        panelPrincipal.add(panelOpcionArtista, BorderLayout.NORTH);
        panelPrincipal.add(panelPrevNext, BorderLayout.WEST);
        panelPrincipal.add(panelAlbums, BorderLayout.CENTER);
        panelPrincipal.add(panelSongs, BorderLayout.EAST);
        panelPrincipal.add(panelPlayStop, BorderLayout.SOUTH);
        
        // 6. Adicionar panel3 al JFrame y hacerlo visible
        add(panelPrincipal);
        setSize(500,300);
        setVisible( true );
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private int getNumeroAlbums()
    {
    	int noAlbums;
    	//Transaccion distribuida: Obtener numero de albums
            
        // 1. Establecer conexion con el server
		conexion.establecerConexion();
		
		// 2. Preparar buffers de entrada y salida para el socket
		//conexion.iniciarBuffers();
		
		// Dialogo entre el cliente y el server
		// 3. Enviar mensaje al server
		conexion.enviarDatos("getNumeroAlbums");
		
		// 4. Recibir mensaje del server
		noAlbums = Integer.parseInt(conexion.recibirDatos());
		
		// 5. Cerrar conexion
		conexion.cerrarConexion();
		
		return noAlbums;
    	
    }
    
    private int crearCatalogo()
    {
    	int respuesta;
    	//Transaccion distribuida: Obtener numero de albums
            
        // 1. Establecer conexion con el server
		conexion.establecerConexion();
		
		// 2. Preparar buffers de entrada y salida para el socket
		//conexion.iniciarBuffers();
		
		// Dialogo entre el cliente y el server
		// 3. Enviar mensaje al server
		conexion.enviarDatos("crearCatalogo");
		
		// 4. Recibir mensaje del server
		respuesta = Integer.parseInt(conexion.recibirDatos());
		
		// 5. Cerrar conexion
		conexion.cerrarConexion();
		
		return respuesta;
    }
    
    private int crearAlbums(String artista)
    {
    	int respuesta;
    	//Transaccion distribuida: Obtener numero de albums
            
        // 1. Establecer conexion con el server
		socket = conexion.establecerConexion();
		
		// 2. Preparar buffers de entrada y salida para el socket
		//conexion.iniciarBuffers();
		
		// Dialogo entre el cliente y el server
		// 3. Enviar transaccion al server
		conexion.enviarDatos("crearAlbums");
		conexion.enviarDatos(artista);
		
		// 4. Recibir mensaje del server
		respuesta = Integer.parseInt(conexion.recibirDatos());
		
		// 5. Cerrar conexion
		conexion.cerrarConexion();
		
		return respuesta;
    }
    
    private void obtenerSongs(int numeroAlbum)
    {
    	String respuesta;
    	// 1. Establecer conexion con el server
		socket = conexion.establecerConexion();
		
		// 2. Enviar transaccion al server
		conexion.enviarDatos("obtenerSongs");
		conexion.enviarDatos(""+numeroAlbum);
		
		//3. Recibir respuesta del server
		respuesta = conexion.recibirDatos();
		int numSongs = Integer.parseInt(respuesta);
		
		//4. Leer del bugger las canciones y crear un vector de songs
		vectorSongs = new Vector();
		
		int i=0;
		
		while(i<numSongs)
		{
			vectorSongs.add(conexion.recibirDatos());
			i++;
		}
		
		conexion.cerrarConexion();
		
		//5. Crear un JList del vectorSongs
		listaSongs = new JList(vectorSongs);
		
		//6. Colocar listaSongs en el panelSongs
		panelSongs.setVisible(false);
		panelSongs.removeAll();
		panelSongs.add(new JScrollPane(listaSongs));
		panelSongs.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String imagenAlbum="";
    	int nb=0;
    	File f;
    	String artista="";
    	
    		
    	
    	if(e.getSource()== bArtista)
    	{
 			artista = tfArtista.getText();
 			
 			if(artista.isEmpty())
 			{
 				numeroImagenes = crearCatalogo();
 			} 			    		
 			else
 			{
 				numeroImagenes = crearAlbums(artista);
 			}
 			
 			bNext.setEnabled(true);
 			bPrev.setEnabled(false);
 			contadorImagen=-1;
 			System.out.println("Albums: "+numeroImagenes);
 			
 			panelAlbums.setVisible(false);
 			panelAlbums.removeAll();
 			panelAlbums.add(new JLabel("IMAGE ALBUM"));
 			panelAlbums.setVisible(true);
 			
 			//obtenerSongs(contadorImagen);
    	}
    	
        if (e.getSource()== bNext)
        {
            contadorImagen++;
            
            if(contadorImagen > 0)
            {
                bPrev.setEnabled(true);
            }
            
            if(contadorImagen == numeroImagenes-1)
            {
                bNext.setEnabled(false);
            }
            
            //Transaccion distribuida: Obtener nombre del album
            
            // 1. Establecer conexion con el server
			socket=conexion.establecerConexion();
			
			// 2. Preparar buffers de entrada y salida para el socket
			//conexion.iniciarBuffers();
			
			// Dialogo entre el cliente y el server
			// 3. Enviar mensaje al server
			conexion.enviarDatos("getImagenAlbum");
			conexion.enviarDatos(""+contadorImagen);
			
			// 4. Recibir mensaje del server
			imagenAlbum = conexion.recibirDatos();
			
			try
			{
				//4.1 Recibir Imagen Server
				streamImagen=new byte[1024];
				archivoImagen=new FileOutputStream(imagenAlbum+".jpg");
				bufferImagen=socket.getInputStream();
				
				nb=0;
				while((nb=bufferImagen.read(streamImagen))>0)
				{
					archivoImagen.write(streamImagen,0,nb);
				}
				archivoImagen.close();
				
				// 5. Cerrar conexion
				conexion.cerrarConexion();
	            
	            //4.3Preparar el ImageIcon y desplegarlo en el panel 2
	            imagen=new ImageIcon(getClass().getResource(imagenAlbum+".jpg"));
	            
	            panelAlbums.setVisible(false);
	            panelAlbums.removeAll();
	            //panel2.add(new JLabel(imagenad.getImage(contadorImagen)));
	            //panel2.add(new JLabel(imagenAlbum));
	            panelAlbums.add(new JLabel(imagen));
	            panelAlbums.setVisible(true);
	            
	            //4.4 Borrar imagen JPG del cliente
	            f=new File(imagenAlbum+".jpg");
	            f.delete();
	            
	            System.out.println("Album: "+imagenAlbum);
			}
			catch(IOException ioe)
			{
				System.out.println("Error:"+ioe);
			}
			
			obtenerSongs(contadorImagen);
			
        }
        
        if (e.getSource() == bPrev)
        {
            contadorImagen--;
            
            if(contadorImagen < numeroImagenes)
            {
                bNext.setEnabled(true);
            }
            
            if(contadorImagen == 0)
            {
                bPrev.setEnabled(false);
            }
            
             //Transaccion distribuida: Obtener nombre del album
            // 1. Establecer conexion con el server
			socket=conexion.establecerConexion();
			
			// 2. Preparar buffers de entrada y salida para el socket
			//conexion.iniciarBuffers();
			
			// Dialogo entre el cliente y el server
			// 3. Enviar mensaje al server
			//mensaje = tfMensaje.getText();
			conexion.enviarDatos("getImagenAlbum");
			conexion.enviarDatos(""+contadorImagen);
			
			// 4. Recibir mensaje del server
			imagenAlbum = conexion.recibirDatos();
			
			try
			{
				//4.1 Recibir Imagen Server
				streamImagen=new byte[1024];
				archivoImagen=new FileOutputStream(imagenAlbum+".jpg");
				bufferImagen=socket.getInputStream();
			
				nb=0;
				while((nb=bufferImagen.read(streamImagen))>0)
				{
					archivoImagen.write(streamImagen,0,nb);
				}
				archivoImagen.close();
				
				// 5. Cerrar conexion
				conexion.cerrarConexion();
	            
	            //4.3Preparar el ImageIcon y desplegarlo en el panel 2
	            imagen=new ImageIcon(getClass().getResource(imagenAlbum+".jpg"));
	            
	            panelAlbums.setVisible(false);
	            panelAlbums.removeAll();
	            //panel2.add(new JLabel(imagenad.getImage(contadorImagen)));
	            panelAlbums.add(new JLabel(imagen));
	            panelAlbums.setVisible(true);
	            
	             //4.4 Borrar imagen JPG del cliente
	            f=new File(imagenAlbum+".jpg");
	            f.delete();
	            
	            System.out.println("Album: "+imagenAlbum);
			}
			catch(IOException ioe)
			{
				System.out.println("Error:"+ioe);
			}
			obtenerSongs(contadorImagen);
        }
    }

	
	//// Metodo principal main()
	public static void main(String args[])
	{
		new ImagenGUI();
	}
}