import javax.swing.JOptionPane;

public class Arrays5{
    private int numbers1[];
    private int numbers2[];

    public Arrays5(){
        numbers1 = new int[3];
        numbers2 = new int [3];
    }
    private void copy(int nums1[], int nums2[]){
        for (int i = 0; i < nums1.length; i++) {
            nums2[i] = nums1[i];
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
        copy(numbers1,numbers2);
        displayData(numbers2);
    }


    public static void main(String[] args) {
        Arrays5 object = new Arrays5();
        object.principal();
    }


}