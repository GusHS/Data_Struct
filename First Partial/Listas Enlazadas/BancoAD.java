import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.StringTokenizer;

import jdk.jfr.events.FileWriteEvent;

public class BancoAD{
    //Atributos
    private ClienteDP   primeroCDP, ultimoCDP, actualCDP, pasadoCDP;
    private DepositosDP primeroDDP, ultimoDDP, actualDDP;
    private RetirosDP   primeroRDP, ultimoRDP, actualRDP;
    private PrintWriter archivoOut;
    private BufferedReader archivoIn;
    private Boolean encontrado;
    
    //Constructor
    public BancoAD(){
        try {
            //Read Clients
            archivoIn = new BufferedReader(new FileReader("Datos.txt"));
            while(archivoIn.ready()){
                capturarCDP(archivoIn.readLine());
            }
            archivoIn.close();

            //Read Deposits
            archivoIn = new BufferedReader(new FileReader("Depositos.txt"));
            while(archivoIn.ready()){
                capturarDDP(archivoIn.readLine());
            }
            archivoIn.close();

            //Read Retiros
            archivoIn = new BufferedReader(new FileReader("Retiros.txt"));
            while(archivoIn.ready()){
                capturarRDP(archivoIn.readLine());
            }
            archivoIn.close();

        } catch (Exception e) {
           System.out.println("Exception: " + e );
        }
    }

