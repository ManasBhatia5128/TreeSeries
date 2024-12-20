package MediumProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class BoundryTraversal{
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Integer> ans =  boundryTraversal(root);
        System.out.println(ans);
    }
    static List<Integer> boundryTraversal(Node root){
        // What I understood was: root -> root.left - > root.left.right -> leafNodes (l to right) -> root.right -> root;
        // Bsdk not everyTime you'll be getting simple tree, for ex 
        List<Integer> ans = new ArrayList<>();
        List<Integer> leafNodes = new ArrayList<>();
        List<Integer> rightNodes = new ArrayList<>();
        leafNodes(root, leafNodes);
        Node leftPtr = root;
        Node rightPtr = root;
        while (leftPtr.left != null) {
            if(leftPtr.left != null || leftPtr.right != null){
                ans.add(leftPtr.data);
            }

            // Move only if it is not a leaf node
            if(leftPtr.left != null){
                leftPtr = leftPtr.left;
            }
            else{
                leftPtr = leftPtr.right;
            }
        }
        ans.addAll(leafNodes);
        while(rightPtr.right != null){
            if(rightPtr.left != null && rightPtr.right != null){
                rightNodes.add(rightPtr.data);
            }
            if(rightPtr.right != null){
                rightPtr = rightPtr.right;
            }
            else{
                rightPtr = rightPtr.left;
            }
        }
        Collections.reverse(rightNodes);
        rightNodes.remove(rightNodes.size()-1);
        ans.addAll(rightNodes);
        return ans;
    }
    static void leafNodes(Node root, List<Integer> ans){
        // We are using post order
        if(root == null){
            return;
        }
        leafNodes(root.left, ans);
        leafNodes(root.right, ans);
        if(root.left == null && root.right == null){
            ans.add(root.data);
        }
    }



    // Striver's methods
    
}