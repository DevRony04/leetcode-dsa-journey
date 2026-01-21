class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
         int n = colors.length;
        int count = 0;
        int validPairs = 0;

        // Check first window (k-1 adjacent pairs)
        for (int i = 1; i < k; i++) {
            if (colors[i % n] != colors[(i - 1) % n]) {
                validPairs++;
            }
        }

        if (validPairs == k - 1) count++;

        // Slide window across the circle
        for (int i = k; i < n + k - 1; i++) {
            // Remove leftmost pair
            if (colors[(i - k + 1) % n] != colors[(i - k) % n]) {
                validPairs--;
            }
            // Add rightmost pair
            if (colors[i % n] != colors[(i - 1) % n]) {
                validPairs++;
            }

            if (validPairs == k - 1) count++;
        }

        return count;
    }
}