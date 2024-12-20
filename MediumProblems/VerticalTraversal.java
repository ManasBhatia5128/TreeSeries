package MediumProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;

public class VerticalTraversal {
    // Solutionw without sorting
     private static void dfs(TreeNode node, int hzIndex, int veIndex, Map<Integer, List<Integer>> map) {
        if (node == null) return;

        // Insert the node into the map based on its horizontal index
        map.putIfAbsent(hzIndex, new ArrayList<>());
        map.get(hzIndex).add(node.data);
        
        // DFS for left and right children with adjusted horizontal and vertical indices
        dfs(node.left, hzIndex - 1, veIndex + 1, map);  // Left child
        dfs(node.right, hzIndex + 1, veIndex + 1, map); // Right child
    
}
