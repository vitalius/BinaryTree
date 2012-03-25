import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Vitaliy Pavlenko
 *
 * Runs tests for Binary Tree vs Array lookup times
 *
 */
public class Run {

    /**
     * Read, format and tokenize text file into array of strings (words).
     *
     * @param filename
     * @return
     * @throws FileNotFoundException
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
     * Basic Array search (iterate through every element until match is found)
     */
    public static boolean array_search(String [] data, String s) {
        for (String i : data) {
            if (0 == i.compareTo(s))
                return true;
        }
        
        return false;
    }

    /**
     * Returns average time for element look-up in a plain array
     * @param data
     * @return
     */
    public static double array_test(String [] data) {
        double t_sum = 0.0;
        
        for (String s : data) {
            double t_s = System.currentTimeMillis();
            if (false == array_search(data, s))
                System.err.println("Element not found in array_test!");
            t_sum += System.currentTimeMillis() - t_s;
        }
        return t_sum/data.length;        
    }

    /**
     * Returns average time for element look-up in a binary tree
     * @param data
     * @return
     */
    public static double binary_tree_test(String[] data) {
        BinaryTree b = new BinaryTree();
        b.populate(data);
        double t_sum = 0.0;

        for (String s : data) {
            double t_s = System.currentTimeMillis();
            if (false == b.find(s))
                System.out.println("Element not found in binary_tree_test!");
            t_sum += System.currentTimeMillis() - t_s;
        }
        return t_sum/data.length;
    }

    public static void main (String [] args) {

        try {
            int set = 100;
            String [] data = gen_data("data/dragon-rock.txt");

            for (int i = set; i < data.length; i += set) {
                String [] test = new String[i];
                System.arraycopy(data, 0, test, 0, i);
                System.out.println(i + ","
                        + String.format("%.10f", array_test(test)) + ","
                        + String.format("%.10f", binary_tree_test(test)));
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

}

/*

Octave plots

key = load("h-output.csv");
x_max = size(key)(1)*100;

key = load("dr-output.csv");
x_max = size(key)(1)*10;


p = plot(key(1:end, 1), key(1:end,2), "3", key(1:end, 1), key(1:end,3), "1");
axis([0 x_max 0.00 0.06]);
title("Array vs Binary Tree");
xlabel("Number of words");
ylabel("Average look-up time (miliseconds)");
set(p, "LineWidth", 1);
set(gca,'FontSize', 7);

p = plot(key(1:end, 1), key(1:end,2:2), "3", key(1:end, 1), (key(1:end,1)./2.0).*0.00000081, "1");
axis([0 x_max 0.00 0.015]);
title("Array");
xlabel("Number of words");
ylabel("Average look-up time (miliseconds)");
set(p, "LineWidth", 1);
set(gca,'FontSize', 7);

p = plot(key(1:end, 1), key(1:end,3:3), "3", key(1:end, 1), log(key(1:end,1))*0.000023, "1");
axis([0, x_max, 0.00, 0.001]);
title("Binary Tree");
xlabel("Number of words");
ylabel("Average look-up time (miliseconds)");
set(p, "LineWidth", 1);
set(gca,'FontSize', 7);

*/