import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeRepresentation {
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

        System.out.println(preInPostAll(root));
    }

    static List<Integer> preOrder(Node root){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<Node> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            root = st.pop();
            list.add(root.data);
            if(root.right != null){
                st.push(root.right);
            }
            if(root.left != null){
                st.push(root.left);
            }
        }
        return list;
    }

    static List<Integer> inOrder(Node root){
        Node node = root;
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<Node> st = new Stack<>();
        while(true){
            if(node != null){
                st.push(node);
                node = node.left;
            }
            else{
                if(st.isEmpty()){
                    break;
                }
                else{
                    node = st.pop();
                    list.add(node.data);
                    node = node.right;
                }
            }
        }
        return list;
    }

    static List<Integer> postOrdertwoStack(Node root){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(root);
        while(!st1.isEmpty()){
            root = st1.pop();
            st2.push(root);
            if(root.left != null){
                st1.push(root.left);
            }
            if(root.right != null){
                st1.push(root.right);
            }
        }
        while(!st2.isEmpty()){
            Node elem = st2.pop();
            list.add(elem.data);
        }
        return list;
    }

    static List<Integer> postOrderOneStack(Node root){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<Node> st = new Stack<>();
        Node prev = null;
        while (!st.isEmpty() || root != null) {
            if(root != null){
                st.push(root);
                root = root.left;
            }
            else{
                Node temp = st.peek();
                if(temp.right != null && temp.right != prev){
                    root = temp.right;
                }
                else{
                    list.add(temp.data);
                    prev = temp;
                    st.pop();
                }
            }
        }
        return list;
    }

    static List<List<Integer>> preInPostAll(Node root){

        List<List<Integer>> finalAns = new ArrayList<>();
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();

        Stack<Pair> st = new Stack<>();
        Pair item = new Pair(root, 1);
        st.push(item);
        while(!st.isEmpty()){
            Pair stItem = st.peek();
            if(stItem.num == 1){
                preOrder.add(stItem.node.data);
                st.pop();
                stItem.num += 1;
                st.push(stItem);
                if(stItem.node.left != null){
                    st.push(new Pair(stItem.node.left, 1));
                }
            }
            else if(stItem.num == 2){
                inOrder.add(stItem.node.data);
                st.pop();
                stItem.num += 1;
                st.push(stItem);
                if(stItem.node.right != null){
                    st.push(new Pair(stItem.node.right,1));
                }
            }
            else if(stItem.num == 3){
                postOrder.add(stItem.node.data);
                st.pop();
            }
        }

        finalAns.add(preOrder);
        finalAns.add(inOrder);
        finalAns.add(postOrder);
        
        return finalAns;
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

class Pair{
    Node node;
    int num;

    Pair(Node node, int num){
        this.node = node;
        this.num = num;
    }
}