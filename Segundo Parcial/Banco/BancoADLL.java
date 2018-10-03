import java.util.*;

public class BancoADLL {
    private LinkedList listaClientes = new LinkedList();
    private int nodoActual;

    public String captura(String datos) {
        listaClientes.add(new ClienteDP(datos));
        return "Nuevo nodo creado en la LinkedList";
    }
    public String  consultarClientes() {
        String datos="";
        if (listaClientes.isEmpty()) {
            datos = "Lista Vacia";
        }else{
            int i=0;
            while (i<listaClientes.size()) {
                datos+=listaClientes.get(i).toString()+"\n";
                i++;
            }
        }
        return datos;
    }
    public String addFirst(String datos){
        listaClientes.addFirst(new ClienteDP(datos));
        return "Nuevo Cliente creado al Inicion de la LinkedList";
    }
    
    public String consultarNocta(String ncta) {
        String datos="";
        boolean encontrado = false;
        ClienteDP actual;

        if (listaClientes.isEmpty()){
            datos ="EMPTY";
        }else{
            int i=0;
            while (i<listaClientes.size() && !encontrado) {
                actual = (ClienteDP)listaClientes.get(i);
                if (ncta.equals(actual.getNocta())){
                    datos = actual.toString()+"\nActual es el nodo: "+i;
                    encontrado = true;
                    nodoActual = i;
                }else{
                    i++;
                    nodoActual = i;
                }
            }
            if(!encontrado)
                datos = "NOT_FOUND";
        }
        return datos;
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
    public String eliminarNodoCliente() {
        listaClientes.remove(nodoActual);
        return "Nodo Cliente eliminado: "+nodoActual;
    }
    public String depositar(int cantidad){
        ClienteDP actual;
        String datos="";
        actual = (ClienteDP) listaClientes.get(nodoActual);
        datos = actual.toString();
        
        if(actual.getTipo().equals("INVERSION") || actual.getTipo().equals("AHORRO"))
            actual.setSaldo(actual.getSaldo() + cantidad);
        else
            actual.setSaldo(actual.getSaldo() - cantidad);
        
            datos = datos+"_"+actual.getSaldo();
        return datos;
    }
    public String retirar(int cantidad){
        ClienteDP actual;
        String datos="";
        actual = (ClienteDP) listaClientes.get(nodoActual);
        datos = actual.toString();
        
        if(actual.getTipo().equals("INVERSION") || actual.getTipo().equals("AHORRO"))
            actual.setSaldo(actual.getSaldo() - cantidad);
        else
            actual.setSaldo(actual.getSaldo() + cantidad);
            
        datos = datos+"_"+actual.getSaldo();
        return datos;
    }
}