package MediumProblems;

public class SameTree {
    static boolean isSameTree(Node p, Node q) {
        // if(p == null && q == null){
        //     return true;
        // }
        // if(p == null || q == null){
        //     return false;
        // }

        // These two conditions can be merged as
        if(p == null || q == null){
            return (p==q);
        }


        // if(p.data != q.data){
        //     return false;
        // }
        // boolean leftTree = isSameTree(p.left, q.left);
        // boolean rightTree = isSameTree(p.right, q.right);
        // if(!leftTree || !rightTree){
        //     return false;
        // }
        // return true;

        // This can be replaced by
        return (p.data == q.data) && (isSameTree(p.left, q.left)) && isSameTree(p.right, q.right);
    }
}
