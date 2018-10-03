import java.io.*;
import java.util.StringTokenizer;

public class BancoAD
{
    // Atributos
    private ClienteDP  primero, ultimo, actual, anterior;
    private DepositosDP primeroDeposito, ultimoDeposito, actualDeposito;
    private RetirosDP   primeroRetiro, ultimoRetiro, actualRetiro;
    
    private PrintWriter    archivoOut;
    private BufferedReader archivoIn;
    
    // Constructor
    public BancoAD()
    {
        try
        {
            archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
            
            while(archivoIn.ready())
                captura(archivoIn.readLine());
            
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }
    }
    
    // Metodos
    public String addFirst(String datos)
    {
        String ncta, respuesta;
        
        StringTokenizer st = new StringTokenizer(datos,"_");
        ncta = st.nextToken();
        
        respuesta = consultarNocta(ncta);
        
        if(respuesta.equals("NOT_FOUND") || respuesta.equals("EMPTY"))
        {
            actual = new ClienteDP(datos);
            actual.setNext(primero.getNext());
            primero = actual;
            
            respuesta = "Nuevo Nodo Cliente capturado al inicio de la LL...";
        }
        else
            respuesta = "La cuenta ya existe: "+ncta;
        
        return respuesta;
    }
    
    public String capturar(String datos)
    {
        String ncta, respuesta;
        
        StringTokenizer st = new StringTokenizer(datos,"_");
        ncta = st.nextToken();
        
        respuesta = consultarNocta(ncta);
        
        if(respuesta.equals("NOT_FOUND") || respuesta.equals("EMPTY"))
        {
            respuesta = captura(datos);
        }
        else
            respuesta = "La cuenta ya existe: "+ncta;
        
        return respuesta;
    }
    
    private String captura(String datos)
    {
        if(primero == null)
        {
            primero = new ClienteDP(datos);
            ultimo = primero;
            ultimo.setNext(null);
        }
        else
        {
            actual = new ClienteDP(datos);
            ultimo.setNext(actual);
            ultimo = actual;
            ultimo.setNext(null);
            
        }
        
        return "Datos: "+datos;
    }
    
    private String capturaDeposito(String datos)
    {
        if(primeroDeposito == null)
        {
            primeroDeposito = new DepositosDP(datos);
            ultimoDeposito = primeroDeposito;
            ultimoDeposito.setNext(null);
        }
        else
        {
            actualDeposito = new DepositosDP(datos);
            ultimoDeposito.setNext(actualDeposito);
            ultimoDeposito = actualDeposito;
            ultimoDeposito.setNext(null);
            
        }
        
        return "Datos: "+datos;
    }
    
    private String capturaRetiro(String datos)
    {
        if(primeroRetiro == null)
        {
            primeroRetiro = new RetirosDP(datos);
            ultimoRetiro = primeroRetiro;
            ultimoRetiro.setNext(null);
        }
        else
        {
            actualRetiro = new RetirosDP(datos);
            ultimoRetiro.setNext(actualRetiro);
            ultimoRetiro = actualRetiro;
            ultimoRetiro.setNext(null);
            
        }
        
        return "Datos: "+datos;
    }
    
    public String consultarClientes()
    {
        String datos="";
        
        if(primero == null)
            datos = "Lista vacia...";
        else
        {
            actual = primero;
            while(actual != null)
            {
                //datos = datos + actual.getNocta()+"_"+actual.getNombre()+"_"+actual.getTipo()+"_"+actual.getSaldo()+"\n";
                
                datos = datos + actual.toString() + "\n";
                
                actual = actual.getNext();
            }
        }
        
        return datos;
    }
    
    public String consultarDepositosLL()
    {
        String datos="";
        
        if(primeroDeposito == null)
            datos = "Lista Depositos vacia...";
        else
        {
            actualDeposito = primeroDeposito;
            while(actualDeposito != null)
            {
                datos = datos + actualDeposito.toString() + "\n";
                
                actualDeposito = actualDeposito.getNext();
            }
        }
        
        return datos;
    }
    
    public String consultarRetirosLL()
    {
        String datos="";
        
        if(primeroRetiro == null)
            datos = "Lista Retiros vacia...";
        else
        {
            actualRetiro = primeroRetiro;
            while(actualRetiro != null)
            {
                datos = datos + actualRetiro.toString() + "\n";
                
                actualRetiro = actualRetiro.getNext();
            }
        }
        
        return datos;
    }
    
    public String consultarNocta(String ncta)
    {
        String datos="";
        boolean encontrado = false;
        int contador=0;
        
        if(primero == null)
            datos = "EMPTY";
        else
        {
            actual = primero;
            //anterior = primero;
            while(actual != null && !encontrado)
            {
                if(ncta.equals(actual.getNocta()))
                {
                    datos = actual.toString() + "\nActual es el nodo "+contador;
                    encontrado = true;
                }
                else
                {
                    anterior = actual;
                    actual = actual.getNext();
                    contador++;
                }
            }
            
            if(!encontrado)
                datos = "NOT_FOUND";
        }
        
        return datos;
    }
    
