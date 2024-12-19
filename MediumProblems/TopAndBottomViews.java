package MediumProblems;

import java.util.ArrayList;
// import java.util.Comparator;
import java.util.List;

class Items{
    Node node;
    int hzIndex;
    int veIndex;

    Items(Node node, int hzIndex, int veIndex){
        this.node = node;
        this.hzIndex = hzIndex;
        this.veIndex = veIndex;
    }
}

public class TopAndBottomViews {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println(bottomView(root));
    }


    static List<Integer> topView(Node root){
        List<Integer> ans = new ArrayList<>();
        List<Items> list = new ArrayList<>();
        helper(root, list, 0, 0);
        // list.sort(Comparator.comparingInt(item -> item.hzIndex));

        // Better do it with both hz and ve index
        list.sort((a, b) -> {
            if (a.hzIndex != b.hzIndex) {
                return Integer.compare(a.hzIndex, b.hzIndex);
            } else {
                return Integer.compare(a.veIndex, b.veIndex);
            }
        });

        int prevHzIndex = Integer.MIN_VALUE;
        for(Items item: list){
            if(item.hzIndex != prevHzIndex){
                ans.add(item.node.data);
            }
            prevHzIndex = item.hzIndex;
        }
        return ans;
    }

    static List<Integer> bottomView(Node root){
        List<Integer> ans = new ArrayList<>();
        List<Items> list = new ArrayList<>();
        helper(root, list, 0, 0);

        list.sort((a,b)->{
            if(a.hzIndex != b.hzIndex){
                return Integer.compare(a.hzIndex, b.hzIndex);
            }
            else{
                return Integer.compare(b.veIndex, a.veIndex);
            }
        });

        int prevHzIndex = Integer.MIN_VALUE;
        for(Items item: list){
            if(item.hzIndex != prevHzIndex){
                ans.add(item.node.data);
            }
            prevHzIndex = item.hzIndex;
        }

        return ans;

    }

    static void helper(Node root, List<Items> ans, int hI, int vI){
        if(root == null){
            return;
        }
        Items item = new Items(root, hI, vI);
        ans.add(item);
        helper(root.left, ans, hI-1, vI+1);
        helper(root.right, ans, hI+1, vI+1);
    }
}
