/*
Ethan McCarthy
Class: COSC 3355.001
Professor: Nary Subramanian
Date: 4/11/2022
Description: The provided "MyThread.java" class with a semaphore to manage concurrency added
*/
import java.util.concurrent.*;

public class ethanMcCarthySemaphoreProject3 implements Runnable {
	private static int num = 0;
	private String name;
	private Thread myThread;
        
        //a reference to a semaphore so that multiple threads can be managed by the same semaphore
	Semaphore s;
	
	ethanMcCarthySemaphoreProject3(String threadName, Semaphore s) {
	    name = threadName; 
	    myThread = new Thread(this, name);
            this.s = s;
	
	    System.out.println("New thread: " + myThread);
	    myThread.start();
	}

	@Override
	public void run() {
		for(int i = 1; i <= 20; i++) {
                    
                    //get permission from the semaphore before accessing the critical section
                    try {
                        s.acquire();
                    } catch (InterruptedException e) {
                           System.out.println(name + "thread Interrupted");
                    }
                    //critical section start

                    num++;
                    System.out.println(name + ": " + i + ", num = " + num);

		    //critical section end
                    
                    //relinquish permission so that other threads can continue
		    try {
                        s.release();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(name + " thread Interrupted");
                    }
		}
		     
		System.out.println(name + " exiting.");
	}
	
	
	public static void main(String[] args) {
            //A semaphore with one permit, is passed through both threads to manage the critical section
            Semaphore s = new Semaphore(1);
  	    new ethanMcCarthySemaphoreProject3("Zero", s);
	    new ethanMcCarthySemaphoreProject3("One", s);
	    
	    try {
	    	Thread.sleep(25000);
	    } catch (InterruptedException e) {
	    	System.out.println("Main thread Interrupted");
	    }

	    System.out.println("Main thread exiting.");
	    System.out.println("Final num = " + num);
	}
}
