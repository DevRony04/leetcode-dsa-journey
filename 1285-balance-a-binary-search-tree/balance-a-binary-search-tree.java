/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void inorder(TreeNode root, ArrayList<Integer> inorderList){
        if(root == null) return;
        inorder(root.left, inorderList);
        inorderList.add(root.val);
        inorder(root.right, inorderList);
    }
    public TreeNode constructTree(ArrayList<Integer> inorderList, int start, int end){
     if(start>end) return null;
     int mid = start + (end-start)/2;
     TreeNode left = constructTree(inorderList, start, mid-1);
     TreeNode right = constructTree(inorderList, mid+1, end);
     TreeNode root = new TreeNode(inorderList.get(mid),left,right);
     return root;
    }
    public TreeNode balanceBST(TreeNode root) {
     ArrayList<Integer> inorderList = new ArrayList<>();
     inorder(root,inorderList);
     return constructTree(inorderList,0,inorderList.size()-1);
    }
}