package edu.hw1.task5;

import static edu.hw1.task2.Task2.countDigits;

public class Task5 {
    private Task5() {
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

        if (isPalindrome) {
            return true;
        }

        long descendant = 0;
        String numStr = String.valueOf(num);
        for (int i = 0; i + 1 < numStr.length(); i += 2) {
            descendant = descendant * 10 + Character.getNumericValue(numStr.charAt(i))
                + Character.getNumericValue(numStr.charAt(i + 1));
        }

        // If num have odd number of digits, then we should add last digits manually
        if (countDigits(num) % 2 != 0) {
            descendant = descendant * 10 + Character.getNumericValue(numStr.charAt(numStr.length() - 1));
        }

        return isPalindromeDescendant(descendant);

    }
}