    public String eliminarNodoCliente(){
        int nodo=0;
        String respuesta="";
        if(actual == primero){
            primero = primero.getNext();
        }
        else{
            if(actual == ultimo){
                ultimo = anterior;
                ultimo.setNext(null);
            }
            else{
                anterior.setNext(actual.getNext());
            }
        }
        almacenarBajaCliente();
        respuesta = "Nodo Cliente eliminado: "+actual.toString();
        return respuesta;
    }

    
    public void datosListaArchivo()
    {
        if(primero == null)
            System.out.println("Lista vacia...");
        else{
            try{
                archivoOut = new PrintWriter(new FileWriter("Clientes.txt"));                
                actual = primero;
                while(actual != null){
                    archivoOut.println(actual.toString());
                    actual = actual.getNext();
                }
                archivoOut.close();
                System.out.println("Datos almacenados en el archivo...");
            }
            catch(IOException ioe){
                System.out.println("Error: "+ioe);
            }
        }
    }
    
    public String consultarClientesArchivo()
    {
        String datos="";
        try{
            archivoIn = new BufferedReader(new FileReader("Clientes.txt")); 
            while(archivoIn.ready())
                datos = datos + archivoIn.readLine() + "\n";
            archivoIn.close();
        }
        catch(IOException ioe){
            datos = "Error: "+ioe;
            System.out.println("Error: "+ioe);
        }
        return datos;
    }
    
    ///// Metodos para Depositos y retiros /////
    public String depositar(String ncta, int cantidad)
    {
        String resultado="";
        String datos="";
        
        datos = actual.toString();
        
        if(actual.getTipo().equals("INVERSION") || actual.getTipo().equals("AHORRO"))
            actual.setSaldo(actual.getSaldo() + cantidad);
        else
            actual.setSaldo(actual.getSaldo() - cantidad);
        
        datos = datos+"_"+actual.getSaldo();
        
        capturaDeposito(datos);
        
        resultado = "Deposito exitoso: "+datos;
        
        return resultado;
    }
    
    public String retirar(String ncta, int cantidad)
    {
        String resultado="";
        String datos="";
        
        datos = actual.toString();
        
        if(actual.getTipo().equals("HIPOTECA"))
            resultado = "No se hacen retiros de una HIPOTECA...";
        else
        {
            if(actual.getTipo().equals("INVERSION") || actual.getTipo().equals("AHORRO"))
                actual.setSaldo(actual.getSaldo() - cantidad);
            else
                actual.setSaldo(actual.getSaldo() + cantidad);
            
            datos = datos+"_"+actual.getSaldo();
            
            capturaRetiro(datos);
            
            resultado = "Retiro exitoso: "+datos;
        }
        
        return resultado;
    }
    
    /////
    
    public void almacenarBajaCliente()
    {
        try{
            archivoOut = new PrintWriter(new FileWriter("BajaClientes.txt",true));     
            archivoOut.println(actual.toString());
            archivoOut.close();
            System.out.println("Datos almacenados en el archivo BajaClientes.txt ... "+actual.toString());
        }
        catch(IOException ioe){
            System.out.println("Error: "+ioe);
        }
    }
    
    public String consultarArchBajaClientes(){
        String datos="";
        try{
            archivoIn = new BufferedReader(new FileReader("BajaClientes.txt")); 
            while(archivoIn.ready())
                datos = datos + archivoIn.readLine() + "\n";
            archivoIn.close();
        }
        catch(IOException ioe){
            datos = "Error: "+ioe;
            System.out.println("Error: "+ioe);
        }
        
        return datos;
    }
    
    public void datosLLDepositosArchDepositos()
    {
        if(primeroDeposito == null)
            System.out.println("Lista Depositos vacia...");
        else{
            try{
                archivoOut = new PrintWriter(new FileWriter("Depositos.txt",true)); 
                actualDeposito = primeroDeposito;
                while(actualDeposito != null)
                {
                    //datos = datos + actual.getNocta()+"_"+actual.getNombre()+"_"+actual.getTipo()+"_"+actual.getSaldo()+"\n"; 
                    archivoOut.println(actualDeposito.toString());
                    actualDeposito = actualDeposito.getNext();
                }
                archivoOut.close();
                System.out.println("Datos almacenados en el archivo Depositos.txt...");
            }
            catch(IOException ioe){
                System.out.println("Error: "+ioe);
            }
        }
    }
    
    public String consultarArchivoDepositos()
    {
        String datos="";
        try{
            archivoIn = new BufferedReader(new FileReader("Depositos.txt")); 
            while(archivoIn.ready())
                datos = datos + archivoIn.readLine() + "\n";
            archivoIn.close();
        }
        catch(IOException ioe){
            datos = "Error: "+ioe;
            System.out.println("Error: "+ioe);
        }
        return datos;
    }
    
    public void datosLLRetirosArchRetiros()
    {
        if(primeroRetiro == null)
            System.out.println("Lista Retiros vacia...");
        else{
            try{
                archivoOut = new PrintWriter(new FileWriter("Retiros.txt",true)); 
                actualRetiro = primeroRetiro;
                while(actualRetiro != null){
                    archivoOut.println(actualRetiro.toString());
                    actualRetiro = actualRetiro.getNext();
                }
                archivoOut.close();
                System.out.println("Datos almacenados en el archivo Retiros.txt...");
            }
            catch(IOException ioe){
                System.out.println("Error: "+ioe);
            }
        }
    }
    
    public String consultarArchivoRetiros()
    {
        String datos="";
        
        try
        {
            archivoIn = new BufferedReader(new FileReader("Retiros.txt"));
            
            while(archivoIn.ready())
                datos = datos + archivoIn.readLine() + "\n";
            
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            datos = "Error: "+ioe;
            System.out.println("Error: "+ioe);
        }
        
        return datos;
    }
    
}














