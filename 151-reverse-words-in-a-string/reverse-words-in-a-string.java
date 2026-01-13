class Solution {
    public String reverseWords(String s) {
         StringBuilder result = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            // Skip spaces
            while (i >= 0 && s.charAt(i) == ' ') i--;

            if (i < 0) break;

            int j = i;
            // Find the start of the word
            while (j >= 0 && s.charAt(j) != ' ') j--;

            // Append the word
            result.append(s.substring(j + 1, i + 1)).append(" ");

            i = j;
        }

        // Remove trailing space
        return result.toString().trim();
    }
}