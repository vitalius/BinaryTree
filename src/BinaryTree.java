/**
 * User: vitaliy
 * Date: 3/24/12
 * Time: 9:35 PM
 */
public abstract class BinaryTree {
    Node root;
    
    public BinaryTree() {
        root = null;
    }

    public boolean populate(String [] data) {
        for (String s : data) {
            if (null == (root = insert(root, new Node(s))))
                return false;
        }
        return true;
    }

    private int depth(Node c) {
        if (null == c)
            return 0;
        
        int h1 = 1;
        int h2 = 1;

        if (null != c.left)
            h1 += depth(c.left);
        if (null != c.right)
            h2 += depth(c.right);

        return (h1 > h2) ? h1 : h2;
    }
    
    public int depth() {
        return depth(root);
    }

    public boolean find(String s) {
        return find(root, new Node(s));
    }
    
    public abstract boolean find (Node c, Node s);
    public abstract Node insert(Node c, Node s);
}