    //Metodos
    public String capturarCDP(String datos){
        if (primeroCDP == null) {
            primeroCDP = new ClienteDP(datos);
            ultimoCDP = primeroCDP;
            ultimoCDP.setNext(null);
        }else{
            actualCDP = new ClienteDP(datos);
            ultimoCDP.setNext(actualCDP);
            ultimoCDP = actualCDP;
            ultimoCDP.setNext(null);
        }
        return "Datos: " + datos;    
    }
    public String capturarDDP(String datos){
        if (primeroDDP == null) {
            primeroDDP = new DepositosDP(datos);
            ultimoDDP = primeroDDP;
            ultimoDDP.setNext(null);
        }else{
            actualDDP = new DepositosDP(datos);
            ultimoDDP.setNext(actualDDP);
            ultimoDDP = actualDDP;
            ultimoDDP.setNext(null);
        }
        return "Datos: " + datos;
    }
    public String capturarRDP(String datos){
        if (primeroRDP == null) {
            primeroRDP = new RetirosDP(datos);
            ultimoRDP = primeroRDP;
            ultimoRDP.setNext(null);
        }else{
            actualRDP = new RetirosDP(datos);
            ultimoRDP.setNext(actualRDP);
            ultimoRDP = actualRDP;
            ultimoRDP.setNext(null);
        }
        return "Datos: " + datos;
    }
    public String consultarNocta(String numCuenta){
        String datos="No se localizo el numero de cuenta", nocta="";
        if (primeroCDP == null) {
          datos = "Lista vacia...";
        }else{
            actualCDP = primeroCDP;
            encontrado = false;
            while (actualCDP != null && !encontrado) {
                nocta = actualCDP.getNocta();
                if (nocta.equals(numCuenta)){
                    datos = actualCDP.toString();
                    encontrado = true;
                    System.out.println(actualCDP.toString());
                }
                else{
                    pasadoCDP = actualCDP;
                    actualCDP = actualCDP.getNext();     
                }
                    
            }
        }
        return datos;
    }
    public String consultarClientes(){
        String datos ="";
        if (primeroCDP == null) {
            datos = "Lista vacia...";
        }else{
            actualCDP = primeroCDP;
            while (actualCDP != null) {
                datos = datos +actualCDP.toString()+"\n";
                actualCDP = actualCDP.getNext();
            }
        }
        return datos;
    }
    public String consultarRetiros(){
        String datos ="";
        if (primeroRDP == null) {
            datos = "Lista vacia...";
        }else{
            actualRDP = primeroRDP;
            while (actualRDP != null) {
                datos = datos +actualRDP.toString()+"\n";
                actualRDP = actualRDP.getNext();
            }
        }
        return datos;
    }
    public String consultarDepositos(){
        String datos ="";
        if (primeroDDP == null) {
            datos = "Lista vacia...";
        }else{
            actualDDP = primeroDDP;
            while (actualDDP != null) {
                datos = datos +actualDDP.toString()+"\n";
                actualDDP = actualDDP.getNext();
            }
        }
        return datos;
    }
    public String saveOnFileCDP(){
        String datos="";
        try {
            if(primeroCDP == null){
                datos = "Sin datos por escribir";
            }else{
                actualCDP = primeroCDP;
                archivoOut = new PrintWriter(new FileWriter("Datos.txt"));
                while(actualCDP != null){
                    archivoOut.println(actualCDP.toString());
                    actualCDP = actualCDP.getNext();
                }
                archivoOut.close();
                datos = "Datos escritos.";
            }
        } catch (Exception e) {
           System.out.println("Error: " + e);
        }
        return datos;
    }
    public String saveOnFileDDP(){
        String datos="";
        try {
            if(primeroDDP == null){
                datos = "Sin datos por escribir";
            }else{
                actualDDP = primeroDDP;
                archivoOut = new PrintWriter(new FileWriter("Depositos.txt"));
                while(actualDDP != null){
                    archivoOut.println(actualDDP.toString());
                    actualDDP = actualDDP.getNext();
                }
                archivoOut.close();
                datos = "Datos escritos.";
            }
        } catch (Exception e) {
           System.out.println("Error: " + e);
        }
        return datos;
    }
    public String saveOnFileRDP(){
        String datos="";
        try {
            if(primeroRDP == null){
                datos = "Sin datos por escribir";
            }else{
                actualRDP = primeroRDP;
                archivoOut = new PrintWriter(new FileWriter("Retiros.txt"));
                while(actualRDP != null){
                    archivoOut.println(actualRDP.toString());
                    actualRDP = actualRDP.getNext();
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
    public String deposito(String ctd){
        String datos = "";
        int saldoAnterior=actualCDP.getSaldo();
        int cantidad = Integer.parseInt(ctd);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String transactionDate = df.format(today); 

        if (actualCDP.getTipo().equals("INVERSION") || actualCDP.getTipo().equals("AHORRO")){
            actualCDP.setSaldo(actualCDP.getSaldo()+cantidad);
        }else{
            actualCDP.setSaldo(actualCDP.getSaldo()-cantidad);
        }
        datos = actualCDP.getNocta()+"_"+actualCDP.getNombre()+"_"+actualCDP.getTipo()+"_"+saldoAnterior+"_"+actualCDP.getSaldo()+"_"+transactionDate;
        // Creación de Elementos Depositos DP.
        capturarDDP(datos);

        return "Deposito exitoso";
    }
    public String retiro(String ctd){
        String datos="";
        int cantidad = Integer.parseInt(ctd);
        int saldoAnterior=actualCDP.getSaldo();

        
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String transactionDate = df.format(today); 

        if (!actualCDP.getTipo().equals("HIPOTECA")) {
            if (actualCDP.getTipo().equals("INVERSION") || actualCDP.getTipo().equals("AHORRO")){
                actualCDP.setSaldo(actualCDP.getSaldo()-cantidad);
            }else{
                actualCDP.setSaldo(actualCDP.getSaldo()+cantidad);
            }
            datos = "Operacion exitosa";
        }
        else{
            datos = "No se puede realizar un retiro en la hipoteca";
        } 
        //Creación de Elementos Retiros DP
        datos = actualCDP.getNocta()+"_"+actualCDP.getNombre()+"_"+actualCDP.getTipo()+"_"+saldoAnterior+"_"+actualCDP.getSaldo()+"_"+transactionDate;
        capturarRDP(datos);
        return datos;
    }
    public String addFirst(String datos){
        if (primeroCDP == null) {
            primeroCDP = new ClienteDP(datos);
            ultimoCDP = primeroCDP;
            ultimoCDP.setNext(null);
        }else{
            actualCDP = new ClienteDP(datos);
            actualCDP.setNext(primeroCDP);
            primeroCDP = actualCDP;
            ultimoCDP.setNext(null);
        }
        return primeroCDP.toString();
    }


    public String borrarCuenta(){
        // En caso de eliminar la primer cuenta
        if (actualCDP == primeroCDP) {
            System.out.println("first");
            primeroCDP = actualCDP.getNext();
        }//En caso de eliminar la ultima cuenta
        else if (actualCDP == ultimoCDP) {
            System.out.println("last");
            ultimoCDP = pasadoCDP;
            ultimoCDP.setNext(null);
        } //Cualquier otro caso
        else{
            System.out.println("OTHER");
            pasadoCDP.setNext(actualCDP.getNext());
        }
        try {
            archivoOut = new PrintWriter(new FileWriter("Bajas.txt",true));
            System.out.println(actualCDP.toString());
            
            archivoOut.println(actualCDP.toString());
            archivoOut.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        return "Cuenta "+ actualCDP.getNocta()+" ha sido eliminada";
    }
    public String consultaBajas(){
        String datos="";
        try {
            archivoIn = new BufferedReader(new FileReader("Bajas.txt"));
            while(archivoIn.ready()){
                datos+=archivoIn.readLine()+"\n";
            }
            archivoIn.close();
        } catch (Exception e) {
        }
        return datos;
    }
}