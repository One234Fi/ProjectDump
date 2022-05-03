/**Ethan McCarthy
 * COSC 4315.001
 * Professor Leonard Brown
 * Provides the functionality for a permuterm index
 */

import java.util.ArrayList;
import java.util.Collections;

public class PermutermIndex {
    private ArrayList<String> terms;
    private ArrayList<String> words;
    
    //constructor assigns values for the lists: "terms" and "words"
    public PermutermIndex(ArrayList words) {
        terms = new ArrayList<>();
        this.words = words;
    }
    
    //on start, create permuterms for each word, then print all terms
    public void start() {
        for(String s : this.words) {
            createPermuterms(s);
        }
        printTerms();
    }
    
    //add the special character to the input word, add its permutations
    //to a list, then add all the generated terms to the main list of terms
    public void createPermuterms(String word) {
        String term = "$" + word;
        ArrayList<String> newTerms = new ArrayList<>();
        
        calc(term, newTerms);
        
        for (String s : newTerms) {
            s += " " + word;
            terms.add(s);
        }
    }
    
    //recursively calculates the permutations of "s" and puts them in "list"
    private String calc (String s, ArrayList list) {
        if (s.endsWith("$")) {
            list.add(s);
            return s;
        }
        String lastChar = s.substring(s.length()-1);
        String remainder = s.substring(0, s.length()-1);
        String output = lastChar + remainder;
        list.add(output);
        return calc(output, list);
    } 
    
    //Print the list of permutations and their original values in two columns
    public String printTerms() {
        Collections.sort(terms);
        String term, word, output;
        output = "Permutation\t\t\tOriginal Term\n";
        System.out.printf("%-16s %-16s\n", "Permutation", "Original Term");
        for (String s : terms) {
            term = s.substring(0, s.indexOf(" "));
            word = s.substring(s.indexOf(" ") + 1);
            output += term + "\t\t\t\t" + word + "\n";
            System.out.printf("%-16s %-16s\n", term, word);
        }
        return output;
    }
}
