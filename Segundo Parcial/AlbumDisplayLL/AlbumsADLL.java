import java.util.LinkedList;
import javax.swing.ImageIcon;
import java.io.File;

public class AlbumsADLL {
    private LinkedList listImages = new LinkedList();
    private ImagesDP actual;
    private File path;
    private String directory[];
    int nodoActual = 0;
    public AlbumsADLL(){
        path = new File("img/");
        directory = path.list();
        for (int i = 0; i < directory.length; i++) {
            capturar(directory[i]);
        }
    }
    public void capturar(String datos){
        listImages.add(new ImagesDP(datos));
    }
    public ImageIcon prevImg(){
        if(nodoActual == 0)
            nodoActual = directory.length-1;
        actual = (ImagesDP)listImages.get(nodoActual--);
        return actual.getImage();
    }
    public ImageIcon nextImg(){
        
        if(nodoActual == directory.length-1)
            nodoActual=0;
        actual = (ImagesDP)listImages.get(nodoActual++);
        return actual.getImage();
    }
    public ImageIcon getImage(){
        actual = (ImagesDP)listImages.getFirst();
        nodoActual = 0;
        return actual.getImage();
    }
}