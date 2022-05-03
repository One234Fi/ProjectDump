/*
Ethan McCarthy
4/19/2022
COSC4315.001
Professor Leonard Brown
Description: The IR system prompts for a file, then searches for consecutive proper nouns
and/or consecutive words that start with uppercase letters. It discards any biwords that match 
with any words from the user's quesion, and returns the biword with the most occurences. The
system does not have any way of determining a more likely candidate in the result of a tie and 
will pick whichever biword it saw first
*/

import java.io.*;
import java.util.*;
class IRSystem {
    private File collection;
    
    //an ArrayList that will keep track of all the words seen 
    private ArrayList<String> words;
    //keep track of how often each of the words is seen
    private ArrayList<String> unStemmedWords;
    private ArrayList<Integer> counts;
    
    private String[] stopWords;
    
    //initialize all the arrays and arrayLists needed to keep track of biwords and the file
    public IRSystem(String[] stopWords) {
        collection = getFiles();
        words = new ArrayList<String>();
        this.stopWords = tokenizeArray(stopWords);
        unStemmedWords = new ArrayList<>();
        counts = new ArrayList<>();
    }
    
    //method that loops through a given string array, 
    //processes each string in it, 
    //and returns a new array of the processed strings
    private String[] tokenizeArray(String[] s) {
        String [] temp = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            temp[i] = process(s[i]);
        }
        return temp;
    }
    
    //list the stop words
    public void listStopWords() {
        for (String s : this.stopWords) {
            System.out.println(s);
        }
    }
    
    //see if a given string "check" is a stop word from the user's question
    public boolean checkStopWords(String check) {
        for (String s : stopWords) {
            if (s.contains(check)) {
                return true;
            }
        }
        return false;
    }
    
    //find the biword with the highest count, 
    //make sure it doesn't have any leftover punctuation, and return it
    public String getMostCommonBiword() {
        int max = 0;
        for (int i : counts) {
            if (i > max) {
                max = i;
            }
        }
        int index = counts.indexOf(max);
        String output = unStemmedWords.get(index);
        output = output.replaceAll("\\p{Punct}?", "");
        
        return output;
    }
    
    //find the user's file
    public File getFiles() {
        File files = null;
        try {
            System.out.println();
            System.out.print("Please enter the text file... ");
            Scanner scan = new Scanner(System.in);
            files = new File(scan.nextLine());
            System.out.println();
        }
        catch (Exception e) {
            System.out.println("Caught error in getFiles: " + e.toString());
        }
        return files;
    }
    
    //stem the input string "w"
    public String process(String w) {
        //put a lowercase version of w into a new string s
        String s = w.toLowerCase().trim();
        //replace all punctuation in s with ""
        s = s.replaceAll("\\p{Punct}?", "");
        
        //if the last characters of s are "es", remove them
        if (s.length() > 2 && s.substring(s.length()-2).equals("es")) {
            s = s.substring(0, s.length()-2);
        }
        
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
    
    //has the string been seen before?
    private boolean seenBefore(String w) {
        //If words does not contain w, w has not been seen before. 
        //If w is in the words list, it has been seen before
        return words.contains(w);
    }
    
    //Scan the file (collection) and count all the relevant biwords
    public void start() {
        String wordBefore = "";
        try {
            Scanner sc = new Scanner(collection);
            while (sc.hasNextLine()) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                while (st.hasMoreTokens()) {
                    String inputWord = st.nextToken();
                    String processedWord = process(inputWord);
                    if (checkStopWords(processedWord)) {
                        //stop word, skip and set the wordBefore to ""
                        wordBefore = "";
                        //System.out.println(processedWord + " is a stop word!");
                    } else {
                        if (Character.isUpperCase(inputWord.charAt(0))) {
                            if (wordBefore.equals("")) {
                                //if there is no wordBefore, this becomes wordBefore
                                wordBefore = inputWord;
                            } else {
                                //there was a word before, so this is a valid biword to be added to words
                                //add the biword to words, then set this token to wordBefore and continue
                                if (!seenBefore(process(wordBefore + " " + processedWord))) {
                                    words.add(process(wordBefore) + " " + processedWord);
                                    unStemmedWords.add(wordBefore + " " + inputWord);
                                    counts.add(1);
                                    wordBefore = inputWord;
                                } else {
                                    int i = counts.get(words.indexOf(process(wordBefore + " " + processedWord))) + 1;
                                    counts.set(words.indexOf(process(wordBefore + " " + processedWord)), i);
                                }
                            }
                        } else {
                            //not a biword, set word before to blank
                            wordBefore = "";
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println("Error in start:  " + e.toString());
        }
    }
}