import javax.swing.JOptionPane;

public class Arrays2{
    private static int numbers[] = new int[3];
   
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


    public static void main(String[] args) {
        Arrays2 object = new Arrays2();

        object.getData(numbers);
        object.displayData(numbers);
    }
}