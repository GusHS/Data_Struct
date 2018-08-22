import java.io.*;
import java.util.StringTokenizer;

public class BancoAD{

    private static final int NUMCUENTA_POS  = 0;
    private static final int NOMCUENTA_POS  = 1;
    private static final int TIPCUENTA_POS  = 2;
    private static final int SALCUENTA_POS  = 3;

    private PrintWriter archivoOut;
    private BufferedReader archivoIn;
    private String arrayClientes[];
    private int lnNumber,posicion;

    public String capturar (String datos){
        String resultado="";
        try {
        //1. Abrir archivo.
        archivoOut = new PrintWriter(new FileWriter("Clientes.txt",true));
        //2. Almacenar o guardar los datos en el archivo.
        archivoOut.println(datos);
        //3. Cerrar el archivo
        archivoOut.close();
        //4. Entregar el resultado de la transacción.
        resultado = "Captura de datos exitosa";
        } catch (Exception ioe) {
            System.out.println("Error: "+ ioe);
            resultado = "Error en captura de datos: \n";
        }
        return resultado;
    }
    public String consultarClientes() {
        String datos="";
        lnNumber = 0;
        try {
            //1.Abrir el archivo
            archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
            //2. Obtener los datos del archivo
            while (archivoIn.ready()) {
                datos+=archivoIn.readLine()+"\n";
            }
            //3. Cerrar el archivo
            archivoIn.close();
        } catch (Exception ioe) {
            System.out.println("Error"+ioe);
        }
        //4.Entregar los datos.
        return datos;
    }

    public void setStringLength(){
        lnNumber=0;
        try {
            //1.Abrir el archivo
            archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
            //2. Obtener los datos del archivo
            while (archivoIn.ready()) {
                archivoIn.readLine();
                lnNumber++;
            }
            //3. Cerrar el archivo
            archivoIn.close();
        } catch (Exception ioe) {
            System.out.println("Error"+ioe);
        }
        System.out.println("String length set");
    }

    public String datosArchivoArreglo(){
        String str="";
        String resultado="";
        int i= 0;
        setStringLength();
        arrayClientes = new String[lnNumber];
        try {
            archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
            while(archivoIn.ready()){
                str=archivoIn.readLine();
                arrayClientes[i]=str;
                i++;
            }
            archivoIn.close();
        } catch (Exception ioe) {
            System.out.println("Error:"+ioe);
        }
        resultado = "Datos en arrayClientes: "+ i;
        return resultado;
    }
    public String displayStringArray(){
       datosArchivoArreglo();
       String datos="";
       for (int i = 0; i < arrayClientes.length; i++) {
           datos+=arrayClientes[i]+"\n";
       }
        return datos;
    }
    public String consultarNcta(String ncta){
        datosArchivoArreglo();
        String str="";
        String datos="";
        String nocta="";
        boolean encontrado = false;
        StringTokenizer st = null;

        int i=0;
        while (i<arrayClientes.length && !encontrado) {
            str= arrayClientes[i];
            st = new StringTokenizer(str,"_");
            nocta = st.nextToken();
            if (ncta.equals(nocta)) {
                posicion = i;
                encontrado = true;
            }
            i++;
            datos = str;
        }
        if (!encontrado) {
            datos = "NO_LOCALIZADO";
        }
        System.out.println("Found in pos:" + posicion);
     return datos;
    }
    public String depositar(String ncta, int cantidad){
        consultarNcta(ncta);
        int saldoActual;
        String updatedValue="";
        String tempArray[] = arrayClientes[posicion].split("_");
        saldoActual =Integer.parseInt(tempArray[SALCUENTA_POS]);
        if (tempArray[TIPCUENTA_POS].equals("AHORRO") || tempArray[TIPCUENTA_POS].equals("INVERSION") ) {
            saldoActual+=cantidad;
            tempArray[SALCUENTA_POS] = Integer.toString(saldoActual);
        }else{
            saldoActual-=cantidad;
            tempArray[SALCUENTA_POS] = Integer.toString(saldoActual);
        }
        int i = 0;
        for (i=0; i < tempArray.length-1; i++) {
            updatedValue+=tempArray[i]+"_";
        }
        updatedValue+=tempArray[i];
        System.out.println("Updated Value: "+updatedValue);
        arrayClientes[posicion] = updatedValue;
        String datos = "Valores Actualizados";
        return datos;
    }
    public String retirar(String ncta, int cantidad){
        consultarNcta(ncta);
        int saldoActual;
        String updatedValue="";
        String tempArray[] = arrayClientes[posicion].split("_");
        saldoActual =Integer.parseInt(tempArray[SALCUENTA_POS]);
        if (tempArray[TIPCUENTA_POS].equals("AHORRO") || tempArray[TIPCUENTA_POS].equals("INVERSION") ) {
            saldoActual-=cantidad;
            tempArray[SALCUENTA_POS] = Integer.toString(saldoActual);
        }else{
            saldoActual+=cantidad;
            tempArray[SALCUENTA_POS] = Integer.toString(saldoActual);
        }
        int i = 0;
        for (i=0; i < tempArray.length-1; i++) {
            updatedValue+=tempArray[i]+"_";
        }
        updatedValue+=tempArray[i];
        System.out.println("Updated Value: "+updatedValue);
        arrayClientes[posicion] = updatedValue;
        String datos = "Valores Actualizados";
        return datos;
    }

    public void arregloArchivo(){
        try {
            archivoOut = new PrintWriter(new FileWriter("Clientes.txt"));
            for (int i = 0; i < arrayClientes.length; i++) {
                archivoOut.print(arrayClientes[i]+"\n");
            }
        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }
        archivoOut.close();
    }
}