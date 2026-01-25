class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        for(int i = 0; i+k<=n; i++){
        minDiff = Math.min(minDiff, nums[i+k-1] - nums[i]);
        }
        return minDiff;
    }
}