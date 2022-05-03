/*
Ethan McCarthy
Class: COSC 3355.001
Professor: Nary Subramanian
Date: 4/11/2022
Description: Implements a monitor object to support concurrent access for the encapsulated integer "num"
*/

public class ethanMcCarthySynchronizedNumberProject3 {
    //the integer variable protected by this monitor
    private static int num = 0;
    
    //Increment num, then print out the name of the thread that incremented it and the value of num
    public static synchronized void runCritSect(String name, int i) {
        num++;
        System.out.println(name + ": " + i + ", num = " + num);
    }
    
    //Increment num
    public static synchronized void incrementNum() {
        num++;
    }
    
    //Get the value of num
    public static synchronized int getNum() {
        return num;
    }
}