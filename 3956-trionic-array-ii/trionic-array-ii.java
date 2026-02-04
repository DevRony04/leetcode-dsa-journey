class Solution {
    public long maxSumTrionic(int[] nums) {
         int n = nums.length;
        long answer = Long.MIN_VALUE;
        for (int start = 0; start < n; start++) {
            int idx = start;
            // ----------  First increasing segment ----------
            while (idx + 1 < n && nums[idx] < nums[idx + 1]) {
                idx++;
            }
            int peak1 = idx;
            // need at least two elements
            if (peak1 == start) continue;
            // ---------- Decreasing segment ----------
            long sum = nums[peak1] + nums[peak1 - 1];
            idx++;
            while (idx < n && nums[idx - 1] > nums[idx]) {
                sum += nums[idx];
                idx++;
            }
            int valley = idx - 1;
            // invalid trionic shape
            if (valley == peak1 || valley == n - 1 ||(idx < n && nums[idx] == nums[valley])) {
                start = valley;
                continue;
            }
            // ---------- Third increasing segment ----------
            sum += nums[valley + 1];
            // maximize third segment extension 
            long maxExtra = 0, running = 0;
            for (int i = valley + 2; i < n && nums[i] > nums[i - 1]; i++) {
                running += nums[i];
                maxExtra = Math.max(maxExtra, running);
            }
            sum += maxExtra;
            // ---------- maximize first segment extension ----------
            maxExtra = 0;
            running = 0;
            for (int i = peak1 - 2; i >= start; i--) {
                running += nums[i];
                maxExtra = Math.max(maxExtra, running);
            }
            sum += maxExtra;
            // ---------- update answer ----------
            answer = Math.max(answer, sum);
            // skip processed region
            start = valley - 1;
        }
        return answer;
    }
}