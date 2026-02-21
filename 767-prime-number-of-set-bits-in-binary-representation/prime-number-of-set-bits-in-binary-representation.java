class Solution {
    public int countPrimeSetBits(int left, int right) {
        HashSet<Integer> hset = new HashSet<>(
            Arrays.asList(2,3,5,7,11,13,17,19,31)
        );
        int ans = 0;
        for(int i = left; i<=right;i++ ){
            //O(right-left)*32 //O(1)
         int setbits = Integer.bitCount(i);
         if(hset.contains(setbits)){
         ans++;
         }
        }
        return ans;
        }
}