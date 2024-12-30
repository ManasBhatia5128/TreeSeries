package HardProblems;

public class CountNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Number of nodes: " + brute(root));
    }
    
    static int brute(TreeNode root){
        int[] ans = new int[1];
        bruteHelper(root, ans); // this is just v basic approach of counting nodes
        return ans[0];
        // TC: O(N) SC = O(height), height = logN
    }

    // Since we are given a complete tree we can use its properties to further reduce time complexity
    static void bruteHelper(TreeNode root, int[] ans){
        if(root == null){
            return;
        }
        ans[0] += 1;
        bruteHelper(root.left, ans);
        bruteHelper(root.right, ans);
    }
    static int bruteHelper2(TreeNode root){
        if(root == null){
            return 0;
        }
        return bruteHelper2(root.left) + bruteHelper2(root.right);
    }

    static int lh(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + lh(root.left); 
    }
    static int rh(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + rh(root.right);
    }

    static int countNodes(TreeNode root){
        // if(root == null){
        //     return 0; // no need of this case since a node will at least have its height one
        // }
        int lh = lh(root);
        int rh = rh(root);
        if(lh == rh){
            return (int) Math.pow(2, lh) - 1; // if left and right heights are equal we can directly return the answer
        }
        int leftNodes = countNodes(root.left); // else do it seprately for left and right sides
        int rightNodes = countNodes(root.right);
        return 1 + leftNodes + rightNodes;
    }
    // TC = O(log2N) but in worst case, though the tree is complete the recursion can run for (logN)^2 times since the loop needs to go this deep for worst case
    // TC = O((log N)^2) in the worst case, because for a complete binary tree, the height calculation happens for up to log N levels.
// In the best case (perfectly balanced tree), the TC is O(log N) because we directly compute the node count.
}
