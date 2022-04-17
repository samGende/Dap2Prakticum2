
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Quicksort2 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
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
            qsort(array);
            assert isSorted(array);
            if(array.length <= 20) {
                System.out.println(Arrays.toString(array));
            }
            double median = median(array);
            System.out.println("Max:" + array[0]+ " Median:" + median + " Min:" + array[array.length-1]);

       }

    }

    public static double median(int[] array){
        if(array.length%2 == 0){
            return array[array.length/2] + array[array.length/2 + 1] / 2.0;
        } else {
            return array[array.length/2];
        }
    }


    public static int[] partition(int data[], int l, int r){
        if(data[l] < data[r]){
            swap(data, l, r);
        }
        int p1 = data[l];
        int p2 = data[r];
        int p1Index = l + 1;
        int p2Index = r -1;
        int i = p1Index;

        while (i<= p2Index){
            if(data[i]> p1){
                swap(data, p1Index, i);
                p1Index++;
            } else if(data[i] <= p2){
                while(data[p2Index] < p2&& i < p2Index){
                    p2Index--;
                }
                swap(data, i, p2Index);
                p2Index--;
                if(data[i] > p1){
                    swap(data, i, p1Index);
                    p1Index++;
                }
            }
            i++;
        }
        p1Index--;
        p2Index++;

        swap(data, l, p1Index);
        swap(data, r, p2Index);

        return new int[] {p1Index, p2Index};
    }

    private static void swap(int[] toSwap, int index1, int index2){
        int temp = toSwap[index1];
        toSwap[index1] = toSwap[index2];
        toSwap[index2] = temp;
    }

    public static void qsort(int[] data, int l, int r){
        if(l< r){
            int[] m = partition(data, l, r);
           qsort(data, l, m[0] -1);
            qsort(data, m[0] +1, m[1]-1);
            qsort(data, m[1]+1, r);
        }

    }

    public static void qsort(int[] data){
        Instant start = Instant.now();
        qsort(data, 0, data.length-1);
        Instant end = Instant.now();
        long time = Duration.between(start, end).toMillis();
        System.out.println("qsort took" +  time+ "ms");
    }

    public static boolean isSorted(int[] data){
        boolean isSorted = true;

        for(int i = 1; i < data.length; i++){
            if(data[i] > data[i-1]){
                isSorted = false;
            }
        }

        return isSorted;
    }


}
