import javax.swing.*;
import java.io.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArreglosGUI extends JFrame implements ActionListener
{
    private JMenuBar  menuBarOpciones;
    private JMenu     menu;
    private JMenuItem miNumero, miString, miSalir;
    
    private JPanel    panelDisplay,panelButtons,panelArray1, panelArray2;
    private JButton   bCopiar, bOrdenar, bBuscar;
    
    private JTextField tfArreglo1[];
    private JTextField tfArreglo2[];
    
    private String arreglo1[];
    private String arreglo2[];
    private int nums[];

    private String type="";
    private Boolean canBeSorted=false;

    public ArreglosGUI(String[] data)
    {
        super("ARREGLOS UNIDIMENSIONALES");

        // 1. Crear objetos
        menuBarOpciones = new JMenuBar();
        menu            = new JMenu("ARREGLOS:");
        miString        = new JMenuItem("Strings");
        miNumero        = new JMenuItem("Numeros");
        miSalir         = new JMenuItem("Exit");
        bCopiar         = new JButton("Copiar Datos");
        bOrdenar        = new JButton("Ordenar Arreglo 2");
        bBuscar         = new JButton("Buscar en Arreglo 1");
        panelArray1     = new JPanel();
        panelArray2     = new JPanel();
        panelButtons    = new JPanel();
        panelDisplay    = new JPanel();
        
        arreglo1 = data;
        arreglo2 = new String[data.length];

        tfArreglo1 = new JTextField[data.length];
        
        tfArreglo2 = new JTextField[data.length];
        
        // Adicionar ActionListener a los botones
        bCopiar.addActionListener(this);
        bOrdenar.addActionListener(this);
        bBuscar.addActionListener(this);
        
        // Adicionar listener a los menu items
        miString.addActionListener(this);
        miNumero.addActionListener(this);
        miSalir.addActionListener(this);
        
        // 2. Adicionar JMenuItems a JMenu
        menu.add(miNumero);
        menu.add(miString);
        menu.add(miSalir);

        // 3. Adicionar JMenu a JMenuBar
        menuBarOpciones.add(menu);
        panelDisplay.setVisible(false);
        add(panelDisplay);
        // 4. Adicionar JMenuBar al JFrame
        setJMenuBar(menuBarOpciones);

        setSize(350,250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
    }
    private void setData(){
        panelArray1.removeAll();
        panelArray2.removeAll();
        panelButtons.removeAll();

        for (int i = 0; i < tfArreglo1.length; i++) 
            tfArreglo1[i]= new JTextField(arreglo1[i]);       
        
        for (int i = 0; i < tfArreglo2.length; i++) 
            tfArreglo2[i]= new JTextField(arreglo2[i]);
        
        panelArray1.setLayout(new GridLayout(1,arreglo1.length+1));
        panelArray1.add(new JLabel("Arreglo 1"));
        for (int i = 0; i < arreglo1.length; i++)
           panelArray1.add(tfArreglo1[i]);

        panelArray2.setLayout(new GridLayout(1,arreglo2.length+1));
        panelArray2.add(new JLabel("Arreglo 2"));
        for (int i = 0; i < arreglo2.length; i++) 
           panelArray2.add(tfArreglo2[i]);

        panelButtons.add(bCopiar);
        panelButtons.add(bBuscar);
        if(type.equals("Numbers")){
            panelButtons.add(bOrdenar);
        }
        
        panelDisplay.setLayout(new GridLayout(3,1));
        panelDisplay.add(panelArray1);
        panelDisplay.add(panelArray2);
        panelDisplay.add(panelButtons);
    }

    private void strInverseCopy(String array1[], String array2[]){
        int pos = array1.length-1;
        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[pos];
            pos--;
        }
    }

    public void sortString(String array[]) {
        nums = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            nums[i] = Integer.parseInt(array[i]);
        }

        for (int i = 0 ; i<nums.length-1; i++) {
            int min = i;
            for (int j=i+1 ; j < nums.length; j++) {
                if (nums[j] < nums[min]) min = j;
            }
            if (i != min) {
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.toString(nums[i]);
        }
    }

    public void findString(String data[]) {
        String str = JOptionPane.showInputDialog(null, "String to find: ");
        String output="";
        int counter=0;
        for (int i = 0; i < data.length; i++) {
            if (str.equals(data[i])) {
                output+="String '"+str + "' found in Arreglo1 postion ["+ i +"]\n";
                counter++;
            }
        }
        if (counter!=0) {
            output+= "\n Data was found "+counter+" times.";
        } else {
            output="Data was not found.";
        }
        JOptionPane.showMessageDialog(null, output);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == miNumero)
        {
            type ="Numbers";
            panelDisplay.setVisible(false);
            setData();
            panelDisplay.setVisible(true);
        }
        
        if(e.getSource() == miString)
        {
            type = "String";
            panelDisplay.setVisible(false);
            setData();
            panelDisplay.setVisible(true);
        }
        
        if (e.getSource()== bBuscar) {
            if (type.equals("String")) {
                findString(arreglo1);
            }            
            if (type.equals("Numbers")) {
                findString(arreglo1);
            }
            
        }

        if(e.getSource()==bCopiar){
            if (type.equals("String")) {
                panelDisplay.setVisible(false);
                panelDisplay.removeAll();
                strInverseCopy(arreglo1, arreglo2);
                setData();
                panelDisplay.setVisible(true);
            }
            if(type.equals("Numbers")){
                panelDisplay.setVisible(false);
                panelDisplay.removeAll();
                strInverseCopy(arreglo1, arreglo2);
                setData();
                panelDisplay.setVisible(true);
                canBeSorted = true;
            }
        }

        if(e.getSource() == bOrdenar && canBeSorted){
            panelDisplay.setVisible(false);
            panelDisplay.removeAll();
            sortString(arreglo2);
            setData();
            panelDisplay.setVisible(true);

        }

        if(e.getSource() == miSalir){
            System.exit(0);
        }
    }
    
    public static void main(String args[]){
        ArreglosGUI arreglo = new ArreglosGUI(args);
    }
}
