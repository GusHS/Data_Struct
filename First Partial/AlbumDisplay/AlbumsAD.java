import java.io.BufferedReader;
import java.io.File;

import javax.swing.ImageIcon;

public class AlbumsAD {
    File path;
    String directory[];
    ImageIcon[] img;
    ImageIcon[] image = new ImageIcon[1];

    public AlbumsAD(){
        path = new File("img//");
        directory = path.list();
        img = new ImageIcon[directory.length];
        for (int i = 0; i < directory.length; i++) {
            System.out.println("img/"+directory[i]);
            img[i] = new ImageIcon(((new ImageIcon("img/"+directory[i])).getImage()).getScaledInstance(218, 218, java.awt.Image.SCALE_SMOOTH));
        }
    }
    public int getTotalFiles(){
        int length = directory.length;
        return length;
    } 
    public ImageIcon[] getImage(int pos){
        image[0] = img[pos];
        return image;
    }
}