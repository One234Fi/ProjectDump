/**
 * Ethan McCarthy
 * 3/16/2022
 * COSC 3355
 * Nary Subramanian
 * Purpose: Creates a shell for the windows operating system that 
 * provides functionality for some Linux commands
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class ethanMcCarthyProject2 {
    
    static boolean exit = false;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String command = "";
        do {
            System.out.print(">>>");
            command = sc.nextLine();
            parseCommand(command);
        } while (!exit);
    }
    
    static void prompt() {
        String s = "MyShell, version 2.0, runs on Windows10, developed by Ethan McCarthy\n"
                + "Release date March 16, 2022 \n"
                + "These shell commands are defined internally. Type \'help\' to see this list.\n"
                + "Command and its parameter, if any, should be separated by one space only.\n"
                + "Type \'help name\' to find more about the command \'name\'.";
        System.out.println(s);
    }
    
    /**
     * Determines which command to execute and calls it
     * @param s command keyword given by the user
     */
    static void parseCommand(String s) {
        String temp = s.contains(" ") 
                ? s.substring(s.indexOf(" ")+1)
                : "";
        
        String cmd = (temp.equals("")) 
                ? s.toLowerCase()
                : s.substring(0, s.indexOf(" ")).toLowerCase();
        
        switch (cmd) {
            case "ls": ls(); break;
            case "pwd": pwd(); break;
            case "clear": clear(); break;
            case "date": date(); break;
            case "exit": exit(); break;
            case "help": help(temp); break;
            case "whoami": whoAmI(); break;
            default: unknownCommand();
        }
    }
    
    /**
     * Lists the contents of the current directory
     */
    static void ls() {
        try {
            Process p = Runtime.getRuntime().exec("cmd /c dir");
            BufferedReader dirOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            while ((s = dirOutput.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("Exception @ ls!!");
        }
    }
    
    /**
     * Shows the absolute path for the current directory
     */
    static void pwd() {
        try {
            Process p = Runtime.getRuntime().exec("cmd /c chdir");
            BufferedReader dirOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            while((s = dirOutput.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("Exception @ pwd!!");
        }
    }
    
    /**
     * Clears the console
     */
    static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Exception @ clear!!");
        }
    }
    
    /**
     * Displays day, date, time, and time zone in the format:
     * Fri Mar 12 15:30:56 CST 2021
     */
    static void date() {
        try {
            String s = "", year = "";
            Process p = Runtime.getRuntime().exec("cmd /c date");
            BufferedReader dirOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String temp = dirOutput.readLine();
            if (temp != null) {
                temp = temp.replace("The current date is: ","");
                s += temp.substring(0, temp.indexOf(" ")) + " ";
                
                switch(temp.substring(temp.indexOf(" ") + 1, temp.indexOf(" ") + 3)) {
                    case "01": s += "Jan "; break;
                    case "02": s += "Feb "; break;
                    case "03": s += "Mar "; break;
                    case "04": s += "Apr "; break;
                    case "05": s += "May "; break;
                    case "06": s += "Jun "; break;
                    case "07": s += "Jul "; break;
                    case "08": s += "Aug "; break;
                    case "09": s += "Sep "; break;
                    case "10": s += "Oct "; break;
                    case "11": s += "Nov "; break;
                    case "12": s += "Dec "; break;
                    default: s += "NA ";
                }
                
                s += temp.substring(temp.indexOf("/")+1, temp.lastIndexOf("/")) + " ";
                year = temp.substring(temp.lastIndexOf("/") + 1);
            }
            p = Runtime.getRuntime().exec("cmd /c time");
            dirOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            temp = dirOutput.readLine();
            if (temp != null) {
                temp = temp.replace("The current time is: ", "");
                s += temp.substring(0, temp.indexOf(".")) + " ";
            }
            p = Runtime.getRuntime().exec("cmd /c tzutil /g");
            dirOutput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            temp = dirOutput.readLine();
            if (temp != null) {
                char[] timeZone = temp.toCharArray();
                String caps = "";
                for (char c : timeZone) {
                    if (Character.isUpperCase(c)) {
                        caps += c;
                    }
                }
                s += caps + " ";
                s += year;
            }
            System.out.println(s);
            
        } catch (IOException ex) {
            System.out.println("Exception @ date!!");
        }
    }
    
    /**
     * Close the console (exit the program)
     */
    static void exit() {
        exit = true;
    }
    
    //list of individual commands and their 'help' information
    static String [] helpLines = 
        {"ls\t\t\t\tlist contents of current directory",
        "pwd\t\t\t\tdisplays the current directory",
        "clear\t\t\t\tclears the console",
        "date\t\t\t\tdisplays the current day, date, time, and time zone",
        "help [cmd]\t\t\tget help for command cmd (\'cmd\' is optional);",
        "\t\t\t\twithout cmd displays this list",
        "exit\t\t\t\tquit console",
        "whoami\t\t\t\tdisplays name of programmer"};
    
    /**
     * Display the list of permissible commands
     */
    static String help() {
        prompt();
        String s = "command\t\t\t\tfunction\n"
                + "=======\t\t\t\t========\n";
        
        for (int i = 0; i < helpLines.length; i++) {
            s += helpLines[i] + "\n";
        }
        System.out.println(s);
        return s;
    }
    
    /**
     * Displays help for the specific 'cmd'
     * @param cmd 
     */
    static void help(String cmd) {
        String param = cmd.toLowerCase();
        switch (param) {
            case "": help(); break;
            case "ls": 
                System.out.println(helpLines[0]); break;
            case "pwd": 
                System.out.println(helpLines[1]); break;
            case "clear": 
                System.out.println(helpLines[2]); break;
            case "date":
                System.out.println(helpLines[3]); break;
            case "help": 
                System.out.println(helpLines[4]); break;
            case "exit": 
                System.out.println(helpLines[6]); break;
            case "whoami": 
                System.out.println(helpLines[7]);break;
            default: unknownCommand();
        }
    }
    
    /**
     * Displays current user (my/programmer's name)
     */
    static void whoAmI() {
        System.out.println("Ethan McCarthy");
    }
    
    /**
     * Informs the user of an invalid command
     */
    static void unknownCommand() {
        System.out.println("Unknown command!");
    }
}