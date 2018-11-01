import java.util.StringTokenizer;
import javax.swing.ImageIcon;

public class ImagenDP
{
    // Atributos
    private String artista, album;
    private ImageIcon imagenAlbum;
    
    private ImagenDP next;
    
    // Constructores
    public ImagenDP()
    {
        this.artista     = "";
        this.album       = "";
        this.imagenAlbum = null;
        
        this.next  = null;
    }
    
    public ImagenDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        
        this.artista     = st.nextToken();
        this.album       = st.nextToken();
        this.imagenAlbum = null;
    }
    
    // Metodos: Accesors (geters) y Mutators (seters)
    public String getArtista()
    {
        return this.artista;
    }
    
    public String getAlbum()
    {
        return this.album;
    }
    
    public ImageIcon getImagenAlbum()
    {
        return this.imagenAlbum;
    }
    
    public ImagenDP getNext()
    {
        return this.next;
    }
    
    // Mutators
    public void setArtista(String str)
    {
        this.artista = str;
    }
    
    public void setAlbum(String str)
    {
        this.album = str;
    }
    
    public void setImagenAlbum(ImageIcon imagen)
    {
        this.imagenAlbum = imagen;
    }
    
    public void setNext(ImagenDP nodo)
    {
        this.next = nodo;
    }
    
    public String toString()
    {
        return this.artista+"_"+this.album;
    }
}












