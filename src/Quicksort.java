import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Quicksort {

    public static void main(String[] args){
       /* Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList();

        boolean error = false;
        while (scanner.hasNext() && !error){
            try{
                list.add(scanner.nextInt());
            } catch (InputMismatchException e){
                System.out.println("Eingabe muss ein int sein");
                error = true;
            }
        }
        if(!error) {
            int[] array = new int[list.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }

        }*/
        int [] data = new int[] {5,8, 1,4,4, 9,2,3};
        qsort(data);
        System.out.println(Arrays.toString(data));
    }


    public static int partition(int data[], int l, int r){
        int p = data[l];
        int pIndex = l;
        int temp;
        if(r-l > 1) {
            for (int i = l; i <= r; i++) {
                if (data[i] >= p && pIndex != i) {
                    data[pIndex] = data[i];
                    temp = data[pIndex + 1];
                    data[i] = temp;
                    data[pIndex + 1] = p;
                    pIndex++;
                }
            }

        } else{
            if(data[l] < data[r]){
                temp = data[l];
                data[l] = data[r];
                data[r]= temp;
            }
        }

        return pIndex;
    }

    public static void qsort(int[] data, int l, int r){
        if(l< r){
            int m = partition(data, l, r);
            qsort(data, l, m-1);
            qsort(data, m+1, r);
        }

    }

    public static void qsort(int[] data){
        qsort(data, 0, data.length-1);
    }


}
