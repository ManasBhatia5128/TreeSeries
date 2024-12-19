package MediumProblems;

public class DiameterOfBT{
    static int diameterOfBinaryTree(Node root) {
        int[] ans = new int[1];
        helper(root, ans);
        return ans[0];
    }

    static int helper(Node root, int[] dia) {
        if (root == null) {
            return 0;
        }

        int lh = helper(root.left, dia);
        int rh = helper(root.right, dia);

        dia[0] = Math.max(dia[0], lh + rh); // We cannot use a integer directly since it is a primitive data type and thus its original value won't get updated, that's why we had to use an array or arraylist

        return Math.max(lh, rh) + 1;
    }
}