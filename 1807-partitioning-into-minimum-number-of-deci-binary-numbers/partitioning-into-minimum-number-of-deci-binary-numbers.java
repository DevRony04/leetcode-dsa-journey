class Solution {
    public int minPartitions(String n) {
         int maxDigit = 0;
        
        for (char c : n.toCharArray()) {
            maxDigit = Math.max(maxDigit, c - '0');
            
            if (maxDigit == 9) {  // Early exit (can't get bigger than 9)
                return 9;
            }
        }
        
        return maxDigit;
    }
}