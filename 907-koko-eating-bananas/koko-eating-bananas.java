class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();

        while(left < right){
            int mid = left + (right-left)/2;
            int hours = 0;

            for(int p : piles){
             hours += (p + mid - 1)/mid;
            }
            if(hours <= h) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}