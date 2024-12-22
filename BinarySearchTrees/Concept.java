package BinarySearchTrees;

public class Concept {
    public static void main(String[] args) {

    }

    static TreeNode search(TreeNode root, int k) {
        TreeNode node = root;
        while (node != null) {
            if (node.val == k) {
                return node;
            } else if (node.val < k) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return new TreeNode(-1);
    }

    static TreeNode min(TreeNode root) {
        TreeNode node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    static TreeNode max(TreeNode root) {
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    static int findCeil(TreeNode root, int val) {
        if (root == null)
            return -1;
        TreeNode node = root;
        TreeNode ans = new TreeNode(-1);
        while (node != null) {
            if (node.val < val) {
                node = node.right;
            } else {
                ans = node;
                node = node.left;
            }
        }
        return ans.val;
    }

    static int findFloor(TreeNode root, int val) {
        if (root == null)
            return -1;
        TreeNode node = root;
        TreeNode ans = new TreeNode(-1);
        while (node != null) {
            if (node.val > val) {
                node = node.left;
            } else {
                ans = node;
                node = node.right;
            }
        }
        return ans.val;
    }

    static TreeNode insertNode(TreeNode root, int val) { // Given that val does not exist in root
        TreeNode toInsert = new TreeNode(val);
        TreeNode node = root;
        while (true) {
            if (node.val < val) {
                if (node.right == null) {
                    node.right = toInsert;
                    return root;
                }
                node = node.right;
            } else if (node.val > val) {
                if (node.left == null) {
                    node.left = toInsert;
                    return root;
                }
                node = node.left;
            }
        }
    }

    static TreeNode deleteNode(TreeNode root, int val) {
        // From the question, we can understand that either node will be replaced by its
        // left child or its right child without violating the conditions
        // Say if node is replaced by node.left, left becomes node and left is obv <
        // right
        // Say if node is replaced by node.right, right becomes node and obv right >
        // left

        if (root.val == val) { // We have to seperately handle this case since the root can only be replaced by
                               // either Largest element on left OR smallest element on right

            if (root.left == null && root.right == null) {
                return null;
            }

            else if (root.left != null) {
                TreeNode node = root.left;
                while (node.right != null) {
                    node = node.right;
                }
                root.val = node.val;
                node = null;

            } else {
                TreeNode node = root.right;
                while (node.left != null) {
                    node = node.left;
                }
                root.val = node.val;
                node = null;
            }
        }
        TreeNode node = root;
        while (true) {
            if (root == null) {
                break;
            }
            if (root.val == val) {
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.left == null) {
                    root.val = root.right.val;
                    root.right = null;
                } else {
                    root.val = root.left.val;
                    root.left = null;
                }
                break;
            }
            root = (root.val < val) ? root.right : root.left;
        }
        return node;
    }

    static TreeNode iterativeDelete(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode current = root;

        if(root == null){
            return null;
        }

        // Step-1: Find node
        while (current != null) {
            if (val < current.val) {
                parent = current;
                current = current.left;
            } else if (val > current.val) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }

        // Check if it is null
        if (current == null) {
            return root;
        }

        // Step-2: Handle deletion cases

        // Case-1: current has no childs
        if(current.left == null && current.right == null){
            if(current == root){
                return null;
            }
            if(parent.left == current){
                parent.left = null;
            }
            else if(parent.right == current){
                parent.right = null;
            }
        }
        // Current has one child
        else if(current.left != null || current.right != null){
            if(current.left != null){
                parent.left = current.left;
            }
            else{
                parent.right = current.right;
            }
        }

        // Curent has two childs
        else{
            TreeNode node = current.left;
            TreeNode nodeParent = null;
            while(node.right != null){
                nodeParent = node;
                node = node.right;
            }
            if(nodeParent != null){
                current.val = node.val;
                parent.right = node.left;
            }
            else{
                current.val = node.val;
                current.left = null;
            }
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}