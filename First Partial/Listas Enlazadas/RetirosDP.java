import java.util.StringTokenizer;

public class RetirosDP {
    //Atributos
    private String nocta, nombre, tipo, fecha;
    private int saldoActual, saldoAnterior;
    private RetirosDP next;

    //Constructores
    public RetirosDP(){
        this.nocta ="";
        this.nombre = "";
        this.tipo ="";
        this.saldoActual = 0;
        this.saldoAnterior = 0;
        this.fecha ="";
        this.next = null;
    }

    public RetirosDP(String datos){
        StringTokenizer st = new StringTokenizer(datos, "_");
        this.nocta  = st.nextToken();
        this.nombre = st.nextToken();
        this.tipo   = st.nextToken();
        this.saldoAnterior = Integer.parseInt(st.nextToken());
        this.saldoActual  = Integer.parseInt(st.nextToken());
        this.fecha = st.nextToken();
    }
    //Métodos
    public String getNocta(){
        return this.nocta;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getTipo(){
        return this.tipo;
    }
    public String getFecha(){
        return this.getFecha();
    }
    public int getSaldoAnterior(){
        return this.saldoAnterior;
    }
    public int getSaldoActual(){
        return this.saldoActual;
    }
    public RetirosDP getNext(){
        return this.next;
    }
    
    // Mutators
    public void setNocta(String cta) {
        this.nocta = cta;
    }
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public void setTipo(String tcta) {
        this.tipo = tcta;
    }
    public void setFecha(String date) {
        this.fecha = date;
    }
    public void setSaldoAnterior(int cantidad){
        this.saldoAnterior = cantidad;
    }
    public void setSaldoActual(int cantidad){
        this.saldoActual = cantidad;
    }
    public void setNext(RetirosDP nodo){
        this.next = nodo;
    }
    public String toString(){
        return this.nocta+"_"+this.nombre+"_"+this.tipo+"_"+this.saldoAnterior + "_"+this.saldoActual+"_"+this.fecha;
    }
}