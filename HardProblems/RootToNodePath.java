package HardProblems;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
    }
}

public class RootToNodePath {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // System.out.println(rootToNodePath(root, root.left));
        System.out.println(rootToLeaf(root));
    }

    public static List<Integer> rootToNodePath(TreeNode root, TreeNode toFind){
        List<Integer> ans = new ArrayList<>();
        rootToNodeHelper(root, toFind, ans);
        return ans;
    }
    static boolean rootToNodeHelper(TreeNode root, TreeNode toFind, List<Integer> list){

        // Our thinking is to traverse ways to find node
        // Base case hai, return false
        if(root == null) {
            return false;
        }
        // Add path
        list.add(root.val);
        if(root == toFind) {
            return true;
        }

        // Firstly go to the left then go to the right, we have used preorder traversal here ie {root -> left -> right}
        if(rootToNodeHelper(root.left, toFind, list) || rootToNodeHelper(root.right, toFind, list)) {
            return true;
        }
        // Agar ab realise ho gya hai ki that;s not our path, remove that item and backtrack
        list.remove(list.size() - 1);
        return false;
    }


    static List<List<Integer>> rootToLeaf(TreeNode root){
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        rootToLeafNodeHelper(root, list, ans);
        return ans;
    }

    static void rootToLeafNodeHelper(TreeNode root, List<Integer> list, List<List<Integer>> ans){
        if(root == null){
            return; // Add this case since it doesn't make sense to add null
        }
        list.add(root.val);
        if(root.left == null && root.right == null){
            // ans.add(list); // It is fucking pass by reference, further changes in list will do changes in list added to ans
            ans.add(new ArrayList<>(list)); // do this instead
            list.remove(list.size()-1);
            return;
        }
        rootToLeafNodeHelper(root.left, list, ans);
        rootToLeafNodeHelper(root.right, list, ans);
        list.remove(list.size()-1);
    }
    // TC = O(N) && SC = O(N) {this is extra space for list, we are not considering space of ans}
}
