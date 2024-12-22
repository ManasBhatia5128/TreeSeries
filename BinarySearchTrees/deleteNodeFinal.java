package BinarySearchTrees;

public class deleteNodeFinal {
    static TreeNode delete(TreeNode root, int val) {
        
    }

    static TreeNode helper(TreeNode node) {
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        }
        TreeNode rightChild = node.right;
        TreeNode lastRight = lastRight(node.left);
        lastRight.right = rightChild;
        return node;
    }

    static TreeNode lastRight(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
}
