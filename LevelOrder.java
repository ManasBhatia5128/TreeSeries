import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class LevelOrder {
    public static void main(String[] args) {
        
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        
        root.left = two;
        root.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;

        // System.out.println(levelOrderUsingQueue(root));
        levelWisePrint(root);
    }

    static List<Integer> levelOrderUsingQueue(Node root) {
        Queue<Node> que = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        que.add(root);
        que.add(null);
        while (true) {
            Node elem = que.poll();
            if (elem != null) {
                ans.add(elem.data);
                if (elem.left != null) {
                    que.add(elem.left);
                }
                if (elem.right != null) {
                    que.add(elem.right);
                }
            } else {
                if (que.isEmpty()) {
                    break;
                } else {
                    que.add(elem);
                }
            }
        }
        return ans;
    }

    static void levelWisePrint(Node root) {
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        que.add(null);
        while (true) {
            Node elem = que.poll();
            if (elem != null) {
                System.out.print(elem.data);
                if (elem.left != null) {
                    que.add(elem.left);
                }
                if (elem.right != null) {
                    que.add(elem.right);
                }
            } else {
                if (que.isEmpty()) {
                    break;
                } else {
                    que.add(elem);
                    System.out.println();
                }
            }
        }
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    TreeNode(int data, TreeNode left) {
        this.data = data;
        this.left = left;
        this.right = null;
    }

    TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}