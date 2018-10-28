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
    public String nextImg(){
        nodoActual++;
        if(nodoActual == directory.length)
            nodoActual=0;
        actual = (ImagesDP)listImages.get(nodoActual);
        return actual.toString();
    }

    public String prevImg(){
        nodoActual--;
        if(nodoActual == -1)
            nodoActual = directory.length-1;
        actual = (ImagesDP)listImages.get(nodoActual);
        return actual.toString();
    }

    public String getImage(){
        actual = (ImagesDP)listImages.getFirst();
        nodoActual = 0;
        //return actual.getImage();
        return actual.toString();
    }

    public String getAll(){
        String str="";
        for (int i = 0; i < directory.length; i++) {
            actual = (ImagesDP) listImages.get(i);
            str+= "\n"+actual.toString();
        }
        System.out.println("[ AD ] Get All:"+str);
        return str;
    }
    public int getNumeroAlbums(){
        return listImages.size();
    }
}