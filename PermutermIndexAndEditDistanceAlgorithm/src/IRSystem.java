
import java.io.*;
import java.util.*;
class IRSystem {
    private File[] collection;
    //an ArrayList that will keep track of all the words seen 
    private ArrayList<String> words;
  
    public IRSystem() {
        collection = getFiles();
        words = new ArrayList<>();
    }
  
    public File[] getFiles() {
        File[] files = null;
        try {
            System.out.println();
            System.out.print("Enter name of a directory> ");
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
        String s = w.toLowerCase();
        //replace all punctuation in s with ""
        s = s.replaceAll("\\p{Punct}", "");
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
                while (sc.hasNextLine()) {
                    StringTokenizer st = new StringTokenizer(sc.nextLine());
                    while (st.hasMoreTokens()) {
                        String inputWord = st.nextToken();
                        //System.out.print(inputWord + "\t");
                        String outputWord = process(inputWord);
                        //System.out.print(outputWord + "\t");
                        if (!seenBefore(outputWord)) {
                            //add outputWord to the list of words after it has been processed 
                            words.add(outputWord);
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println("Error in start:  " + e.toString());
        }
    }
    
    public ArrayList<String> getWords() {
        return words;
    }
}