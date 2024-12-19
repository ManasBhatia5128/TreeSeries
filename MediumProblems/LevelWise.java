package MediumProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelWise {
    static List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        boolean revFlag = false;

        if(root == null){
            return ans;
        }
        que.add(root);
        que.add(null);
        while(true){
            Node elem = que.peek();
            if(elem != null){
                tempList.add(elem.data);
                que.poll();
                if(elem.left != null){
                    que.add(elem.left);
                }
                if(elem.right != null){
                    que.add(elem.right);
                }
            }
            else{
                if(revFlag){
                    Collections.reverse(tempList);
                    ans.add(new ArrayList<>(tempList));
                    tempList.clear();
                    revFlag = !revFlag;
                }
                else{
                    ans.add(new ArrayList<>(tempList));
                    tempList.clear();
                    revFlag = !revFlag;
                }
                que.poll();

                if(que.isEmpty()){
                    break;
                }
                que.add(null);
            }
        }
        return ans;
    }

    static void levelOrderRecursion(Node root){
        if(root == null){
            return;
        }
        System.out.println(root.data);

    }
}


class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    Node(int data, Node left) {
        this.data = data;
        this.left = left;
        this.right = null;
    }

    Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}