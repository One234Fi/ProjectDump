/******************************************************************
 
   Name:                   Ethan McCarthy
   Course/Section:       COSC 4315.001
   Instructor:           Dr. Brown
   Program Description:  Main method instantiates an IR system and calls its start method
 
   This program uses the following approach for each issue
   Case: All words are converted to lowercase.
   Punctuation: All punctuation marks are replaced with an empty string using regex.
   Stop Words: 
   Stemming: "s" and "ing" are removed from the end of any words that have them using substring.
 ******************************************************************/
public class Assignment1
{
  public static void main (String args[]) throws Exception
  {
    IRSystem ir = new IRSystem();
    ir.start();
  }
}