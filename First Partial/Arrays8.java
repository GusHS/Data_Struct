import javax.swing.JOptionPane;
import java.util.Arrays;

public class Arrays8{
    private int numbers1[];
    private String names[];

    public Arrays8(String data[]){
        names = new String[data.length];
        names = data; 
    }
    private void copy(int nums1[], int nums2[]){
        for (int i = 0; i < nums1.length; i++) {
            nums2[i] = nums1[i];
        }
    }
    private void inverseCopy(int nums1[], int nums2[]) {
        int pos = nums1.length-1;
        for (int i = 0; i < nums1.length; i++) {
            nums2[i] = nums1[pos];
            pos--;
        }
    }
    
    private void sort(int nums[]) {
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
    }
    private void strToIntArray(String data[], int num[]){
        for (int i = 0; i < data.length; i++) {
            num[i] = Integer.parseInt(data[i]);
        }
    }
    private void getData( int nums[]){
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(JOptionPane.showInputDialog("Numbers ["+i+"] = "));
        }
    }
    private void displayDataString(String nms[]){
        String str = "[";
        int i=0;
        for (i = 0; i < nms.length-1; i++)
            str+=nms[i]+",";
        str+=nms[i]+"]";
        JOptionPane.showMessageDialog(null,"DATA:"+str);
    }
    private void displayDataInt(int nums[]){
        String str="[";
        int i=0;
        for (i=0; i < nums.length-1; i++)
            str+=nums[i]+",";
        
        str+= nums[i]+"]";
        JOptionPane.showMessageDialog(null, str);
    }
    public void findIntegerData(int nums[]){
        int value = Integer.parseInt(JOptionPane.showInputDialog("Search Value: "));
        String output="";
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if (value == nums[i]) {
                output+="Value "+value+" found in postion ["+ i +"]\n";
                count++;
            }
        }
        if(count!=0){
            output+="\n Data was found "+count+" time(s).";
        }else{
            output+="Data was not found";
        }

        
        JOptionPane.showMessageDialog(null, output);
    }

    public void findString(String data[]) {
        String str = JOptionPane.showInputDialog(null, "String to find: ");
        String output="";
        int counter=0;
        for (int i = 0; i < data.length; i++) {
            if (str.equals(data[i])) {
                output+="String '"+str + "' found in postion ["+ i +"]\n";
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

    public void principal(){
        displayDataString(names);
        findString(names);
    }
    public static void main(String[] args) {
        Arrays8 object = new Arrays8(args);
        object.principal();
    }
}