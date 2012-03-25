/**
 * User: vitaliy
 * Date: 3/24/12
 * Time: 2:50 PM
 */
public class Node {
    public Object key = null;
    public Node left = null;
    public Node right = null;
    
    public Node(Object d) {
        key = d;
    }
    
    public int compare(Node n) {
        if (n.key instanceof String && this.key instanceof String) {
            return ((String) n.key).compareTo((String) this.key);
        }
        return 0;
    }

    /**
     * returns a pretty node representation in a string format
     *
     *      dataParent
     *        /    \
     * dataLeft  dataRight
     *
     */
    public String toString() {
        String s = "   " + key.toString() + "\n  /" + "   \\ \n";

        if (null == left)
            s += "null  ";
        else
            s += left.key + "  ";

        if (null == right)
            s += "null";
        else
            s += right.key;
        
        return s;
    }
}
