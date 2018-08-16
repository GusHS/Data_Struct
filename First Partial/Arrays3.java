import javax.swing.JOptionPane;

public class Arrays3{  
    private int numbers[] = new int[3];
   
    private void getData( int nums[]){
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(JOptionPane.showInputDialog("Numbers ["+i+"] = "));
        }
    }
    private void displayData(int nums[]){
        String str="[";
        int i=0;
        for (i=0; i < nums.length-1; i++)
            str+=nums[i]+",";
        
        str+= nums[i]+"]";
        JOptionPane.showMessageDialog(null, str);
    }

    public void principal() {
        getData(numbers);
        displayData(numbers);
    }


    public static void main(String[] args) {
        Arrays3 object = new Arrays3();
        object.principal();
    }
}