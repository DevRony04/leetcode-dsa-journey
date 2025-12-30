class Solution {
    public int sumOfSquareOfDigits(int n){
       int sum = 0;
       // 36/10 -> 3 -> 0
       // dig -> 3
       // sum = 45
       while(n>0){
        int dig = n % 10;
        sum = sum + (dig*dig);
        n = n/10;
       }
       return sum;
    }
    public boolean isHappy(int n) {
        // slow, fast = n;
        // function -> sum of square of digits
        int slow = n,
        fast = n;

        while(fast != 1){
            slow = sumOfSquareOfDigits(slow);
            fast = sumOfSquareOfDigits(sumOfSquareOfDigits(fast));
            if(fast == 1){
                return true;
            }
            if(slow == fast){
                return false;
            }
        }
        return true;
    }
}