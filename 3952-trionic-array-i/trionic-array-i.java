class Solution {
    public boolean isTrionic(int[] nums) {
        int i =1;
        // first part
        while(i<nums.length && nums[i] > nums[i-1]){
           i++;
        }
        if(i==1 || i==nums.length){
         return false;
        }
        // second part
        while(i<nums.length && nums[i] < nums[i-1]){
          i++;
        }
        if(i==nums.length){
         return false;
        }
        //third part
         while(i<nums.length && nums[i] > nums[i-1]){
          i++;
        }
        return (i==nums.length) ? true : false;
    }
}