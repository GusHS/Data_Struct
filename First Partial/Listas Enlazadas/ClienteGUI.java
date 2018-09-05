import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteGUI extends JFrame implements ActionListener
{
    private JTextField tfNocta, tfNombre, tfTipo, tfSaldo;
    private JButton    bCapturar, bConsultar, bSalir, bConsultarArchivo, bConsultarNocta;
    
    private JPanel      panel1, panel2;
    private JTextArea   taDatos;
    private JComboBox   comboCuentas;
    private String      opcionesCuenta[] = {"INVERSION","CREDITO","AHORRO","HIPOTECA"};
    
    private BancoAD bancoad = new BancoAD();
    
    public ClienteGUI()
    {
        super("BANCO VB CITY LL");
        
        // 1. Crear los objetos de los atributos
        tfNocta = new JTextField();
        tfNombre = new JTextField();
        tfTipo   = new JTextField();
        tfSaldo  = new JTextField();
        bCapturar = new JButton("Capturar datos (LList)");
        bConsultar = new JButton("Consultar Clientes (LList)");
        bConsultarArchivo = new JButton("Consultar Archivo");
        bConsultarNocta = new JButton("Consultar No. Cuenta");
        bSalir = new JButton("Exit");
        panel1 = new JPanel();
        panel2 = new JPanel();
        taDatos = new JTextArea(10,30);
        comboCuentas = new JComboBox(opcionesCuenta);
        
        // Adicionar addActionListener a lo JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bSalir.addActionListener(this);
        bConsultarArchivo.addActionListener(this);
        bConsultarNocta.addActionListener(this);
        // 2. Definir los Layouts de los JPanels
        panel1.setLayout(new GridLayout(8,2));
        panel2.setLayout(new FlowLayout());
        
        // 3. Colocar los objetos de los atributos en los JPanels correspondientes
        panel1.add(new JLabel("No. DE CUENTA"));
        panel1.add(tfNocta);
        panel1.add(new JLabel("NOMBRE: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("TIPO DE CUENTA"));
        //panel1.add(tfTipo);
        panel1.add(comboCuentas);
        panel1.add(new JLabel("SALDO"));
        panel1.add(tfSaldo);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarArchivo);
        panel1.add(bConsultarNocta);
        panel1.add(bSalir);
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        // 4. Adicionar el panel2 al JFrame y hacerlo visible
        add(panel2);
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private String obtenerDatos()
    {
        String datos;
        
        String nocta  = tfNocta.getText();
        String nombre = tfNombre.getText();
        //String tipo   = tfTipo.getText();
        String tipo   = (String)comboCuentas.getSelectedItem();
        String saldo  = tfSaldo.getText();
        
        if (!bancoad.consultarNocta(tfNocta.getText()).equals("No se localizo el numero de cuenta")) {
            datos = "VALOR_EXISTENTE";
        }
        else
        if(nocta.equals("") || nombre.isEmpty() || tipo.equals("") || saldo.isEmpty())
            datos = "VACIO";
        else
        {
            try
            {
                int n = Integer.parseInt(saldo);
                datos = nocta+"_"+nombre+"_"+tipo+"_"+saldo;
            }
            catch(NumberFormatException nfe)
            {
                datos = "NO_NUMERICO";
            }
        }
        
        return datos;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String datos, respuesta;
        
        if(e.getSource() == bCapturar)
        {
            // 1. Obtener datos
            datos = obtenerDatos();
            
            // 2. Checar datos: datos no vacios y saldo numerico, y realizar la captura de datos
            if(datos.equals("VACIO"))
                respuesta = "Algun campo esta vacio...";
            else
                if(datos.equals("NO_NUMERICO"))
                    respuesta = "Saldo debe ser numerico...";
                    else
                        if (datos.equals("VALOR_EXISTENTE"))
                            respuesta = "Ya existe ese numero de cuenta";
                            else
                            respuesta = bancoad.capturar(datos);
                    
            
            // 3. Desplegar esultado de transaccion
            taDatos.setText(respuesta);
        }
        
        if(e.getSource() == bConsultar)
        {
            // 1. Realizar consulta de clientes
            datos = bancoad.consultarClientes();
            
            // 2. Desplegar datos
            taDatos.setText(datos);
            //taDatos.setText("Consultar Clientes ...");
        }
        
        if(e.getSource() == bConsultarArchivo){
            taDatos.setText(bancoad.consultarArchivo());
        }
        if (e.getSource() == bConsultarNocta) {
            taDatos.setText(bancoad.consultarNocta(tfNocta.getText()));
        }
        if(e.getSource() == bSalir){
            bancoad.saveOnFile();
            System.exit(0);
        }
    }
    
    public static void main(String args[])
    {
        new ClienteGUI();
    }
}



