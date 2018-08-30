import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImagesGUI extends JFrame implements ActionListener
{
    // Atributos de la aplicacion
    private JPanel panelUsuario, panelArtistas, panelAlbums, panelSongs, panelAudio, panelPrincipal;
    
    private JButton bCatalogo, bArtista, bAlbums, bSongs;
    private JButton bPlay, bStop;
    private JTextField tfArtista, tfAlbum, tfSong;
    private JTextArea  taArtistas, taAlbums, taSongs;
    
    private ImagesAD audiotunesad = new ImagesAD();
    
    private JList listaAlbums;
    
    private ImageIcon imagenesAlbums[];
    
    public ImagesGUI()
    {
        super("Arreglos con Images");
        
        // 1. Crear objetos d elos atributos
        tfArtista = new JTextField(10);
        tfAlbum   = new JTextField(10);
        tfSong    = new JTextField(10);
        bCatalogo = new JButton("Catalogo");
        bArtista  = new JButton("Artista");
        bAlbums   = new JButton("Albums");
        bSongs    = new JButton("Songs");
        bPlay     = new JButton("Play");
        bStop     = new JButton("Stop");
        taArtistas= new JTextArea("Artistas",20,20);
        taAlbums  = new JTextArea("Albums");
        taSongs   = new JTextArea("Songs",20,20);
        panelUsuario  = new JPanel();
        panelArtistas = new JPanel();
        panelAlbums   = new JPanel();
        panelSongs    = new JPanel();
        panelAudio    = new JPanel();
        panelPrincipal= new JPanel();
        
        // Adionar actionListener a los JButtons
        bPlay.addActionListener(this);
        bStop.addActionListener(this);
        bCatalogo.addActionListener(this);
        bAlbums.addActionListener(this);
        bSongs.addActionListener(this);
        
        // 2. Definir Layouts de los JPanels
        panelUsuario.setLayout(new FlowLayout());
        panelArtistas.setLayout(new GridLayout(1,1));
        panelAlbums.setLayout(new GridLayout(1,1));
        panelSongs.setLayout(new GridLayout(1,1));
        panelAudio.setLayout(new FlowLayout());
        panelPrincipal.setLayout(new BorderLayout(5,5));

        taArtistas.setText(audiotunesad.getArtistas());

        // 3. Colocar los objetos de los atributos en los panels correspondientes
        //panelUsuario.add(bCatalogo);
        panelUsuario.add(new JLabel("Artista: "));
        panelUsuario.add(tfArtista);
        panelUsuario.add(bAlbums);
        //panelUsuario.add(new JLabel("   Album: "));
        //panelUsuario.add(tfAlbum);
        //panelUsuario.add(bSongs);
        
        panelArtistas.add(new JScrollPane(taArtistas));
        panelAlbums.add(new JScrollPane(taAlbums));
        panelSongs.add(new JScrollPane(taSongs));
        
        //panelAudio.add(new JLabel("Song: "));
        //panelAudio.add(tfSong);
        //panelAudio.add(bPlay);
        //panelAudio.add(bStop);
        
        panelPrincipal.add(panelUsuario, BorderLayout.NORTH);
        panelPrincipal.add(panelArtistas, BorderLayout.WEST);
        panelPrincipal.add(panelAlbums, BorderLayout.CENTER);
        panelPrincipal.add(panelSongs, BorderLayout.EAST);
        //panelPrincipal.add(panelAudio, BorderLayout.SOUTH);
        
        // 4. Adicionar el panelPrincipal al JFrame
        add(panelPrincipal);
        setSize(800,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String song, respuesta;
        String artistas, artista, albums, songs;
        
        if(e.getSource() == bAlbums)
        {
            // 1. Obtener al artista de quien se desea los albums
            artista = tfArtista.getText().toUpperCase();
            System.out.println("Selected Artist: " + artista);
            // 2. Obtener los albums del artista
            imagenesAlbums = audiotunesad.obtenerImagenesAlbums(artista);
            System.out.println(imagenesAlbums.length);
            if (imagenesAlbums.length == 0){
                taAlbums.setText("Sin Imagenes");
            }else {
                // 3. Crear listaAlbums a partir del imagenesAlbums
                listaAlbums = new JList(imagenesAlbums);
                //4. Mostrar la listaAlbums en el panelAlbums
                panelAlbums.setVisible(false);
                panelAlbums.removeAll();
                panelAlbums.add(new JScrollPane(listaAlbums));
                panelAlbums.setVisible(true);
            }
        }
    }
    
    public static void main(String args[])
    {
        new ImagesGUI();
    }
}











