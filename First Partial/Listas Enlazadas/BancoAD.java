import java.io.*;
import java.util.StringTokenizer;

public class BancoAD{
    //Atributos
    private ClienteDP primero, ultimo, actual;
    private PrintWriter archivoOut;
    private BufferedReader archivoIn;
    private StringTokenizer st;

    //Constructor
    public BancoAD(){
        String str;
        try {
            archivoIn = new BufferedReader(new FileReader("Datos.txt"));
            while(archivoIn.ready()){
                capturar(archivoIn.readLine());
            }
            archivoIn.close();
        } catch (Exception e) {
           System.out.println("Exception: " + e );
        }
    }

    //Metodos
    public String capturar(String datos){
        if (primero == null) {
            primero = new ClienteDP(datos);
            ultimo = primero;
            ultimo.setNext(null);
        }else{
            actual = new ClienteDP(datos);
            ultimo.setNext(actual);
            ultimo = actual;
            ultimo.setNext(null);
        }
        return "Datos: " + datos;
    }
    
    public String consultarNocta(String numCuenta){
        String datos="No se localizo el numero de cuenta", nocta="";
        if (primero == null) {
          datos = "Lista vacia...";
        }else{
            actual = primero;
            while (actual != null) {
                nocta = actual.getNocta();
                if (nocta.equals(numCuenta))
                    datos = actual.toString();
                actual = actual.getNext();
            }
        }
        return datos;
    }
    public String consultarClientes(){
        String datos ="";
        if (primero == null) {
            datos = "Lista vacia...";
        }else{
            actual = primero;
            while (actual != null) {
                datos = datos +actual.toString()+"\n";
                actual = actual.getNext();
            }
        }
        return datos;
    }
    public String saveOnFile(){
        String datos="";
        try {
            if(primero == null){
                datos = "Sin datos por escribir";
            }else{
                actual = primero;
                archivoOut = new PrintWriter(new FileWriter("Datos.txt"));
                while(actual != null){
                    archivoOut.println(actual.toString());
                    actual = actual.getNext();
                }
                archivoOut.close();
                datos = "Datos escritos.";
            }
        } catch (Exception e) {
           System.out.println("Error: " + e);
        }
        return datos;
    }
    public String consultarArchivo(){
        String datos="";
        try {
            archivoIn = new BufferedReader(new FileReader("Datos.txt"));
            while(archivoIn.ready()){
                datos+=archivoIn.readLine()+"\n";
            }
            archivoIn.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return datos;
    }
}