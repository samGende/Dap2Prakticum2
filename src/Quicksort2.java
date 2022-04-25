
import java.awt.*;
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
                System.out.println(e.getStackTrace());
                System.out.println("Eingabe muss ein int sein");
                error = true;
            }
        }
        if(!error) {
            int[] array = new int[list.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }
            //array sortierent mit quicksort 2 piviots
            qsort(array);
            //assert dass den Array sortiert ist
            assert isSorted(array);
            //wenn das array kurzer als 20 ist ausdrucken
            if(array.length <= 20) {
                System.out.println(Arrays.toString(array));
            }
            //median von denn array finden
            double median = median(array);
            // max median und min von sortiertes array finden
            System.out.println("Max:" + array[0]+ " Median:" + median + " Min:" + array[array.length-1]);

       }

    }

    public static double median(int[] array){
        //wenn array ein gerade lange hat dann die durchschnitt von die zwei
        // mittere Zahlen nehmen
        if(array.length%2 == 0){
            return array[array.length/2] + array[array.length/2 + 1] / 2.0;
        }
        // sonst die median ist array.lenght/2 weil integer division
        else {
            return array[array.length/2];
        }
    }


    public static int[] partition(int data[], int l, int r){
        // piviots in die richtige stelle stellen
        if(data[l] < data[r]){
            swap(data, l, r);
        }
        int p1 = data[l];
        int p2 = data[r];
        int p1Index = l + 1;
        int p2Index = r -1;
        int i = p1Index;

        while (i<= p2Index){
            // wenn i grosser als erste pivot dann mit p1Index tauschen und p1Index anpassen
            if(data[i]>= p1){
                swap(data, p1Index, i);
                p1Index++;
            }
            // wenn i kleine gleich dass zweite pivot ist in denn richtigen platz setzen
            else if(data[i] <= p2){
                // i zum richtigen index tauschen  dann p2Index anpassen
                while(data[p2Index] < p2&& i < p2Index){
                    p2Index--;
                }
                swap(data, i, p2Index);
                p2Index--;
                // wenn die wert die mit i vertauscht wurde grosser als p1 ist, dann
                // in die richtigen platz vor p1 setzen
                if(data[i] > p1){
                    swap(data, i, p1Index);
                    p1Index++;
                }
            }
            i++;
        }
        p1Index--;
        p2Index++;

        //pivots sind immer noch an l und r indexes werden dann zu die richtigen gesetzt
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
        //q sort wiederrufen bis die pivots gleich sind dann ist die array sortiert
        if(l< r){
            int[] m = partition(data, l, r);
           qsort(data, l, m[0] -1);
            qsort(data, m[0] +1, m[1]-1);
            qsort(data, m[1]+1, r);
        }

    }

    public static void qsort(int[] data){
        // qsort rufen und messen wie lang es dauert denn array zu sortieren
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
