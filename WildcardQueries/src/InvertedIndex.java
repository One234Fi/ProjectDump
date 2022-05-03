/**
 * Ethan McCarthy
 * COSC 4315.001
 * 3/21/2022
 * Leonard Brown
 * Contains the functionality for implementing inverted indexes
 */

import java.util.ArrayList;

class InvertedIndex {
    private String term;
    private int collectionFrequency;
    private int[][] docList;
    
    public InvertedIndex(String term, ArrayList<ArrayList<String>> collection) {
        this.term = term;
        collectionFrequency = 0;
        docList = build(collection);
    }
    
    public String getTerm() {
        return term;
    }
    
    public int getFrequency() {
        return collectionFrequency;
    }
    
    public int[][] getDocList() {
        return docList;
    }
    
    private int[][] build(ArrayList<ArrayList<String>> collection) {
        int [][] output;
        int freq, numDocs = 0;
        for (int i = 0; i < collection.size(); i++) {
            freq = 0;
            for (String ct : collection.get(i)) {
                if(ct.equals(term)) {
                    freq++;
                }
            }
            if (freq > 0) {
                numDocs++;
            }
        }
        output = new int [numDocs][2];
        numDocs = 0;
        for (int i = 0; i < collection.size(); i++) {
            freq = 0;
            for (String ct : collection.get(i)) {
                if(ct.equals(term)) {
                    //System.out.println("Found! " + ct + " in document " + i);
                    freq++;
                }
            }
            if (freq > 0) {
                output[numDocs][0] = i; 
                output[numDocs][1] = freq;
                numDocs++;
                collectionFrequency += freq;
            }
        }
        
        return output;
    }
}