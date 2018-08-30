import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AlbumsGUI extends JFrame implements ActionListener{

    private JButton bPrev, bNext;
    private JPanel panelControl, panelOutput, panelImage;
    private AlbumsAD albumsAD = new AlbumsAD();
    private ImageIcon image;
    private JList listaAlbum;
    private int counter = 0;

    public AlbumsGUI(){
        super("Albums Images");
        bPrev = new JButton("<");
        bNext = new JButton(">");
        panelImage= new JPanel();
        panelControl = new JPanel();
        panelOutput = new JPanel();

        bPrev.setEnabled(false);

        bPrev.addActionListener(this);
        bNext.addActionListener(this);

        panelControl.setLayout(new GridLayout(1,2));
        panelControl.add(bPrev);
        panelControl.add(bNext);

        listaAlbum = new JList(albumsAD.getImage(0));
        panelImage.add(listaAlbum);
        panelOutput.setLayout(new GridLayout(2,1));
        panelOutput.add(panelControl);
        panelOutput.add(panelImage);

        add(panelOutput);
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed( ActionEvent e) {
        if (e.getSource() == bPrev) {
            counter--;
            if(counter==0){
                bPrev.setEnabled(false);
            }
            listaAlbum = new JList(albumsAD.getImage(counter));
            panelImage.setVisible(false);
            panelImage.removeAll();
            panelImage.add(listaAlbum);
            panelImage.setVisible(true);
        }
        if (e.getSource()== bNext) {
            counter++;
            if(counter == albumsAD.getTotalFiles()-1){
                bNext.setEnabled(false);
            }
            if (counter!=0) {
                bPrev.setEnabled(true);
            }
            listaAlbum = new JList(albumsAD.getImage(counter));
            panelImage.setVisible(false);
            panelImage.removeAll();
            panelImage.add(listaAlbum);
            panelImage.setVisible(true);
        }
    }


    public static void main(String[] args) {
        new AlbumsGUI();
    }
    
}