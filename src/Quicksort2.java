import sun.jvm.hotspot.utilities.Assert;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Quicksort2 {

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
        assert isSorted(data);
        System.out.println(Arrays.toString(data));
    }


    public static int[] partition(int data[], int l, int r){
        int p1 = l;
        int p2 = r;
        int p1Index = l;
        int p2Index = r;
        if(data[l] == Math.max(data[l], data[r])){
            p1 = data[l];
            p1Index = l;
            p2 = data[r];
            p2Index = r;
        } else {
            p1 = data[r];
            p1Index = r;
            p2 = data[l];
            p2Index = l;
        }

        int temp;
        if(r-l > 1) {
            for (int i = l; i <= r; i++) {

                if(data[i]> p1){
                    data[p1Index]= data[i];
                    data[i] = data[p1Index+1 ];
                    data[p1Index+1] = p1;

                } else if(data[i]< p2){
                    data[p2Index] = data[i];
                    data[i] = data[p2Index+1];
                    data[p2Index+1] = p2;
                }

            }

        } else{
            if(data[l] < data[r]){
                temp = data[l];
                data[l] = data[r];
                data[r]= temp;
            }
        }

        return new int[] {p1Index, p2Index};
    }

    public static void qsort(int[] data, int l, int r){
        if(l< r){
            int[] m = partition(data, l, r);
           qsort(data, l, m[1]);
            qsort(data, m[1], m[2]);
            qsort(data, m[2], l);
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
