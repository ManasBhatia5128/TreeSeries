package HardProblems;

import java.util.HashMap;

public class ConstructBinaryTree {
    public static void main(String[] args) {
        int[] inorder = { 40, 20, 50, 10, 60, 30 };
        int[] preorder = { 10, 20, 40, 50, 30, 60 };

        System.out.println(treePreAndIn(preorder, inorder).val);
    }

    static TreeNode treePreAndIn(int[] preorder, int[] inorder) {
        // We can construct a unique binary tree only with 2 traversals. We need inorder
        // tranversal with pre or post order since we need to determine the left and
        // right subtree once we identify the node thorugh either preorder or postorder
        // traversal
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hash.put(inorder[i], i);
        }
        return helper(preorder, inorder, hash, 0, inorder.length - 1, 0, preorder.length - 1);
    }

    static TreeNode helper(int[] preorder, int[] inorder, HashMap<Integer, Integer> hash, int si, int ei, int sp,
            int ep) {
        if (sp > ep || si > ei) { // base cases
            return null;
        }
        // 
        int index = hash.get(preorder[sp]);
        int leftSubtreeSize = index - si;
        TreeNode root = new TreeNode(preorder[sp]);
        root.left = helper(preorder, inorder, hash, si, index - 1, sp + 1, sp + leftSubtreeSize);
        root.right = helper(preorder, inorder, hash, index + 1, ei, sp + leftSubtreeSize + 1, ep);
        return root;
    }
}
