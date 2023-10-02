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
        // printArray(array);
        System.out.println("File: " + file);
        System.out.println("Array Size: " + (array.length));
        System.out.println("Insertion Sort Time: " + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
        array = originalArray; //resets the array

        timeInit = System.nanoTime(); //records initial system time in nanoseconds
        quickSort(array, 0, array.length-1); 
        timeFinal = System.nanoTime(); // records final system time in nanoseconds
        time = timeFinal - timeInit; //calculates time taken for insertion sort algorithm
        // printArray(array);
        System.out.println("File: " + file);
        System.out.println("Array Size: " + (array.length));
        System.out.println("Quick Sort Time: " + time + " nanoseconds, " + (float)time/1000000 + " milliseconds, or " + (float)time/1000000000 + " seconds");
        array = originalArray; //resets the array

        timeInit = System.nanoTime(); //records initial system time in nanoseconds
        medianOfThreeQuickSort(array, 0, array.length-1); 
        timeFinal = System.nanoTime(); // records final system time in nanoseconds
        time = timeFinal - timeInit; //calculates time taken for insertion sort algorithm
        // printArray(array);
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
    Description: called in main to print out each element in the array after it is sorted for testing purposes
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

    /*
    Description: this function recursively calls itself on the array and the resulting subarrays, causing them to be partitioned at the pivot value each time which is determined by partition()
    Parameters:
    int[] array - the array to be sorted
    int low - the lowest index in the array
    int high - the highest index in the array
    Returns: nothing
     */
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) { //recursively call function on subarrays until they are of size one
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1); //calls quicksort on the left subarray
            quickSort(array, pivot + 1, high); //calls quicksort on the right subarray
        }
    }

    /*
    Description: this function ensures that all indices less than the pivot contain values less than then pivot value and returns the pivot index, sorting the subarray with each call
    Parameters:
    int[] array - the array to be sorted
    int low - the lowest index in the array
    int high - the highest index in the array
    Returns:
    int i + 1 - the new pivot value for the next call of partition()
     */
    public static int partition(int[] array, int low, int high) {
        int x = array[high]; //set x as the highest value in the array
        int i = low - 1; //set pivot to the lowest value - 1
        for (int j = low; j < high; j++) { //iterate from the lowest index of the array to the highest
            if (array[j] <= x) { //if the value of the current index is less than or equal to the value of the highest index
                i++; //forward iterate the pivot value by 1
                int temp = array[i]; //next few lines swap the lowest index and the pivot
                array[i] = array[j]; 
                array[j] = temp; 
            }
        }
        int temp = array[i + 1]; //next few lines swap the highest index with the pivot + 1 
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1; //return new pivot
    }

    /*
    Description: this function is essentially the same as quickSort(), except it uses the medianOfThree() function to determine the pivot value, then calls partitionMedian() on that value
    Parameters:
    int[] array - the array to be sorted
    int low - the lowest index in the array
    int high - the highest index in the array
    Returns: nothing
     */
    public static void medianOfThreeQuickSort(int[] array, int low, int high) {
        if (low < high) { //recursively call function on subarrays until they are of size one
            int pivot = medianOfThree(array, low, high); 
            int partitionIndex = partitionMedian(array, low, high, pivot); //partition at the pivot value
            quickSort(array, low, partitionIndex - 1); //recursively call on left subarray
            quickSort(array, partitionIndex + 1, high); //recursively call on right subarray
        }
    }

    /*
    Description: this function determines the median of the lowest, middle, and highest indices using a series of if statements and returns that value as the pivot
    Parameters:
    int[] array - the array to be sorted
    int low - the lowest index in the array
    int high - the highest index in the array
    Returns:
    int low, int mid, or int high depending on the if conditions which all function as the pivot value
     */
    public static int medianOfThree(int[] array, int low, int high) {
        int mid = low + (high - low) / 2; //calculate middle index
        if (array[low] > array[high]) { //if the value of the lowest index is greater than the value of the highest index
            if (array[mid] > array[high]) { //if the value of the middle index is greater than the value of the highest index
                return mid; 
            }
            else if (array[low] > array[mid]){ //if the value of the lowest index is greater than the value of the middle index
                return high;
            }
            else {
                return low;
            }
        }
        else {
            if (array[low] > array[high]) { //if the value of the lowest index is greater than the value of the highest index
                return low;
            }
            else if (array[mid] > array[high]){ //if the value of the middle index is greater than the value of the highest index
                return low;
            }
            else {
                return mid;
            }
        }
    }

    /*
    Description: this function ensures that all indices less than the pivot contain values less than then pivot value and returns the pivot index, sorting the subarray with each call
    Parameters:
    int[] array - the array to be sorted
    int low - the lowest index in the array
    int high - the highest index in the array
    int pivot - the value at which the array is partitioned
    Returns:
    int i + 1 - the new pivot value for the next call of partition()
     */
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
