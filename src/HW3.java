import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class HW3 {

    private static int[] originalArray;
    private static int[] array;

    public static void main(String[] args) {
        algorithms("input_16.txt");
        System.out.println();
        algorithms("input_32.txt");
        System.out.println();
        algorithms("input_64.txt");
        System.out.println();
        algorithms("input_128.txt");
        System.out.println();
        algorithms("input_256.txt");
        System.out.println();
        algorithms("input_512.txt");
        System.out.println();
        algorithms("input_1024.txt");
        System.out.println();
        algorithms("input_2048.txt");
        System.out.println();
        algorithms("input_4096.txt");
        System.out.println();
        algorithms("input_8192.txt");
        System.out.println();
        algorithms("Input_Random.txt");
        System.out.println();
        algorithms("Input_ReversedSorted.txt");
    }
    public static void algorithms(String path) {
        try (Scanner reader = new Scanner(Paths.get(path))) {
            while (reader.hasNextLine()) {
            String intString = reader.nextLine();
            intString = intString.trim();
            String[] stringArray = intString.split("\\s"); //splits the string at each space and adds each individual string to an array
            originalArray = new int[stringArray.length]; //initializes array to size of the string array read from the file
            array = new int[stringArray.length]; //initializes a copy of the original array
            for(int i = 0; i < stringArray.length; i++) { //converts each string in the array to an int and adds to two new identical arrays
                String string = stringArray[i];
                originalArray[i] = Integer.parseInt(string);
                array[i] = Integer.parseInt(string);
            }
        }
        reader.close();
        long timeInit = System.nanoTime(); //records initial system time in nanoseconds
        selectionSort(array); 
        long timeFinal = System.nanoTime(); // records final system time in nanoseconds
        long time = timeFinal - timeInit; //calculates time taken for insertion sort algorithm
        printArray(array);
        System.out.println("Array Size: " + (array.length));
        System.out.println("Insertion Sort Time:" + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
        array = originalArray; //resets the array
        } catch (IOException e) {
            e.printStackTrace();
       } 
    }

    public static void selectionSort(int[] array) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[min]) {
                    min  = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    public static void printArray(int[] array) {
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s = s + array[i] + " ";
        }
        System.out.println(s);
    }

}
