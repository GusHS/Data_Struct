import javax.swing.JOptionPane;

public class Arrays {
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
        Arrays object = new Arrays();
        int numbers[] = new int[3];

        object.getData(numbers);
        object.displayData(numbers);
    }
}