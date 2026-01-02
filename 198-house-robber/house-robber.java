class Solution {
    public int rob(int[] nums) {
        int prev1 = 0; // max money till house i-1
        int prev2 = 0; // max money till house i-2

        for(int money:nums){
            int curr = Math.max(prev1, prev2+money);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}