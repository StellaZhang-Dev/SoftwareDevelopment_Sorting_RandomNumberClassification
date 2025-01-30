import java.util.Random;
import java.util.Scanner;
/**
 * This is the program for Marked Assignment 2
 *
 * This program generates random integers, separates them into even and odd numbers,
 * sorts them, and displays the results in the required format.
 *
 * Functional requirememts:
 * User specifies the number of random integers.
 * Program handles OutOfMemoryError and invalid input.
 * Random numbers are between 0 and 999.
 * Even numbers are sorted in ascending order.
 * Odd numbers are sorted in descending order.
 * Results are displayed in the required format.
 *
 * @author Stella Zhang
 * @version 1.0
 */
class Main {

    static final String USER_INPUT_PROMPT = "How many random numbers in the range 0 - 999 are desired?";
    static final String RANDOM_NUMBERS_LIST_MESSAGE = "Here are the random numbers:";
    static final String RANDOM_NUMBERS_SORTED_MESSAGE = "Here are the random numbers arranged:";
    static final String NO_ODD_NUMBERS_MESSAGE = "No Odd Numbers";
    static final String NO_EVEN_NUMBERS_MESSAGE = "No Even Numbers";
    static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    static final int MAX_RANDOM_VALUE = 1000;

     /**
     * Main method to execute the program.
     *
     * @param args Command-line arguments (not used).
     */
    public  static  void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println(USER_INPUT_PROMPT);

        //Get valid input
        int count = getValidInput(scanner);

        //Handle random number generation, sorting, and output
        int[] randomNumbers = generateRandomNumbers(count, random);
        printArray(RANDOM_NUMBERS_LIST_MESSAGE, randomNumbers);

        int[] evenNumbers = filterNumbers(randomNumbers, true);
        int[] oddNumbers = filterNumbers(randomNumbers, false);

        bubbleSort(evenNumbers, true);
        bubbleSort(oddNumbers, false);

        printSortedNumbers(evenNumbers, oddNumbers);
        System.out.println("\n Of the above " + count + " numbers, "
                + evenNumbers.length + " were even and " + oddNumbers.length + " odd.");
    }

    /**
     * Prompts the user for a valid positive integer input.
     * @param scanner A {@link Scanner} object for reading user input.
     * @return A valid positive integer input.
     */
    private static int getValidInput(final Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println(INVALID_INPUT_MESSAGE);
            scanner.next();
        }
        int input = scanner.nextInt();
        if (input <= 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
            System.exit(0);
        }
        return input;
    }

    /**
     * Generates an array of random integers between 0 and 999.
     *
     * @param count The number of random integers to generate.
     * @param random A {@link Random} object for generating random numbers.
     * @return An array containing the generated random integers.
     */
    private static int[] generateRandomNumbers(final int count, final Random random) {
        int[] numbers = new int[count];
        try {
            numbers = new int[count];
            for (int i = 0; i < count; i++) {
                numbers[i] = random.nextInt(MAX_RANDOM_VALUE);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Error, Cannot allocate so much memory.");
            return null; // Return null if memory allocation fails
        }
        return numbers;
    }

     /**
     * Filters an array of integers to return only even or odd numbers.
     *
     * @param numbers The array of integers to filter.
     * @param isEven A boolean indicating whether to filter even numbers (true) or odd numbers (false).
     * @return An array containing only the filtered numbers.
     */
    private static int[] filterNumbers(final int[] numbers, final boolean isEven) {
        int size = 0;
        for (int num : numbers) {
            if ((num % 2 == 0) == isEven) {
                size++;
            }
        }
        int[] result = new int[size];
        int index = 0;
        for (int num : numbers) {
            if ((num % 2 == 0) == isEven) {
                result[index++] = num;
            }
        }
        return result;
    }

    /**
     * Sorts an array of integers using the bubble sort algorithm.
     *
     * @param array The array to sort.
     * @param ascending A boolean indicating the sort order (true for ascending, false for descending).
     */
    private static void bubbleSort(final int[] array, final boolean ascending) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if ((ascending && array[j] > array[j + 1]) || (!ascending && array[j] < array[j + 1])) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Prints the elements of an array with a message.
     *
     * @param message The message to print before the array.
     * @param array The array of integers to print.
     */
    private static void printArray(final String message, final int[] array) {
        System.out.println(message);
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * Prints the sorted even and odd numbers in the required format.
     *
     * @param evenNumbers The array of sorted even numbers.
     * @param oddNumbers The array of sorted odd numbers.
     */
    private static void printSortedNumbers(final int[] evenNumbers, final int[] oddNumbers) {
        System.out.println(RANDOM_NUMBERS_SORTED_MESSAGE);

        if (evenNumbers.length > 0) {
            for (int num : evenNumbers) {
                System.out.print(num + " ");
            }
        } else {
            System.out.print(NO_EVEN_NUMBERS_MESSAGE);
        }

        System.out.print(" - ");

        if (oddNumbers.length > 0) {
            for (int num : oddNumbers) {
                System.out.print(num + " ");
            }
        } else {
            System.out.print(NO_ODD_NUMBERS_MESSAGE);
        }
    }
} // end of class
