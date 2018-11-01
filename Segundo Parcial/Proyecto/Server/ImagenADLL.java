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
    private ImagenDP  primero, actual, ultimo;
    private ImagenDP listaImagenes;
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
        
            primero = null;
            ultimo = null;
            actual = primero;
        //listaImagenes = new LinkedList();
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
                System.out.println("[ AD ] "+ datos);

                //listaImagenes.add(new ImagenDP(datos));
                if (primero == null) {
                    primero = new ImagenDP(datos);
                    ultimo = primero;
                    ultimo.setNext(null);
                }else{
                    actual = new ImagenDP(datos);
                    ultimo.setNext(actual);
                    ultimo = actual;
                    ultimo.setNext(null);
                }

                // //if(i == 0){
                // //     actual = primero;
                // // }
                // // else{
                // //     actual = actual.getNext();
                // // }
                
                // actual = (ImagenDP)listaImagenes.get(i);
                actual = primero;
                for(int j=0; j<i; j++)
                    actual = actual.getNext();
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

            primero = null;
            ultimo = null;
            actual = primero;
        
        //listaImagenes = new LinkedList();
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

                System.out.println("Artista: "+ artista +"  &&  "+"Grupo: "+ grupo );
                
                if(artista.equals(grupo))
                {
                    //listaImagenes.add(new ImagenDP(datos));

                    if (primero == null) {
                        primero = new ImagenDP(datos);
                        ultimo = primero;
                        ultimo.setNext(null);
                    }else{
                        actual = new ImagenDP(datos);
                        ultimo.setNext(actual);
                        ultimo = actual;
                        ultimo.setNext(null);
                    }


                    //actual = (ImagenDP)listaImagenes.get(i);
                    actual = primero;
                    for(int j=0; j<i; j++)
                        actual = actual.getNext();
                    System.out.println("[ AD ] "+ actual.toString());
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
        actual = primero;
        for(int i=0; i<noImagen; i++)
            actual = actual.getNext();
        // actual = (ImagenDP)listaImagenes.get(noImagen);
        
        return actual.getImagenAlbum();
    }
    
    public String getImagenAlbum(int noImagen)
    {
        actual = primero;
        for(int i=0; i<noImagen; i++)
            actual = actual.getNext();
        //actual = (ImagenDP)listaImagenes.get(noImagen);
        
        return actual.getAlbum();
    }
    
    public String[] obtenerListaSongs(int numeroAlbum)
    {
    	String album,datos,record,song;
    	StringTokenizer st;
        String text="";
        //1. Obtener el nombre del album
        actual = primero;
        for(int i=0; i<numeroAlbum; i++)
            actual = actual.getNext();
        
            //actual = (ImagenDP)listaImagenes.get(numeroAlbum);
    	album = actual.getAlbum();
    	
    	//2. Crear lista de songs de albums
    	
    	try
    	{
    		//2.1 Abrir el archivo de songs.txt
    		archivoIn = new BufferedReader(new FileReader("Songs.txt"));
    		
    		//listaSongs = new LinkedList();
    		
            //procesar el archivo
            int i  = 0;
    		while(archivoIn.ready())
    		{
    			datos = archivoIn.readLine();
    			st = new StringTokenizer(datos,"_");
    			record = st.nextToken();
    			song = st.nextToken();
    			
    			if(album.equals(record))
    			{
                    i++;
                    text+=song+"_";
                }
                
    		}
    			
    	}
    	catch(IOException ioe)
    	{
    		System.out.println("Error: "+ioe);
        }
        String data[] = text.split("_");
    	return data;
    }
}