package edu.hw1.task5;

public class Solution5 {
    private Solution5() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(long num) {

        // Return false if num have only one digit
        if (num / 10 == 0) {
            return false;
        }

        // Compare reversed num to original one
        long reverse = 0;
        long temp = num;
        while (temp != 0) {
            reverse = (reverse * 10) + (temp % 10);
            temp /= 10;
        }
        boolean isPalindrome = reverse == num;

        // Get descendant
        long descendant = 0;
        String numStr = String.valueOf(num);
        for (int i = 0; i + 1 < numStr.length(); i += 2) {
            descendant = descendant * 10 + Character.getNumericValue(numStr.charAt(i))
                + Character.getNumericValue(numStr.charAt(i + 1));
        }

        return isPalindrome || isPalindromeDescendant(descendant);
    }
}
