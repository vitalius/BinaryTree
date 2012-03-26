import java.util.Random;
import java.util.Hashtable;
/**
 * User: vitaliy
 * Date: 3/24/12
 * Time: 9:29 PM
 */
public class Unordered extends BinaryTree{
    Random rand = new Random();
    Hashtable<String, Boolean> dict = new Hashtable<String, Boolean>();

    public Unordered() {
        super();
    }

    public Node insert(Node current, Node leaf) {
        if (dict.containsKey((String)leaf.key))
            return current;

        if (null == leaf)
            return null;

        if (null == current) {
            dict.put((String)leaf.key, true);
            return leaf;
        }

        int cmp_score = rand.nextInt(2);
        
        if (cmp_score > 0)
            current.left = insert(current.left, leaf);
        else
            current.right = insert(current.right, leaf);

        return current;
    }


    public boolean find(Node current, Node s) {
        if (null == current)
            return false;

        if (0 == current.compare(s))
            return true;

        return find(current.left, s) ? true : find(current.right, s);
    }
}
