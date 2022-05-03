/**
 * Ethan McCarthy
 * 2/1/2022
 * Memory.java simulates the memory component of the virtual machine and holds the main method
 */

import java.io.*;
import java.util.Scanner;
//memory holds all the data (and the main method, which handles I/O and calls the methods to actually run the program(s))
public class Memory {
    //initialize the memory, 0-1024 for the stack and 1025-4096 for user memory
    private static int[] memory = new int[4096];
    
    //read a value at the location of address
    public static int getLocation(int address) {
        return memory[address];
    }
    
    //set the value at address to the int value
    public static void setLocation(int address, int value) {
        memory[address] = value;
    }
    
    public static void main(String[] args) throws IOException{
        //variables to hold the default names for the input and output files
        String input = "input.txt";
        String output = "output.txt";
        
        //overwrites the input and output variables with paths supplied by the user
        if (args.length > 0) {
            input = args[0];
            output = args[1];
        }
        
        //create a scanner to read the input file and a filewriter to create the output file
        Scanner sc = new Scanner(new File(input));
        FileWriter fw = new FileWriter(output);
        
        //temporary variables that will be used to parse data from the input file into memory
        String line, temp, temp2;
        int t, t2;
        
        //loop through each line, if it has a '.' parse the instructions from it into memory, otherwise ignore it
        while(sc.hasNext()) {
            line = sc.nextLine();
            //determine if the line has an instruction
            if (line.contains(".")) {
                //find the instruction address and store it in t
                temp = line.substring(line.indexOf(' ')+1, line.indexOf(' ', line.indexOf(' ')+1));
                t = Integer.parseInt(temp, 16);
                
                //find the instruction and store it in t2
                temp2 = line.substring(line.indexOf(' ', line.indexOf(' ')+1)+1, line.indexOf(';')-1);
                t2 = Integer.parseInt(temp2, 16);
                
                //set the memory location at the instruction address to the data value
                setLocation(t, t2);
            }
        }
        
        //start the program by calling the fetch/processor method
        fw.write(CPU.processor());
        
        
        fw.close();
        
    }
    
    
    
}
