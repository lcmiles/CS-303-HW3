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
            intString = intString.trim(); //removed extra spaces from the beginning
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
        int size = array.length;
        for (int i = 0; i < size - 1; i++) { //iterates over the whole array
            int min = i;  //sets smallest value to current index
            for (int j = i + 1; j < size; j++) { //iterates over the portion of the array that has already been sorted
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
}
