import javax.swing.JOptionPane;
import java.util.Arrays;

public class Arrays6{
    private int numbers1[];
    private int numbers2[];

    public Arrays6(){
        numbers1 = new int[3];
        numbers2 = new int [3];
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
        getData(numbers1);
        displayData(numbers1);
        inverseCopy(numbers1,numbers2);
        displayData(numbers2);
        sort(numbers2);
        displayData(numbers2);
    }

    public static void main(String[] args) {
        Arrays6 object = new Arrays6();
        object.principal();
    }
}