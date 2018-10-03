import java.util.StringTokenizer;

import javax.swing.ImageIcon;

public class ImagesDP {
    private ImageIcon img;
    private String path;
    private ImagesDP next,prev;

    public ImagesDP(String str){
        this.img  = new ImageIcon(((new ImageIcon("img/"+str)).getImage()).getScaledInstance(218, 218, java.awt.Image.SCALE_SMOOTH));
        this.path = "img/"+str;
        this.next = null;
        this.prev = null;
    }

    public ImageIcon getImage() {
        return this.img;
    }

    public ImagesDP getNext(){
        return this.next;
    }
    
    public ImagesDP getPrev(){
        return this.prev;
    }
    
    public void setImage(String str){
        this.img = new ImageIcon(((new ImageIcon("img/"+str)).getImage()).getScaledInstance(218, 218, java.awt.Image.SCALE_SMOOTH));
    }

    public void setNext(ImagesDP node){
        this.next = node;
    }
    public void setPrev(ImagesDP node){
        this.prev = node;
    }
    public String toString(){
        return this.path;
    }

}
