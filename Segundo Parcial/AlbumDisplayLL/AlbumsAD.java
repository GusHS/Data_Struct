import java.io.BufferedReader;
import java.io.File;

import javax.swing.ImageIcon;

public class AlbumsAD {
    private File path;
    private String directory[];
    private ImagesDP first, last, actual;
    public AlbumsAD(){
        path = new File("img/");
        directory = path.list();
        for (int i = 0; i < directory.length; i++) {
            capturar(directory[i]);
        }
        first.setPrev(last);
        last.setNext(first);
        actual = first;
    }

    public String capturar(String datos){
        if (last == null) {
            first = new ImagesDP(datos);
            last = first;
            first.setPrev(null);
            last.setNext(null); 
        }else{
            actual = new ImagesDP(datos);
            actual.setPrev(last);
            System.out.println(actual.toString());
            last.setNext(actual);
            last = actual;
            last.setNext(null);
        }
        return "Captura Exitosa: "+ datos;
    }

    public ImageIcon prevImg(){
        actual = actual.getPrev();
        return actual.getImage();
    }
    public ImageIcon nextImg(){
        actual = actual.getNext();
        return actual.getImage();
    }
    public ImageIcon getImage(){
        return actual.getImage();
    }
}