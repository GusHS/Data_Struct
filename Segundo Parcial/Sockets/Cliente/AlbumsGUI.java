import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AlbumsGUI extends JFrame implements ActionListener{

    private JButton bPrev, bNext;
    private JPanel panelControl, panelOutput, panelImage;
    private Conexion conexion = new Conexion();
    private JLabel labelAlbum;
    private JTextArea taDatos = new JTextArea();
    private ImageIcon imageIcon;

    private byte streamImagen[];
    private OutputStream archivoImagen;
    private InputStream bufferImagen;
    private Socket socket;

    private int contador, numeroImagenes;

    private String path;

    public AlbumsGUI(){
        super("Albums Images");
        bPrev = new JButton("<");
        bNext = new JButton(">");
        panelImage= new JPanel();
        panelControl = new JPanel();
        panelOutput = new JPanel();

        contador = 0;
        numeroImagenes = getNumeroAlbums();

        bPrev.addActionListener(this);
        bNext.addActionListener(this);

        panelControl.setLayout(new GridLayout(1,2));
        panelImage.setLayout(new GridLayout(1,1));
        panelOutput.setLayout(new FlowLayout());
        panelControl.add(bPrev);
        panelControl.add(bNext);

        //labelAlbum = new JLabel(albumsAD.getImage());
        //taDatos.setText(getFirstImage());
        getAll();


        panelImage.add(new JLabel(getFirstImage()));
        panelOutput.setLayout(new GridLayout(2,1));
        panelOutput.add(panelControl);
        panelOutput.add(panelImage);

        add(panelOutput);
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed( ActionEvent e) {
            
        if (e.getSource() == bPrev){
            panelImage.setVisible(false);
            panelImage.removeAll();
            panelImage.add(new JLabel(getPrevImage()));
            panelImage.setVisible(true);
        }

        if (e.getSource()== bNext) {
            panelImage.setVisible(false);
            panelImage.removeAll();
            panelImage.add(new JLabel(getNextImage()));
            panelImage.setVisible(true);
        }
    }
    private int getNumeroAlbums(){
        int nAlbums;
        conexion.establecerConexion();
        conexion.enviarDatos("getNumeroAlbums");
        nAlbums = Integer.parseInt(conexion.recibirDatos());
        conexion.cerrarConexion();
        return nAlbums;
    }
    private ImageIcon getFirstImage(){
        socket = conexion.establecerConexion();
        conexion.enviarDatos("getImagenAlbum");
        path = conexion.recibirDatos();
        try {
            bufferImagen = socket.getInputStream();
            Image image = ImageIO.read(bufferImagen);
            imageIcon = new ImageIcon(image);
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println("[ Client ] Exception at First: " + e);
        }
        
        return imageIcon;
    }
    private ImageIcon getNextImage(){
        socket = conexion.establecerConexion();
        conexion.enviarDatos("getNextImage");
        path = conexion.recibirDatos();
        try {
            bufferImagen = socket.getInputStream();
            Image image = ImageIO.read(bufferImagen);
            imageIcon = new ImageIcon(image);
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println("[ Client ] Exception at Next: " + e);
        }
        
        return imageIcon;
    }
    private ImageIcon getPrevImage(){
        socket = conexion.establecerConexion();
        conexion.enviarDatos("getPrevImage");
        path = conexion.recibirDatos();
        try {
            bufferImagen = socket.getInputStream();
            Image image = ImageIO.read(bufferImagen);
            imageIcon = new ImageIcon(image);
            conexion.cerrarConexion();
        } catch (Exception e) {
            System.out.println("[ Client ] Exception at Prev: " + e);
        }
        return imageIcon;
    }
    private String getAll(){
        conexion.establecerConexion();
        conexion.enviarDatos("getAll");
        path = conexion.recibirDatos();
        conexion.cerrarConexion();
        return path;
    }
    public static void main(String[] args){
        new AlbumsGUI();
    }
    
}