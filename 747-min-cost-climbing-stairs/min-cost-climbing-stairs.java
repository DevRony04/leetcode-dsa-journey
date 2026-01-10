class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] minCost = new int[n+1];

         // Base cases: starting at step 0 or step 1 costs nothing
        minCost[0] = 0;
        minCost[1] = 0;

        // DP transition
        for(int i = 2; i<=n; i++){
            minCost[i] = Math.min(
                cost[i-1] + minCost[i-1],
                cost[i-2] + minCost[i-2]
            );
        }
        return minCost[n];
    }
}