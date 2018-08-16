import javax.swing.JOptionPane;

public class Arrays4{
    
    private int numbers[];

    public Arrays4(){
        numbers = new int[]{40,20,60};
    }


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
        Arrays4 object = new Arrays4();
        object.principal();
    }
}