class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
         int n = nums.length;
        // keeps smallest (k-1) values in current window
        TreeSet<int[]> chosen = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        // keeps remaining values in window
        TreeSet<int[]> extra = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        long currSum = 0;
        long best = Long.MAX_VALUE;
        int idx = 1;
        // initial window [1 .. dist + 1)
        while (idx < n && idx - dist < 1) {
            int[] p = new int[]{nums[idx], idx};
            chosen.add(p);
            currSum += nums[idx];
            if (chosen.size() > k - 1) {
                int[] move = chosen.pollLast();
                currSum -= move[0];
                extra.add(move);
            }
            idx++;
        }
        // slide window
        while (idx < n) {
            int[] p = new int[]{nums[idx], idx};
            chosen.add(p);
            currSum += nums[idx];
            if (chosen.size() > k - 1) {
                int[] move = chosen.pollLast();
                currSum -= move[0];
                extra.add(move);
            }
            best = Math.min(best, currSum);
            // remove element going out of range
            int out = idx - dist;
            int[] rem = new int[]{nums[out], out};
            if (chosen.remove(rem)) {
                currSum -= nums[out];
                if (!extra.isEmpty()) {
                    int[] take = extra.pollFirst();
                    chosen.add(take);
                    currSum += take[0];
                }
            } else {
                extra.remove(rem);
            }
            idx++;
        }
        return nums[0] + best;
    }
}