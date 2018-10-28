import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class ImagenADLL
{
    private BufferedReader archivoIn;
    private ImagenDP   actual;
    private LinkedList listaImagenes; // = new LinkedList();
    private LinkedList listaSongs;
    
    //private ImageIcon imagenAlbum;
    private int numeroAlbums;
    
    // Metodo que crea la LinkedList de los albums
    public void crearCatalogo()
    {
        String datos="";
        String strImagen="";
        StringTokenizer st;
        int i=0;
        
        listaImagenes = new LinkedList();
        numeroAlbums=0;
        try
        {
            // 1. Abrir el archivo de datos
            archivoIn = new BufferedReader(new FileReader("Albums.txt"));
            
            // 2. Leer los datos del archivo
            while(archivoIn.ready())
            {
                datos = archivoIn.readLine();
                //crearNodoImagen(new ClienteDP(datos));
                
                listaImagenes.add(new ImagenDP(datos));
                actual = (ImagenDP)listaImagenes.get(i);
                
                strImagen = actual.getAlbum();
                strImagen = "images/"+strImagen+".jpg";
                actual.setImagenAlbum(new ImageIcon(getClass().getResource(strImagen)));
                                  
                numeroAlbums++;
                i++;
            }
            
            // 3. Cerrar el archivo
            archivoIn.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("Error: "+fnfe);
        }
        catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }
    }
    
    // Metodo que crea la LinkedList de los albums
    public void crearAlbums(String artista)
    {
        String datos="";
        String strImagen="";
        StringTokenizer st;
        int i=0;
        String grupo;
        
        listaImagenes = new LinkedList();
        numeroAlbums=0;
        try
        {
            // 1. Abrir el archivo de datos
            archivoIn = new BufferedReader(new FileReader("Albums.txt"));
            
            // 2. Leer los datos del archivo
            while(archivoIn.ready())
            {
                datos = archivoIn.readLine();
                //crearNodoImagen(new ClienteDP(datos));
                
                st = new StringTokenizer(datos,"_");
                grupo = st.nextToken();
                
                if(artista.equals(grupo))
                {
                    listaImagenes.add(new ImagenDP(datos));
                    actual = (ImagenDP)listaImagenes.get(i);
                    
                    strImagen = actual.getAlbum();
                    strImagen = "images/"+strImagen+".jpg";
                    actual.setImagenAlbum(new ImageIcon(getClass().getResource(strImagen)));
                    
                    numeroAlbums++;
                    i++;
                }
                
            }
            
            // 3. Cerrar el archivo
            archivoIn.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("Error: "+fnfe);
        }
        catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }
    }
    
    public int getNumeroAlbums()
    {
        return this.numeroAlbums;
    }
    
    public ImageIcon getImage(int noImagen)
    {
        //actual = primero;
        
        //for(int i=0; i<noImagen; i++)
            //actual = actual.getNext();
        actual = (ImagenDP)listaImagenes.get(noImagen);
        
        return actual.getImagenAlbum();
    }
    
    public String getImagenAlbum(int noImagen)
    {
        //actual = primero;
        
        //for(int i=0; i<noImagen; i++)
        //actual = actual.getNext();
        actual = (ImagenDP)listaImagenes.get(noImagen);
        
        return actual.getAlbum();
    }
    
    public LinkedList obtenerListaSongs(int numeroAlbum)
    {
    	String album,datos,record,song;
    	StringTokenizer st;
    	
    	//1. Obtener el nombre del album
    	actual = (ImagenDP)listaImagenes.get(numeroAlbum);
    	album = actual.getAlbum();
    	
    	//2. Crear lista de songs de albums
    	
    	try
    	{
    		//2.1 Abrir el archivo de songs.txt
    		archivoIn = new BufferedReader(new FileReader("Songs.txt"));
    		
    		listaSongs = new LinkedList();
    		
    		//procesar el archivo
    		while(archivoIn.ready())
    		{
    			datos = archivoIn.readLine();
    			st = new StringTokenizer(datos,"_");
    			record = st.nextToken();
    			song = st.nextToken();
    			
    			if(album.equals(record))
    			{
    				listaSongs.add(song);
    			}
    			
    		}
    			
    	}
    	catch(IOException ioe)
    	{
    		System.out.println("Error: "+ioe);
    	}
    	
    	return listaSongs;
    }
}