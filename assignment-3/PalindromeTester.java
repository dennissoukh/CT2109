public class PalindromeTester implements Palindrome
{
    public int primitiveCount1 = 0;
    public int primitiveCount2 = 0;
    public int primitiveCount3 = 0;
    public int primitiveCount4 = 0;

    public boolean loopCharacter(String input) {
        StringBuilder res = new StringBuilder();
        primitiveCount1++;

        // Reserve the input string using a for loop
        for (int i = input.length() - 1; i >= 0; i--) {
            primitiveCount1 += 7;
            res.append(input.charAt(i));
        }

        primitiveCount1 += 3;
        return res.toString().equals(input);
    }

    public boolean characterCompare(String input) {
        // Compare the characters on an element-by-element basis
        for (int i = 0; i < input.length(); i++) {
            primitiveCount2 += 5;

            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                primitiveCount2 += 6;
                return false;
            }
        }

        primitiveCount2++;
        return true;
    }

    public boolean stackQueue(String input) {
        ArrayStack stack = new ArrayStack();
        ArrayQueue queue = new ArrayQueue();
        primitiveCount3 += 2;

        // Push the characters of the string to both a stack and queue
        for (char x: input.toCharArray()) {
            primitiveCount3 += 4;
            stack.push(x);
            queue.enqueue(x);
            primitiveCount3 += 4;
        }

        // Compare each character in the stack and queue
        for (int i = 0; i < input.length(); i++) {
            primitiveCount3 += 4;

            if (stack.pop() != queue.dequeue()) {
                primitiveCount3 += 4;
                return false;
            }
        }

        primitiveCount3++;
        return true;
    }

    public boolean reverseString(String input) {
        primitiveCount4 += 3;
        return reverse(input).equals(input);
    }

    public String reverse(String input) {
        primitiveCount4 += 2;
        if (input.isEmpty()) {
            primitiveCount4 += 1;
            return input;
        }

        primitiveCount4 += 5;
        return reverse(input.substring(1)) + input.charAt(0);
    }

    public String decimalBinary(String input) {
        ArrayStack stack = new ArrayStack();

        // Check if the number provided is zero
        if (input.equals("0")) return "0";

        int n = Integer.parseInt(input);

        // Create the binary number
        while (n > 0) {
            stack.push((char) n % 2);
            n = n / 2;
        }

        // Build the binary string
        StringBuilder res = new StringBuilder();

        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.toString();
    }
}
