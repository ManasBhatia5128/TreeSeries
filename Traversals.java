import java.util.ArrayList;
import java.util.List;
public class Traversals {
    public static void main(String[] args) {
        
    }

    public static List<Integer> postorderTraversal(Node root) {
        List<Integer> ans = new ArrayList<>();
        postOrder(root, ans);
        return ans;
    }
    public static void postOrder(Node root, List<Integer> ans){
        if(root == null){
            return;
        }
        postOrder(root.left, ans);
        postOrder(root.right, ans);
        ans.add(root.data);
    }

    public static List<Integer> inorderTraversal(Node root) {
        List<Integer> ans = new ArrayList<>();
        inOrder(root, ans);
        return ans;
    }
    public static void inOrder(Node root, List<Integer> ans){
        if(root == null){
            return;
        }
        inOrder(root.left, ans);
        ans.add(root.data);
        inOrder(root.right, ans);
    }

    public static List<Integer> preorderTraversal(Node root) {
        List<Integer> ans = new ArrayList<>();
        preOrder(root, ans);
        return ans;
    }
    public static void preOrder(Node root, List<Integer> ans){
        if(root == null){
            return;
        }
        ans.add(root.data);
        preOrder(root.left, ans);
        preOrder(root.right, ans);
    }
}

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    Node(int data, Node left){
        this.data = data;
        this.left = left;
        this.right = null;
    }
    Node(int data, Node left, Node right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}