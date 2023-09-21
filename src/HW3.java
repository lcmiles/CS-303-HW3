import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class HW3 {

    private static int[] originalArray;
    private static int[] array;

    public static void main(String[] args) {
        try (Scanner reader = new Scanner(Paths.get("input_16.txt"))) {
            while (reader.hasNextLine()) {
            String intString = reader.nextLine();
            String[] stringArray = intString.split("\\s+"); //splits the string at each space and adds each individual string to an array
            originalArray = new int[stringArray.length]; //initializes array to size of the string array read from the file
            array = new int[stringArray.length]; //initializes a copy of the original array
            for(int i = 1; i < stringArray.length; i++) { //converts each string in the array to an int and adds to two new identical arrays
                String string = stringArray[i];
                originalArray[i] = Integer.parseInt(string);
                array[i] = Integer.parseInt(string);
                System.out.println(array[i]);
            }
        }
        reader.close();
        } catch (IOException e) {
            e.printStackTrace();
       } 
    }
}
