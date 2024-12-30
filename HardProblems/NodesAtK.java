package HardProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NodesAtK {
    public static void main(String[] args) {
        // [3,5,1,6,2,0,8,null,null,7,4]
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(5);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(6);
    root.left.right = new TreeNode(2);
    root.right.left = new TreeNode(0);
    root.right.right = new TreeNode(8);
    root.left.right.left = new TreeNode(7);
    root.left.right.right = new TreeNode(4);

    TreeNode target = root.left; // target is node with value 5
    int k = 2;

    List<Integer> result = distanceK(root, target, k);
    System.out.println(result);
    }

    static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode, TreeNode> parents = getParents(root); // Map to store parent pointers
        HashMap<TreeNode, Boolean> visited = new HashMap<>(); // Map to track visited nodes
        Queue<TreeNode> que = new LinkedList<>(); // BFS queue
        List<Integer> ans = new ArrayList<>(); // Result list
        int currLevel = 0;

        // Initialize BFS
        que.offer(target);
        visited.put(target, true);

        while (!que.isEmpty()) {
            int size = que.size(); // Process nodes level-by-level
            if (currLevel == k) {
                for (TreeNode node : que) {
                    ans.add(node.val); // Collect all nodes at level k
                }
                break;
            }
            currLevel++;
            for (int i = 0; i < size; i++) {
                TreeNode current = que.poll();

                // Check left child
                if (current.left != null && !visited.containsKey(current.left)) {
                    que.add(current.left);
                    visited.put(current.left, true);
                }

                // Check right child
                if (current.right != null && !visited.containsKey(current.right)) {
                    que.add(current.right);
                    visited.put(current.right, true);
                }

                // Check parent node
                TreeNode parent = parents.get(current);
                if (parent != null && !visited.containsKey(parent)) {
                    que.add(parent);
                    visited.put(parent, true);
                }
            }
        }

        return ans;
    }

    static HashMap<TreeNode, TreeNode> getParents(TreeNode root) {
        HashMap<TreeNode, TreeNode> hash = new HashMap<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        que.add(null);
        while (!que.isEmpty()) {
            TreeNode elem = que.poll();
            if (elem != null) {
                if (elem.left != null) {
                    que.add(elem.left);
                    hash.put(elem.left, elem);
                }
                if (elem.right != null) {
                    que.add(elem.right);
                    hash.put(elem.right, elem);
                }
            } else {
                if (que.isEmpty()) {
                    break;
                } else {
                    que.add(null);
                }
            }
        }
        return hash;
    }

}

// What i learned: When you have to traverse back use a map to store the parent
// node
