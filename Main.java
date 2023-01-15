import java.util.*;
import java.io.*;

/*
  This program is written by: Christian Samson
*/

class Main {
  
  //Reads text file and prints out the search results for each test case
  public static void main(String[] args) {
   File inputFile = new File("in.txt");
   
     try(Scanner scnr = new Scanner(inputFile))
     {
       int numTestcases = scnr.nextInt();
       for(int i = 0; i < numTestcases ; i++)
       {
         System.out.println("Test#"+(i + 1));
         int height = scnr.nextInt();
         int width = scnr.nextInt();
         int numWords = scnr.nextInt();
         WordSearch wordsearch = readFile(height, width, scnr);
         for(int j = 0; j < numWords; j++)
         {
           String word = scnr.nextLine();
           String value = wordsearch.lookingFor(word);
           System.out.println("Looking for "+word);
           System.out.println(value);
           if(value.contains("not found"))
           {
             System.out.println("");
           }
         }

       }
      
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
  }
  //Creates a WordSearch object which stores letters into the matrix 
  public static WordSearch readFile(int m, int n, Scanner file)
  {
    WordSearch wordSearch = new WordSearch(m, n);
    for(int i = 0; i < m; i++)
    {
      for(int j = 0; j < n ; j++)
      {
        wordSearch.add(file.next().charAt(0));
      }
      file.nextLine();
    }
    return wordSearch;
  }
}