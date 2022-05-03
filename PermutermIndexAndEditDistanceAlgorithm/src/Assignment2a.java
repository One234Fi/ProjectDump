/**Ethan McCarthy
 * COSC 4315.001
 * Professor Leonard Brown
 * Description: Program uses an IRSystem to tokenize a dictionary
 * of words, then creates and prints the permutations of the words
 * sorted into an alphabetical index.
 */

public class Assignment2a {

    public static void main(String[] args) {
        IRSystem system = new IRSystem();
        system.start();
        PermutermIndex index = new PermutermIndex(system.getWords());
        index.start();
    }
    
}
