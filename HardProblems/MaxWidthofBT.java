package HardProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxWidthofBT {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = null;
        root.right.left = null;
        root.right.right = new TreeNode(9);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = null;
        root.right.right.left = new TreeNode(7);
        System.out.println(widthOfBinaryTreeStriver(root));

    }

    // meaning: no of nodes in a level (including that 2 nodes) is width of BT and
    // we have to find max width
    // between any two nodes you can consider null also as a node but not at edge

    // ** count krna hai toh ye dekh last level mein kitne nodes aa skte hain, na ki
    // vertically space banake

    // MyBrute
    // The method has a Time complexity of O(N) but for very large test cases due to operations of list and all tc is higher
    static int widthOfBinaryTree(TreeNode root) {
        // In leetcode values are from -100 to 100, that's why 101 for null and -101 for breaking away
        int givenLevel = height(root);
        int maxWidth = 0;
        // We used LinkedList<> instead of simple list to have add and remove operations in O(1) time, especially for the while loop part
        LinkedList<Integer> list = new LinkedList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        que.add(new TreeNode(-101));
        int level = 1;
        while (!que.isEmpty() && level <= givenLevel) {
            TreeNode elem = que.poll();
            if (elem.val == -101) {
                while(list.getFirst() == 101){
                    list.removeFirst();
                }
                while (list.getLast() == 101) {
                    list.removeLast();
                }
                maxWidth = Math.max(maxWidth, list.size());
                list.clear();
                level++;
                que.add(new TreeNode(-101));
            } else {
                list.add(elem.val);
                que.add(elem.left != null ? elem.left : new TreeNode(101));
                que.add(elem.right != null ? elem.right : new TreeNode(101));
            }
        }
        return maxWidth;
    }

    static int widthOfBinaryTreeStriver(TreeNode root){
        // We will use 2i+1 and 2i+2 as children indeces for nodes
        // You can take intution from heaps / segment trees

        // However take the case of a linear BT, the max length could react 10^5 so integers will be 0, 1, 3, 7 ... this is for 4 integers, think of 10^5 integers
        // So assign is as {(0), (0,1),(0,1,2)} ie i - minOfThatLevel
        // Width = last - first + 1

        int maxLength = 0;
        LinkedList<NodePair> que = new LinkedList<>();
        List<NodePair> list = new ArrayList<>();
        que.add(new NodePair(root, 0));
        que.add(new NodePair(null, -1));
        while(!que.isEmpty()){
            TreeNode elem = que.peek().node;
            int index = que.peek().index;
            que.poll();
            if(elem != null){
                list.add(new NodePair(elem, index));
                if(elem.left != null){
                    que.add(new NodePair(elem.left, (2*index)+1));
                }
                if(elem.right != null){
                    que.add(new NodePair(elem.right, (2*index)+2));
                }
            }
            else{
                if(list.isEmpty()){
                    break;
                }
                maxLength = Math.max(maxLength, list.get(list.size()-1).index - list.get(0).index + 1);
                list.clear();
                que.add(new NodePair(null, -1));
            }
        }
        return maxLength;
    }  

    static void levelOrderIncludingNulls(TreeNode root) {
        int givenLevel = 4; // dummy value, it will come from height fun
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        que.add(new TreeNode(-101));
        int level = 1;
        while (!que.isEmpty() && level <= givenLevel) {
            TreeNode elem = que.poll();
            if (elem == null) {
                list.add(-9);
            } else if (elem.val == -101) {
                // while(list.size() < Math.pow(2, level-101)){
                // list.add(-9);
                // } // no need of this loop, given level keeps check of the elements
                System.out.println(list);
                list.clear();
                level++;
                que.add(new TreeNode(-101));
            } else {
                list.add(elem.val);
                que.add(elem.left);
                que.add(elem.right);
            }
        }
    }

    static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftTreeHeight = height(root.left);
        int rightTreeHeight = height(root.right);
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }
}

class NodePair{
    TreeNode node;
    int index;
    NodePair(TreeNode node, int index){
        this.node = node;
        this.index = index;
    }
}