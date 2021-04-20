import java.util.Scanner;

public class Main {
    /**
     * This function contains the timer that will time how long it takes to input the alphabet.
     * For each letter in the alphabet array we'll get the input from the processInput() function and
     * check if it's correct. If it's correct, we'll move on to the next letter.
     */
    public static void main(String[] args) {
        System.out.print("Type the alphabet in order (hit enter between letters)\nForwards or Backwards (f/b)? ");

        // We use a normal array as we know what the size of the array is going to be beforehand.
        char alphabet[] = initializeAlphabet(), input;
        long start;

        System.out.printf("[Begin!]\n");

        // Create timer to monitor how long it takes to complete the alphabet.
        start = System.nanoTime();

        // Input and display each letter in the alphabet array
        for (int i = 0; i < alphabet.length; i++) {
            input = processInput();
            if (input == alphabet[i]) {
                // Check if end of array reached
                if (i == 25) {
                    System.out.print("[Correct! Well Done!]\n");
                } else {
                    System.out.printf("[%s: Correct! Now type: %s]\n", alphabet[i], alphabet[i + 1]);
                }
            } else {
                --i;
            }
        }

        System.out.printf("\nTime Taken: %.3f seconds", (System.nanoTime() - start) / 1_000_000_000.0);
    }

    /**
     * First we'll ask the user what direction they want the alphabet to be in. If the user inputs F or B, we'll
     * create the array, else we'll just ask again by running this function again.
     */
    private static char[] initializeAlphabet() {
        char alphabet[] = new char[26], input;

        // First, we'll ask if the user wants the input forwards or backwards.
        input = processInput();

        // Initialize the alphabet array based on the what order the user has chosen.
        if (input == 'f') {
            alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        } else if (input == 'b') {
            alphabet = "zyxwvutsrqponmlkjihgfedcba".toCharArray();
        } else {
            System.out.printf("Invalid. You must enter either 'f' or 'b' to start: ");
            return initializeAlphabet();
        }

        return alphabet;
    }

    /**
     * This function creates a scanner and takes in the user input, converting it to lowercase and checking
     * the length to validate.
     */
    private static char processInput() {
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();

        // If the length of the inputted string is 1, we'll return it so the main function can check if it's correct.
        // To allow correct capital letters, we'll always return the lowercase of the letter.
        if (line.length() == 1) {
            return line.toLowerCase().charAt(0);
        } else {
            return processInput();
        }
    }
}