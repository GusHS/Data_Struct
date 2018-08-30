import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.swing.ImageIcon;

public class ImagesAD
{
    private BufferedReader archivoIn;
    private ImageIcon imagenesAlbums[];
    private String albums;
    private String albumsImages[];
    

    public ImageIcon[] obtenerImagenesAlbums(String artista)
    {
        String str, grupo, album;
        StringTokenizer st;
        int numeroAlbums=0;
        albums="";
        try {
            archivoIn = new BufferedReader(new FileReader("Albums.txt"));
            while (archivoIn.ready()) {
                str = archivoIn.readLine();
                st = new StringTokenizer(str,"_");
                grupo = st.nextToken();
                album = st.nextToken();
                if (artista.equals(grupo)) {
                    albums+=album+"\n";
                    numeroAlbums++;
                }
            }
            archivoIn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        //String to Image
        albumsImages = albums.split("\n");
        imagenesAlbums = new ImageIcon[numeroAlbums];
        for (int i = 0; i < imagenesAlbums.length; i++) {
            str = "img/"+albumsImages[i]+".jpg";
            imagenesAlbums[i] = new ImageIcon(((new ImageIcon(str)).getImage()).getScaledInstance(218, 218, java.awt.Image.SCALE_SMOOTH)); //Get image and set size
        }
        return imagenesAlbums;
    }
    public String getArtistas(){
        int ln = 0;
        String sortArtistas[];
        String artistas = "", str = "";
        StringTokenizer st;
        try {
            archivoIn = new BufferedReader(new FileReader("Albums.txt"));
            while(archivoIn.ready()){
                str = archivoIn.readLine();
                st = new StringTokenizer(str,"_");
                artistas+=st.nextToken()+"\n";
                ln++;
            }
            archivoIn.close();
        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }
        //Sort and remove duplicates.
        sortArtistas = new String[ln];
        sortArtistas = artistas.split("\n");
        Arrays.sort(sortArtistas);
        artistas = sortArtistas[0]+"\n";
        for (int i = 1; i < sortArtistas.length; i++) {
            if (!sortArtistas[i].equals(sortArtistas[i-1])) {
                artistas+=sortArtistas[i]+"\n";
            }
        }
        return artistas;
    }
}















