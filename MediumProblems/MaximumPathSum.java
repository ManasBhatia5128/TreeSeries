package MediumProblems;

public class MaximumPathSum {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        
        int[] sum = new int[1];
        int[] ans = new int[1];
        height(root, sum, ans);
        System.out.println(ans[0]);
    }

    static int height(Node root, int[] sum, int[] maxSum){
        if(root == null){
            return 0;
        }
        sum[0] += root.data;
        int lh = height(root.left, sum, maxSum);
        if(root.left != null){
            sum[0] += root.left.data;
        }
        int rh = height(root.right, sum, maxSum);
        if(root.right != null){
            sum[0] += root.right.data;
        }
        maxSum[0] = Math.max(maxSum[0], sum[0]);

        return (1 + Math.max(lh, rh));
    }
}
