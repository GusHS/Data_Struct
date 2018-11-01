import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioOS {
    private File songFile;    
    private AudioInputStream audioIn;
    private InputStream inputStream;
    private Clip clip;
    private String song;
    
    public AudioOS(){ 
    }

    public void reproducir(InputStream is) throws Exception
    {
        // 1. Especificar el nombre de la cancion a reproducir        
        // // 2. Abrir el archivo .wav de la cancion usando File
        //songFile = new File(song+".wav");
        inputStream = new BufferedInputStream(is);
        // // 3. Preparar el Audio Stream relacionado con la cancion
        audioIn = AudioSystem.getAudioInputStream(inputStream);
        
        // 4. Reproducir la cancion con los metodos de la clase Clip
        clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
    public void stop() {
        clip.stop();
    }
    public void play(){
        clip.start();
    }


    public static void main(String args[]) throws Exception
    {
    
    }
}