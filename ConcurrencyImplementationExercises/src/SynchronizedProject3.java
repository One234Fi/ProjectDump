/*
Ethan McCarthy
Class: COSC 3355.001
Professor: Nary Subramanian
Date: 4/11/2022
Description: The provided "MyThread.java" class modified to use a monitor to handle concurrency
*/

public class ethanMcCarthySynchronizedProject3 implements Runnable {
	private String name;
	private Thread myThread;
	
	ethanMcCarthySynchronizedProject3(String threadName) {
	    name = threadName; 
	    myThread = new Thread(this, name);
	
	    System.out.println("New thread: " + myThread);
	    myThread.start();
	}

	@Override
	public void run() {
		for(int i = 1; i <= 20; i++) {
		    //critical section start
                    
                    //pass thread name and loop-iteration into the monitor to run the critical portion of the program
                    ethanMcCarthySynchronizedNumberProject3.runCritSect(name, i);

		    //critical section end

		    try 
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println(name + " thread Interrupted");
                    }
		}
		     
		System.out.println(name + " exiting.");
	}
	
	public static void main(String[] args) {
            //instantiate sub threads
  	    new ethanMcCarthySynchronizedProject3("Zero");
	    new ethanMcCarthySynchronizedProject3("One");
	    
	    try {
	    	Thread.sleep(25000);
	    } catch (InterruptedException e) {
	    	System.out.println("Main thread Interrupted");
	    }

	    System.out.println("Main thread exiting.");
	    System.out.println("Final num = " + ethanMcCarthySynchronizedNumberProject3.getNum());
	}
}
