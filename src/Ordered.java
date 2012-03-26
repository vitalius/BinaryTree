/**
 * User: vitaliy
 * Date: 3/24/12
 * Time: 2:49 PM
 */
public class Ordered extends BinaryTree {

    public Ordered() {
        super();
    }


    public Node insert(Node current, Node leaf) {
        if (null == leaf)
            return null;
        
        if (null == current)
            return leaf;

        int cmp_score = current.compare(leaf);

        if (cmp_score == 0)
            return current;

        if (cmp_score > 0)
            current.left = insert(current.left, leaf);
        else
            current.right = insert(current.right, leaf);

        return current;
    }


    public boolean find(Node current, Node s) {
        if (null == current)
            return false;

        int cmp_score = current.compare(s);

        if (cmp_score == 0)
            return true;

        if (cmp_score > 0)
            return find(current.left, s);
        else
            return find(current.right, s);
    }
}
