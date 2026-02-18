class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] freq = new int[26];
        
        // Build frequency for s1
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int needed = s1.length();

        while (right < s2.length()) {
            char r = s2.charAt(right);

            // If character is needed
            if (freq[r - 'a'] > 0) {
                needed--;
            }

            freq[r - 'a']--;
            right++;

            // When window size equals s1 length
            if (right - left == s1.length()) {
                if (needed == 0) return true;

                char l = s2.charAt(left);

                if (freq[l - 'a'] >= 0) {
                    needed++;
                }

                freq[l - 'a']++;
                left++;
            }
        }

        return false;
    }
}