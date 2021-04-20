public class ComplexityAnalysis
{
    static PalindromeTester palindrome = new PalindromeTester();

    public static void main(String[] args) {
        // Run the efficiency tester
        efficiencyTester();
    }

    public static void printMethodResult(long duration, int decimalCount, int binaryCount, int decimalBinaryEquivalent) {
        System.out.println("\n> Time taken: " + duration + " ms");
        System.out.println("> Decimal palindromes: " + decimalCount);
        System.out.println("> Binary palindromes: " + binaryCount);
        System.out.println("> Decimal & binary equivalent palindromes: " + decimalBinaryEquivalent);
    }

    public static void efficiencyTester() {
        // Method 1
        long startTime = System.nanoTime();
        int decimalCount = 0; int binaryCount = 0; int decimalBinaryEquivalent = 0;

        System.out.println("\nMethod One: loopCharacter()");
        for (int i = 0; i < 1000000; i++) {
            if (palindrome.loopCharacter(Integer.toString(i))) {
                decimalCount++;
            }

            if (palindrome.loopCharacter(palindrome.decimalBinary(Integer.toString(i)))) {
                binaryCount++;
            }

            if (
                palindrome.loopCharacter(Integer.toString(i)) &&
                palindrome.loopCharacter(palindrome.decimalBinary(Integer.toString(i)))
            ) decimalBinaryEquivalent++;

            if (i % 50000 == 0) {
                System.out.println(i + ": " + palindrome.primitiveCount1);
            }
        }

        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);
        System.out.println(1000000 + ": " + palindrome.primitiveCount1);

        printMethodResult(duration, decimalCount, binaryCount, decimalBinaryEquivalent);

        // Method 2
        startTime = System.nanoTime();
        decimalCount = 0; binaryCount = 0; decimalBinaryEquivalent = 0;

        System.out.println("\nMethod Two: characterCompare()");
        for (int i = 0; i < 1000000; i++) {
            if (palindrome.characterCompare(Integer.toString(i))) {
                decimalCount++;
            }

            if (palindrome.characterCompare(palindrome.decimalBinary(Integer.toString(i)))) {
                binaryCount++;
            }

            if (
                palindrome.characterCompare(Integer.toString(i)) &&
                palindrome.characterCompare(palindrome.decimalBinary(Integer.toString(i)))
            ) decimalBinaryEquivalent++;

            if (i % 50000 == 0) {
                System.out.println(i + ": " + palindrome.primitiveCount2);
            }
        }

        endTime = System.nanoTime();
        duration = ((endTime - startTime) / 1000000);
        System.out.println(1000000 + ": " + palindrome.primitiveCount2);

        printMethodResult(duration, decimalCount, binaryCount, decimalBinaryEquivalent);

        // Method 3
        startTime = System.nanoTime();
        decimalCount = 0; binaryCount = 0; decimalBinaryEquivalent = 0;

        System.out.println("\nMethod Three: stackQueue()");
        for (int i = 0; i < 1000000; i++) {
            if (palindrome.stackQueue(Integer.toString(i))) {
                decimalCount++;
            }

            if (palindrome.stackQueue(palindrome.decimalBinary(Integer.toString(i)))) {
                binaryCount++;
            }

            if (
                palindrome.stackQueue(Integer.toString(i)) &&
                palindrome.stackQueue(palindrome.decimalBinary(Integer.toString(i)))
            ) decimalBinaryEquivalent++;

            if (i % 50000 == 0) {
                System.out.println(i + ": " + palindrome.primitiveCount3);
            }
        }

        endTime = System.nanoTime();
        duration = ((endTime - startTime) / 1000000);
        System.out.println(1000000 + ": " + palindrome.primitiveCount3);

        printMethodResult(duration, decimalCount, binaryCount, decimalBinaryEquivalent);

        // Method 4
        startTime = System.nanoTime();
        decimalCount = 0; binaryCount = 0; decimalBinaryEquivalent = 0;

        System.out.println("\nMethod Four: reverseString()");
        for (int i = 0; i < 1000000; i++) {
            if (palindrome.reverseString(Integer.toString(i))) {
                decimalCount++;
            }

            if (palindrome.reverseString(palindrome.decimalBinary(Integer.toString(i)))) {
                binaryCount++;
            }

            if (
                palindrome.reverseString(Integer.toString(i)) &&
                palindrome.reverseString(palindrome.decimalBinary(Integer.toString(i)))
            ) decimalBinaryEquivalent++;

            if (i % 50000 == 0) {
                System.out.println(i + ": " + palindrome.primitiveCount4);
            }
        }

        endTime = System.nanoTime();
        duration = ((endTime - startTime) / 1000000);
        System.out.println(1000000 + ": " + palindrome.primitiveCount4);

        printMethodResult(duration, decimalCount, binaryCount, decimalBinaryEquivalent);
    }
}