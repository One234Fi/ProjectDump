/*
Ethan McCarthy
4/19/2022
COSC4315.001
Professor Leonard Brown
Description: The main method for this assignment, 
it reads in the question, calls the IR constructor, 
and feeds it the user's question, then prints the result
*/

import java.util.Scanner;

class Assignment5 {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
                System.out.println("Please enter your question...");
                String question = sc.nextLine();
                String[] questionTokenized = question.split(" ");
                
                IRSystem IR = new IRSystem(questionTokenized);
                IR.start();
                //IR.listStopWords();
                System.out.println(IR.getMostCommonBiword());
	}
}