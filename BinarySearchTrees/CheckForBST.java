package BinarySearchTrees;

public class CheckForBST {
    public static void main(String[] args) {
        // Creating a binary search tree (BST)
        TreeNode bstRoot = new TreeNode(10);
        bstRoot.left = new TreeNode(5);
        bstRoot.right = new TreeNode(15);
        bstRoot.left.left = new TreeNode(2);
        bstRoot.left.right = new TreeNode(7);
        bstRoot.right.left = new TreeNode(12);
        bstRoot.right.right = new TreeNode(20);

        // Creating a binary tree (BT) that is not a BST
        TreeNode btRoot = new TreeNode(10);
        btRoot.left = new TreeNode(5);
        btRoot.right = new TreeNode(15);
        btRoot.left.left = new TreeNode(2);
        btRoot.left.right = new TreeNode(12); // This makes it not a BST
        btRoot.right.left = new TreeNode(7);  // This makes it not a BST
        btRoot.right.right = new TreeNode(20);

        // Testing the check function
        System.out.println("BST check: " + check(bstRoot)); // Should print true
        System.out.println("BT check: " + check(btRoot));   // Should print false
    }

    static boolean check(TreeNode root){
        


        if(root == null){
            return true;
        }

        if(root.left.val < root.val && root.val < root.right.val){
            return true;
        }
        boolean check1 = check(root.left);
        boolean check2 = check(root.right);

        if(!check1 || !check2){
            return false;
        }
        return true;
    }
}
