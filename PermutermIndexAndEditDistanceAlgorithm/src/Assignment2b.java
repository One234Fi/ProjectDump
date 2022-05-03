/**Ethan McCarthy
 * COSC 4315.001
 * Professor Leonard Brown
 * Description: Program asks the user for two words, then 
 * outputs the Levenshtein distance between the words
 */

import static java.lang.Math.min;
import java.util.Scanner;

public class Assignment2b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter two words separated by a space to find their edit distance...");
        String one = sc.next(), two = sc.next();
        int[][] dist = distance(one, two);
        System.out.printf("The edit distance matrix for \"%s\" and \"%s\" is:\n", one, two);
        System.out.println(printMat2d(dist));
        sc.close();
    }
    
    //implementation of the algorithm to find edit distance between two words
    static int[][] distance (String one, String two) {
        int len1 = one.length();
        int len2 = two.length();
        int [][] mat = new int[len1+1][len2+1];
        
        //generate the first row and column by incrementing
        for (int i = 1; i <= len1; i++) {
            mat[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            mat[0][j] = j;
        }
        
        //for each position in the matrix, set it to the smallest of:
        //          1) the value above + 1
        //          2) the value to the left + 1
        //          3) the value above and to the left + func()
        //where func() is 0 if the correlating characters are the same, and 1 otherwise
        for (int i = 1; i < len1+1; i++) {
            for (int j = 1; j < len2+1; j++) {
                mat[i][j] = min( mat[i-1][j-1] + func(i, j, one, two), 
                        min(mat[i-1][j] + 1, mat[i][j-1] + 1));
            }
        }
        
        //return the bottom right value of the matrix
        return mat;
    }
    
    //implementation of func() as explained above
    static int func(int i, int j, String s1, String s2) {
        if (s1.charAt(i-1) == s2.charAt(j-1)) {
            return 0;
        }
        return 1;
    }
    
    static String printMat2d(int[][]mat) {
        String s = "";
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                s += mat[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
