/**
 * Ethan McCarthy
 * COSC 4315.001
 * 3/21/2022
 * Leonard Brown
 * Implements the IR system and accepts user input to find and output file names from a query word
 */

import java.util.Scanner;

class Assignment3 {
    
    //Prompt for directory and search query
    public static void main(String [] args) {
        
        IRSystem ir = new IRSystem();
        ir.start();
        
        Scanner sc = new Scanner(System.in);
        String in;
        
        System.out.println("Please enter your query:");
        in = sc.nextLine();
        
        ir.search(in);
    }
    
}