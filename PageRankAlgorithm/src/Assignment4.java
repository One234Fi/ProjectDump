/**
 * Ethan McCarthy
 * 4/4/2022
 * COSC 4315.001
 * Professor: Leonard Brown
 * Assignment 4
 * Description: Finds and prints the page ranks for each node of the graph 
 * from part one of this assignment at the user-specified time "k"
 */

import java.util.Scanner;

class Assignment4 {
    //The probability matrix for the graph from part one
    static double [][] probMat = {
        {0, 0, 0, 1, 0},
        {0.5, 0, 0, 0.5, 0},
        {0, 1.0/3, 0, 1.0/3, 1.0/3},
        {0.5, 0, 0, 0, 0.5},
        {0, 0, 0, 0, 0}
    };
    
    //Initialize an array that will be used to store all of the page ranks
    static double [][] nodeRanks;
    //Initialize x, the probability that a teleport will occur, to be filled in by the user
    static double x;
    
    //main method prompts the user for "x" and "k", calls methods to fill the nodeRank matrix, then prints the values at "k"
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the probability that a teleport will occur...");
        x = sc.nextDouble();
        System.out.println("Please enter the number of iterations you would like to calculate...");
        int k = sc.nextInt();
        
        //create the empty array with initialArr(), then fill it with generateMatrix()
        nodeRanks = initialArr(5, k);
        generateMatrix(k);
        
        //print the page ranks at time/index "k"
        for (int i = 0; i < 5; i++) {
            System.out.println("Page rank for node " + (i+1) + ": " + nodeRanks[i][k]);
        }
        
        sc.close();
    }
    
    //creates the empty array at the appropriate size, 
    //then fills the first row with the initial rank values (1/N)
    static double[][] initialArr(int numNodes, int k) {
        double [][] temp = new double[numNodes][k+1];
        
        for (int i = 0; i < numNodes; i++) {
            temp[i][0] = 1.0/numNodes;
        }
        
        return temp;
    }
    
    //Loops through every value in the rank matrix, except the first row, and calls calculateRank() for each
    static void generateMatrix(int k) {
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < 5; j++) {
                calculateRank(j, i);
            }
        }
    }
    
    //Fills nodeRank[i][j] with the calculation: 
    //Pk[j] = x * (1/N) + (1 - x) * (the sum from 0 to i {probMat[i][j] * nodeRanks[i][k-1]}
    static void calculateRank(int j, int k) {
        double sum = 0.0;
        for (int i = 0; i < 5; i++) {
            sum += (double) probMat[i][j] * (double) nodeRanks[i][k-1];
        }
        
        nodeRanks[j][k] = x * (1.0/5) + (1.0 - x) * sum;
    }
}