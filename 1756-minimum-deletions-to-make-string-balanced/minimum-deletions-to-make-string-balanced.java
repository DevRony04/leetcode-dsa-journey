class Solution {
    public int minimumDeletions(String s) {
        int ans=0,cnt=0;
    for(char c:s.toCharArray()){
        if(c=='b'){
            cnt++;
        }else if(cnt!=0){
            ans++;
            cnt--;
        }
    }
        return ans;
    }
}