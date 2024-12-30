package HardProblems;

public class ChildrenSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(35);
        root.left = new TreeNode(20);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(15);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(15);

        System.out.println(childrenSumBrute(root));
    }

    public static int childrenSumBrute(TreeNode root) {
        // Our basic thinking is to traverse to every node and check the property

        if (root == null || (root.left == null && root.right == null)) {
            return 1; // Base case for null or leaf nodes
        }

        // the second check is due to linear trees which go in same direction
        if (root.left == null) {
            return (root.val != root.right.val) ? 0 : childrenSumBrute(root.right); // Right-only case
        }

        if (root.right == null) {
            return (root.val != root.left.val) ? 0 : childrenSumBrute(root.left); // Left-only case
        }

        // Check values for a general node if they follow the childrenSumProperty
        if (root.left.val + root.right.val != root.val) {
            return 0; // Sum property violation
        }

        if (childrenSumBrute(root.left) == 1 && childrenSumBrute(root.right) == 1) {
            return 1;
        }

        return 0; // Default failure case
        // Default is failure case since jo bhi satisfying nodes honge vo at last leaf
        // nodes wali condition ko satisfy kr jayegne
        // this will execute jb dono mein se koi ek bhi 1 nhi hoga ie jb bhi ye sum wali
        // condition fail ho jayegi
    }

    // static TreeNode childrenSum(TreeNode root) {
    //     /*
    //      * The node values can be increased by any positive integer any number of times,
    //      * but decrementing any node value is not allowed.
    //      * A value for a NULL node can be assumed as 0.
    //      * We cannot change the structure of the given binary tree.
    //      */

    //      if(root == null || root.left == null || root.right == null){
    //         return root;
    //      }
    // }
}
