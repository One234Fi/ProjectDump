/**
 * Ethan McCarthy
 * 2/1/2022
 * Stack.java provides methods to store values LIFO in memory at addresses 0 through 3FF
 */

public class Stack {
    //head holds the address to the top of the stack in memory
    private static int head = 0x400;
    
    
    //push item onto the top of the stack and increment head
    public static void push(int item) {
        //stops the method and notifies the user if the stack is full
        if(head < 0) {
            System.out.println("Error, Stack is full");
            return;
        }
        Memory.setLocation(--head, item);
    }
    
    //if the stack is not empty, returns the value at the top of the stack, 
    //then decrements head, otherwise, returns -1 and notifies the user
    public static int pop() {
        if (!isEmpty()) {
            return Memory.getLocation(head++);
        }
        System.out.println("Error, Stack is empty");
        return -1;
    }
    
    //getter for the head of the stack
    public static int getHeadAddress() {
        return head;
    }
    
    //boolean that checks if the stack is empty
    public static boolean isEmpty() {
        if (head > 0x3FF) {
            return true;
        }
        return false;
    }
}
