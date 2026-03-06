class Solution {
    public boolean checkOnesSegment(String s) {
        int n = s.length();
        //01
        for(int i =1; i<n; i++){
          if(s.charAt(i) == '1' && s.charAt(i-1) == '0'){
            return false;
          }
        }
        return true;
    }
}