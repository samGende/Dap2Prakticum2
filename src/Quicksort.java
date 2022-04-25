import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Quicksort {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList();

        boolean error = false;
        while (scanner.hasNext() && !error){
            try{
                list.add(scanner.nextInt());
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
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
            //wenn das array kurzer als 20 ist ausdrucken
            if(array.length <= 20) {
                System.out.println(Arrays.toString(array));
            }
        }

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

    public static int partition(int data[], int l, int r){
        int p = data[l];
        int pIndex = l;
        int temp;
        //prufen ob es nur zwei elemente ins sub array gibt
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

        }
        //wenn es nur zwei element gibt check dass sie in richtige ordnung sind
        else{
            if(data[l] < data[r]){
                temp = data[l];
                data[l] = data[r];
                data[r]= temp;
            }
        }

        return pIndex;
    }

    public static void qsort(int[] data, int l, int r){
        //qsort recusive anrufen
        if(l< r){
            int m = partition(data, l, r);
            qsort(data, l, m-1);
            qsort(data, m+1, r);
        }

    }

    public static void qsort(int[] data){
        Instant start = Instant.now();
        qsort(data, 0, data.length-1);
        Instant end = Instant.now();
        long time = Duration.between(start, end).toMillis();
        System.out.println("qsort took" +  time+ " ms");
    }


}
