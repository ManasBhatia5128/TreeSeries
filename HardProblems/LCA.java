package HardProblems;

import java.util.ArrayList;
import java.util.List;

public class LCA {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        System.out.println(lcaHelper3(root, root.left.left, root.left.right.right, false).val);
    }

    static int lcaBrute(TreeNode root, TreeNode p, TreeNode q){
        // We will use preorder traversal
        List<Integer> pathP = rootToNodePath(root,  p);
        List<Integer> pathQ = rootToNodePath(root, q);
        int ptr = 0;
        while(ptr < Math.min(pathP.size(), pathQ.size())){
            if(pathP.get(ptr) != pathQ.get(ptr)){
                return ptr-1;
            }
        }
        return -1; // dummy return
    }

    // static boolean lcaHelper(TreeNode root, TreeNode p, TreeNode q, TreeNode ans){
    //     if(root == null){
    //         return false;
    //     }
    //     if(root == p){
    //         return true;
    //     }
    //     if(root == q){
    //         return true;
    //     }

    //     if(lcaHelper(root.left, p, q, ans) && lcaHelper(root.right, p, q, ans)){
    //         ans = root;
    //         return true;
    //     }
    //     return false;
    // }

    static TreeNode lcaHelper2(TreeNode root, TreeNode p, TreeNode q){

        // Our thinking: We will do DFS preorder traversal and check if we have found the required nodes, if by both left and right ways our ans is not coming to be null, we will then return the node we are at as our ans since it will be the first path where both our ans are not null
        
        // These three are our base cases that in case we reach null or find the required Node
        if(root == null){
            return null;
        }
        if(root == p){
            return p;
        }
        if(root == q){
            return q;
        }

        // While returning we have three possibilites: we found p, we found q, we found both

        // However, while returning, hame aisa lg skta hai ki maan lo wrt main root, dono answers left side se mil gyi lekin tb bhi right side check hogi, but also in that case right side will return null and so our answer in any case will be case1 || case2 and since case1 is not null we get our ans returned
        // this (4,7) is a great example of that

        // We can further simplify this approch by introducing a boolean which becomes true when both answers are found, this way we won't have to tranverse the whole tree when ans is found earlier since in this case TC is bit less than O(N) {when one of node is found iteration stops in that path only} in any case
        TreeNode case1 = lcaHelper2(root.left, p, q);
        TreeNode case2 = lcaHelper2(root.right, p, q);
        if(case1 != null || case2 != null){
            if(case1 != null && case2 != null){
                return root;
            }
            else if(case1 != null){
                return case1;
            }
            else{
                return case2;
            }
        }
        return null;
    }


    static TreeNode lcaHelper3(TreeNode root, TreeNode p, TreeNode q, boolean flag){
        if(flag){
            return root;
        }
        if(root == null){
            return null;
        }
        if(root == p){
            return p;
        }
        if(root == q){
            return q;
        }


        TreeNode case1 = lcaHelper3(root.left, p, q, flag);
        TreeNode case2 = lcaHelper3(root.right, p, q, flag);
        if(case1 != null || case2 != null){
            if(case1 != null && case2 != null){
                return root;
            }
            else if(case1 != null){
                return case1;
            }
            else{
                return case2;
            }
        }
        return null;
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
}
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }