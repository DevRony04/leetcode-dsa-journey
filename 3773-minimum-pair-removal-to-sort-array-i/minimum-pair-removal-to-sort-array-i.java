class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int x : nums) list.add(x);

        int operations = 0;

        while(!isNonDecreasing(list)){
         int minSum = Integer.MAX_VALUE;
         int idx = 0;

         // find the leftmost adjasent pair with minimum sum
         for(int i =0; i<list.size()-1; i++){
          int sum = list.get(i) + list.get(i+1);
          if(sum<minSum){
            minSum = sum;
            idx = i;
          }
         }
         //merge the pair
         list.set(idx,minSum);
         list.remove(idx+1);
         operations++;
        }
        return operations;

    }
    private boolean isNonDecreasing(List<Integer> list){
     for(int i =1; i< list.size();i++){
      if(list.get(i) < list.get(i-1)) return false;
     }
     return true;
    }
}