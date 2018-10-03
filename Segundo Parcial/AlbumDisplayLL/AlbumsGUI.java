import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AlbumsGUI extends JFrame implements ActionListener{

    private JButton bPrev, bNext;
    private JPanel panelControl, panelOutput, panelImage;
    //private AlbumsADLL albumsAD = new AlbumsADLL();
    private AlbumsAD albumsAD = new AlbumsAD();
    private JLabel labelAlbum;

    public AlbumsGUI(){
        super("Albums Images");
        bPrev = new JButton("<");
        bNext = new JButton(">");
        panelImage= new JPanel();
        panelControl = new JPanel();
        panelOutput = new JPanel();

        bPrev.addActionListener(this);
        bNext.addActionListener(this);

        panelControl.setLayout(new GridLayout(1,2));
        panelImage.setLayout(new GridLayout(1,1));
        panelOutput.setLayout(new FlowLayout());
        panelControl.add(bPrev);
        panelControl.add(bNext);

        labelAlbum = new JLabel(albumsAD.getImage());
        panelImage.add(labelAlbum);
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
            labelAlbum = new JLabel(albumsAD.prevImg());
            panelImage.setVisible(false);
            panelImage.removeAll();
            panelImage.add(labelAlbum);
            panelImage.setVisible(true);
        }
        if (e.getSource()== bNext) {
            labelAlbum = new JLabel(albumsAD.nextImg());
            panelImage.setVisible(false);
            panelImage.removeAll();
            panelImage.add(labelAlbum);
            panelImage.setVisible(true);
        }
    }
    public static void main(String[] args) {
        new AlbumsGUI();
    }
    
}