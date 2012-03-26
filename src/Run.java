import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Vitaliy Pavlenko
 *
 * Runs tests for Binary Tree lookup times
 *
 */
public class Run {

    /**
     * Read, format and tokenize text file into array of strings (words).
     */
    public static String [] gen_data(String filename) throws FileNotFoundException {
        String data = "";
        Scanner s = new Scanner(new FileReader(filename));
            try {
                while (s.hasNextLine())
                    data += s.nextLine();
            } finally {
                s.close();
            }
        return data.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");
    }

    /**
     * Reduce to unique words
     */
    public static String[] unique (String [] data) {
        Hashtable<String, Boolean> dict = new Hashtable<String, Boolean>();
        for (String w : data)
            dict.put(w, true);

        return dict.keySet().toArray(new String[0]);
    }

    /**
     * Returns average time for element look-up in a ordered binary tree
     */
    public static String ordered_tree_test(String[] data) {
        Ordered b = new Ordered();
        b.populate(data);
        double t_sum = 0.0;

        for (String s : data) {
            double t_s = System.currentTimeMillis();
            if (false == b.find(s))
                System.out.println("Element not found in ordered_tree_test!");
            t_sum += System.currentTimeMillis() - t_s;
        }
        return String.format("%.10f", t_sum/data.length);
    }


    /**
     * Returns average time for element look-up in a unordered binary tree
     * using pre-order tree walk
     */
    public static String unordered_tree_test(String[] data) {
        Unordered b = new Unordered();
        b.populate(data);
        double t_sum = 0.0;

        for (String s : data) {
            double t_s = System.currentTimeMillis();
            if (false == b.find(s))
                System.out.println("Element not found in unordered_tree_test!");
            t_sum += System.currentTimeMillis() - t_s;
        }
        return String.format("%.10f", t_sum/data.length) + "," + b.depth();
    }


    
    public static void main (String [] args) {

        try {
            int set = 1;
            String [] data = gen_data("data/dragon-rock.txt");
            data = unique(data);

            for (int i = set; i < data.length; i += set) {
                String [] test = new String[i];
                System.arraycopy(data, 0, test, 0, i);
                //System.out.println(test.length + "," + unique(test).length);
                System.out.println(i + "," + ordered_tree_test(test) + "," + unordered_tree_test(test));
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

}