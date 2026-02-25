class Solution {
    // Brian Kernighan's Algorithm
    private int countSetBits(int n) {
        int count = 0;

        while (n > 0) {
            n = n & (n - 1);   // remove last set bit
            count++;
        }

        return count;
    }
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        List<int[]> list = new ArrayList<>();
        for(int a : arr){
            int count = countSetBits(a);
            list.add(new int[]{a, count});
        }
        Collections.sort(list, (a,b) -> a[1] != b[1] ? a[1] - b[1] : a[0] -b[0]);
        int[] res = new int[n];
        for(int i=0;i<list.size();i++){
            res[i] = list.get(i)[0];
        }
        return res;
    }
}