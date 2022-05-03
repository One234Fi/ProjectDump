/**
 * Ethan McCarthy
 * 2/1/2022
 * CPU.java simulates the CPU component of the virtual machine
 */

/**
 * Opcode Instruction 
0001 Load AC from memory  
0010 Store AC to memory 
0011 Load AC from REG 
0100 Store AC to REG 
0101 Add to AC from memory 
0110 Load REG with operand  
0111 Add REG to AC 
1000 Multiply REG to AC 
1001 Subtract REG from AC 
1010 Divide AC by REG value (integer division) 
1011 Jump to subroutine starting at the address 
1100 Return from subroutine 
1111 Halt (end of program)
 */
import java.util.ArrayDeque;
//CPU holds the registers and handles the processing
public class CPU {
    //keep track of the # of instructions and subroutines by incrementing these
    static int instructionCount = 0;
    static int subroutineNum = 0;
    //empty string that is added to during execution and then passed to the main method to be printed
    static String output = "";
    
    //initialize variables for the registers
    private static int PC = 0x400;
    private static int IR;
    private static int AC;
    private static int REG;
    
    //FETCH
    public static String processor() {
        IR = Memory.getLocation(PC);
        PC++;
        return decode(IR);
    }
    
    //EXECUTE
    public static String decode(int instruction) {
        //use modulus to split the instruction into the opcode and address
        int opcode = instruction / 0x1000;
        int address = instruction % 0x1000;
        
        //increment instruction count before each instruction execution
        instructionCount++;
        
        //decode instruction and execute appropriately
        switch (opcode) {
            case 0x1: //Load AC from memory
                AC = Memory.getLocation(address); 
                break; 
                
            case 0x2: //Store AC to memory
                Memory.setLocation(address, AC); 
                break; 
                
            case 0x3: //Load AC from REG
                AC = REG; 
                break; 
            
            case 0x4: //Store AC to REG
                REG = AC; 
                break; 
                
            case 0x5: //Add to AC from memory
                AC = AC + Memory.getLocation(address); 
                break; 
                
            case 0x6: //Load REG with operand
                REG = address; 
                break; 
            
            case 0x7: //Add REG to AC
                AC = AC + REG; 
                break; 
            
            case 0x8: //Multiply REG to AC
                AC = AC * REG; 
                break; 
            
            case 0x9: //Subtract REG from AC
                AC = AC - REG; 
                break; 
            
            case 0xA: //Divide AC by REG value (int division)
                AC = AC / REG; 
                break; 
            
            case 0xB: //Jump to subroutine starting at the address
                
                //push register values onto stack before reassigning PC to the subroutine address
                Stack.push(PC);
                Stack.push(IR);
                Stack.push(AC);
                Stack.push(REG);
                PC = address;
                break; 
                
            case 0xC: //Return from subroutine
                //increment subroutine counter before printing header
                subroutineNum++;
                output += "======Before Return from Subroutine " + subroutineNum 
                        + " Status======\n=============Stack Status=============\n";
                //call stack contents method
                output += stackContents();
                output += "=============Registers & Memory Status============="
                        //print all registers as hexadecimal values
                        + "\nPC = " + Integer.toHexString(PC).toUpperCase()
                        + "\nIR = " + Integer.toHexString(IR).toUpperCase()
                        + "\nAC = " + Integer.toHexString(AC).toUpperCase()
                        + "\nREG = " + Integer.toHexString(REG).toUpperCase()
                        //print the relevant memory locations and the instruction count
                        + "\nMemory 940 = " + Integer.toHexString(Memory.getLocation(0x940)).toUpperCase()
                        + "\nMemory 941 = " + Integer.toHexString(Memory.getLocation(0x941)).toUpperCase()
                        + "\nMemory 942 = " + Integer.toHexString(Memory.getLocation(0x942)).toUpperCase()
                        + "\nNumber of instructions executed = " + instructionCount + "\n\n";
                
                //pop stack contents back into the appropriate registers
                REG = Stack.pop();
                AC = Stack.pop();
                IR = Stack.pop();
                PC = Stack.pop();
                
                break; 
                
            case 0xF: //Halt (end of program)
                //mostly copy pasted from case 0xC, could maybe be put in a method for readability
                output += "======End of Program Status======\n" 
                        + "=============Stack Status=============";
                //call stack contents method
                output += stackContents();
                output += "=============Registers & Memory Status============="
                        //print all registers as hexadecimal values
                        + "\nPC = " + Integer.toHexString(PC).toUpperCase()
                        + "\nIR = " + Integer.toHexString(IR).toUpperCase()
                        + "\nAC = " + Integer.toHexString(AC).toUpperCase()
                        + "\nREG = " + Integer.toHexString(REG).toUpperCase()
                        //print the relevant memory locations and the instruction count
                        + "\nMemory 940 = " + Integer.toHexString(Memory.getLocation(0x940)).toUpperCase()
                        + "\nMemory 941 = " + Integer.toHexString(Memory.getLocation(0x941)).toUpperCase()
                        + "\nMemory 942 = " + Integer.toHexString(Memory.getLocation(0x942)).toUpperCase()
                        + "\nNumber of instructions executed = " + instructionCount + "\n\n";
                
                
                return output;
        }
        
        return processor();
    }
    
    //method that handles formatting the stack contents as a string regarless of stack size
    static String stackContents() {
        //no processing required if stack is empty
        if (Stack.isEmpty()) {
            return "\nNo Data in Stack! \n\n";
        }
        
        //make a temporary stack to hold and preserve the values in stack
        ArrayDeque<Integer> temp = new ArrayDeque<>();
        int t, t2;
        String s = "";
        
        //pop all the stack contents into a string while copying them
        while (!Stack.isEmpty()) {
            t = Stack.getHeadAddress();
            t2 = Stack.pop();
            temp.push(t2);
            s += "Stack contents at " + 
                    String.format("%x", t).toUpperCase() + " = " + 
                    String.format("%x", t2).toUpperCase() + "\n";
        }
        
        //return the copied stack contents to the stack
        while (!temp.isEmpty()) {
            Stack.push(temp.pop());
        }
        
        //return the finished string
        return s;
    }
}
