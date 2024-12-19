public class Representation{
    public static void main(String[] args) {
        Node root = new Node(0);
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);

        root.left = one;
        root.right = two;
        one.left = three;
        one.right = four;

        System.out.println(one.left.data);
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