class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int res = 0;
        int left = 0;
        int n = colors.length;
        for(int right = 1; right<(n+k-1); right++){
            // skip entire subarray
            if(colors[right%n] == colors[(right-1)%n]){
                left = right;
            }
            if(right-left+1==k){
              res++;
              left++; // move to next subarray
            }
        }
        return res;
    }
}