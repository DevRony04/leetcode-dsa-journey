class Solution {
    public boolean hasAllCodes(String s, int k) {
         int n = s.length();
        
        if (n < k) {
            return false;
        }

        int required = 1 << k;   // total possible binary strings of length k
        Set<String> seenPatterns = new HashSet<>();

        for (int end = k; end <= n; end++) {
            String current = s.substring(end - k, end);

            // Add returns true only if it was not already present
            if (seenPatterns.add(current)) {
                required--;
            }

            if (required == 0) {
                return true;
            }
        }

        return false;
    }
}