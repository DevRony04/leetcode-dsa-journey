class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int n = nums.length;
        for(int i = 0; i<(n/2);i++){
            sum = Math.max(sum, nums[i] + nums[n-i-1]);
        }
        return sum;
    }
}