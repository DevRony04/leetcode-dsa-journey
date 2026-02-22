class Solution {
    public int binaryGap(int n) {
        int lastSeen = -1;
        int maxGap = 0;
        int index = 0;

        while (n > 0) {
            if ((n & 1) == 1) {           // if current bit is 1
                if (lastSeen != -1) {
                    maxGap = Math.max(maxGap, index - lastSeen);
                }
                lastSeen = index;
            }
            n >>= 1;
            index++;
        }

        return maxGap;
    }
}