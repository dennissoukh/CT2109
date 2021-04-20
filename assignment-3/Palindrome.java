interface Palindrome
{
    /**
     * 1: Reverse all characters in the string using a loop and then compare
     * the reversed string to the original
     */
    public boolean loopCharacter(String input);

    /**
     * 2: Compare characters on an element by element basis using a loop
     */
    public boolean characterCompare(String input);

    /**
     * 3: Use ArrayStack and ArrayQueue implementations
     */
    public boolean stackQueue(String input);

    /**
     * 4: Use recursion to reverse the characters and compare with original
     * string
     */
    public boolean reverseString(String input);

    /**
     * Utility function to convert decimal into binary
     */
    public String decimalBinary(String input);
}
