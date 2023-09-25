import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class HW3 {

    private static int[] originalArray;
    private static int[] array;

    public static void main(String[] args) {
        readFile("input_16.txt");
        System.out.println();
        readFile("input_32.txt");
        System.out.println();
        readFile("input_64.txt");
        System.out.println();
        readFile("input_128.txt");
        System.out.println();
        readFile("input_256.txt");
        System.out.println();
        readFile("input_512.txt");
        System.out.println();
        readFile("input_1024.txt");
        System.out.println();
        readFile("input_2048.txt");
        System.out.println();
        readFile("input_4096.txt");
        System.out.println();
        readFile("input_8192.txt");
        System.out.println();
        readFile("Input_Random.txt");
        System.out.println();
        readFile("Input_ReversedSorted.txt");
        System.out.println();
        readFile("Input_Sorted.txt");
    }

    /*
    Description: this function reads the file using the file parameter, converts strings from text file to ints and adds them to an array, then runs the algorithms and prints execution time and outputs
    Parameters:
    String file - this string represents the file name to be read
    Returns: nothing
     */
    public static void readFile(String file) {
        try (Scanner reader = new Scanner(Paths.get(file))) {
            while (reader.hasNextLine()) {
            String intString = reader.nextLine();
            intString = intString.trim(); //removes extra spaces from the beginning
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
        System.out.println("File: " + file);
        System.out.println("Array Size: " + (array.length));
        System.out.println("Insertion Sort Time: " + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
        array = originalArray; //resets the array

        timeInit = System.nanoTime(); //records initial system time in nanoseconds
        quickSort(array, 0, array.length-1); 
        timeFinal = System.nanoTime(); // records final system time in nanoseconds
        time = timeFinal - timeInit; //calculates time taken for insertion sort algorithm
        printArray(array);
        System.out.println("File: " + file);
        System.out.println("Array Size: " + (array.length));
        System.out.println("Quick Sort Time: " + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
        array = originalArray; //resets the array

        timeInit = System.nanoTime(); //records initial system time in nanoseconds
        medianOfThreeQuickSort(array, 0, array.length-1); 
        timeFinal = System.nanoTime(); // records final system time in nanoseconds
        time = timeFinal - timeInit; //calculates time taken for insertion sort algorithm
        printArray(array);
        System.out.println("File: " + file);
        System.out.println("Array Size: " + (array.length));
        System.out.println("Median of 3 Quick Sort Time: " + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
        array = originalArray; //resets the array

        } catch (IOException e) {
            e.printStackTrace();
       } 
    }

    /*
    Description: finds the minimum element in the unsorted portion of the array then swaps the minimum element in the unsorted portion with the first element in the unsorted portion
    Parameters:
    int[] array - the array of integers to be sorted 
    Returns: nothing
     */
    public static void selectionSort(int[] array) {
        int size = array.length; //sets size equal to  number of elements
        for (int i = 0; i < size - 1; i++) { //iterates over the whole array
            int min = i;  //sets smallest value to current index
            for (int j = i + 1; j < size; j++) { //iterates over the unsorted portion of the array
                if (array[j] < array[min]) { //if the current iteration value is smaller than the original minimum
                    min  = j; //set new minimum to new index
                }
            }
            int temp = array[i]; //creates temp index copy of the value of the current index
            array[i] = array[min]; //swaps the index of the original min to new min
            array[min] = temp; //sets the index of new min to original min
        }
    }

    /*
    Description: prints out each element in the array after it is sorted for testing purposes
    Parameters:
    int[] array - array that the function prints
    Returns: nothing
     */
    public static void printArray(int[] array) {
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s = s + array[i] + " ";
        }
        System.out.println(s);
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int x = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= x) {
                i++;
                int temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp; 
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void medianOfThreeQuickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = medianOfThree(array, low, high);
            int partitionIndex = partitionMedian(array, low, high, pivot);
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    public static int medianOfThree(int[] array, int low, int high) {
        int mid = low + (high - low) / 2;
        if (array[low] > array[high]) {
            if (array[mid] > array[high]) {
                return mid;
            }
            else if (array[low] > array[mid]){
                return high;
            }
            else {
                return low;
            }
        }
        else {
            if (array[low] > array[high]) {
                return low;
            }
            else if (array[mid] > array[high]){
                return low;
            }
            else {
                return mid;
            }
        }
    }

    public static int partitionMedian(int[] array, int low, int high, int pivot) {
        int x = array[pivot];
        int temp = array[pivot];
        array[pivot] = array[high];
        array[high] = temp;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= x) {
                i++;
                temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp; 
            }
        }
        temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
