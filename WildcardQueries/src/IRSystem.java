/**
 * Ethan McCarthy
 * COSC 4315.001
 * 3/21/2022
 * Leonard Brown
 * Contains the functionality for creating and searching an IRSystem of files
 */

import java.io.*;
import java.util.*;
class IRSystem {
    private File[] collection;
    private ArrayList<ArrayList<String>> collectionTokenized;
    private ArrayList<InvertedIndex> indexes;
    private PermutermIndex permIndex;
    
    //an ArrayList that will keep track of all the words seen 
    private ArrayList<String> words;
  
    public IRSystem() {
        collection = getFiles();
        collectionTokenized = new ArrayList<ArrayList<String>>();
        words = new ArrayList<String>();
        indexes = new ArrayList<InvertedIndex>();
    }
  
    public File[] getFiles() {
        File[] files = null;
        try {
            System.out.println();
            System.out.print("Enter the name of the collection directory: ");
            Scanner scan = new Scanner(System.in);
            File dir = new File(scan.nextLine());
            files = dir.listFiles();
            
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("Caught error in getFiles: " + e.toString());
        }
        return files;
    }
    
    public String process(String w) {
        //put a lowercase version of w into a new string s
        String s = w.toLowerCase().trim();
        //replace all punctuation in s with ""
        s = s.replaceAll("\\p{Punct}?", "");
        //if the last character of s is "s", remove it
        if (s.length() > 1 && s.substring(s.length()-1).equals("s")) {
            s = s.substring(0, s.length()-1);
        }
        //if the last character of s is "ing" remove it, if statement is separated 
        //from "remove s" if statement so that both execute for words like "servings"
        if (s.length() > 3 && s.substring(s.length()-3).equals("ing")) {
            s = s.substring(0, s.length()-3);
        }
        return s;
    }
  
    private boolean seenBefore(String w) {
        //If words does not contain w, w has not been seen before. 
        //If w is in the words list, it has been seen before
        return words.contains(w);
    }
  
    public void start() {
        try {
            for (File f : collection) {
            Scanner sc = new Scanner(f);
            ArrayList<String> s = new ArrayList<String>();
                while (sc.hasNextLine()) {
                    StringTokenizer st = new StringTokenizer(sc.nextLine());
                    while (st.hasMoreTokens()) {
                        String inputWord = st.nextToken();
                        String outputWord = process(inputWord);
                        
                        if (!outputWord.equals("")) {
                            s.add(outputWord);
                            if (!seenBefore(outputWord)) {
                                //add outputWord to the list of words after it has been processed 
                                words.add(outputWord);
                            }
                        }
                    }
                }
                collectionTokenized.add(s);
            }
            //put words into alphabetical order
            Collections.sort(words);
            buildIndexes();
            permIndex = new PermutermIndex(words);
            //permIndex.start();
        }
        catch(Exception e) {
            System.out.println("Error in start:  " + e.toString());
        }
    }
    
    public void search(String query) {
        //check term against the permutation index
        ArrayList<String> searchList = permIndex.getWord(query);
        
        //loop through each term in searchList and find each acceptable index to use
        //calculate and store TF-iDF for each document
        ArrayList<Integer> docs = new ArrayList<Integer>();
        ArrayList freqs = new ArrayList();
        for (String s : searchList) {
            for (InvertedIndex inv : indexes) {
                if (inv.getTerm().equals(s)) {
                    double iDF = 1.0 / inv.getDocList().length;
                    for (int i = 0; i < inv.getDocList().length; i++) {
                        int docID = inv.getDocList()[i][0];
                        int docFreq = inv.getDocList()[i][1];
                        
                        double TF = Math.log(1.0 + ((double)docFreq / (double)collectionTokenized.get(docID).size()));
                        double TFiDF = TF * iDF; 
                        docs.add(docID);
                        freqs.add(TFiDF);
                    }
                }
            }
        }
        
        //sort documents by calculated TF-iDF
        ArrayList<Integer> sortedDocs = new ArrayList<Integer>();
        int index;
        while (!freqs.isEmpty()) {
            index = freqs.indexOf(Collections.max(freqs));
            sortedDocs.add(docs.get(index));
            freqs.remove(index);
            docs.remove(index);
        }
        
        //remove duplicates
        Set<Integer> removedDuplicates = new LinkedHashSet<Integer>();
        removedDuplicates.addAll(sortedDocs);
        sortedDocs.clear();
        sortedDocs.addAll(removedDuplicates);
        
        //print document names
        for (int docID : sortedDocs) {
            System.out.println(collection[docID].getName());
        }
    }
    
    public void buildIndexes() {
        InvertedIndex inv;
        for (String s : words) {
            inv = new InvertedIndex(s, collectionTokenized);
            indexes.add(inv);
        }
    }
    
    @Override
    public String toString() {
        String s = "";
        
        for (ArrayList<String> ct : collectionTokenized) {
            for (String ct1 : ct) {
                s += ct1 + " ";
            }
            s += "\n";
        }
        
        return s;
    }
}