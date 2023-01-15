import java.util.*;

class WordSearch
{
  char [][] matrix;
  int r = 0;
  int c = 0;

  //Constructor (gets called when you do new WordSearch)
  public WordSearch(int m, int n)
  {
    matrix = new char [m][n]; 
  }

  public void add(char a)
  {
    matrix [r][c] = a;
    c++;
    if(c == matrix[0].length)
    {
      c = 0;
      r++;
    }
  }

  
  //initiating search at every postion for the given word
  public String lookingFor(String word)
  {
    Stack<Integer> found = new Stack<>();
    for(int row = 0; row < matrix.length; row++)
    {
      for(int col = 0; col < matrix[0].length; col++)
      {
        //start search at each position in the matrix
        lookingFor(found, new boolean[this.matrix.length][this.matrix[0].length], row, col, word);
        if(found.size() / 2 == word.length())
        {
          return toString(found);
        }
        found = new Stack<>();
      }
    }
    return word + " not found!";
  }

  /*
    Takes the postions in a stack , cordinates we visited, row , col, and the word as a string. It then determines what letter of the word we are looking for and if the letter is found adds those cordinates to the position stack. Iterator created to show what path is prioritized and we check to see if those cordinates are in the matrix. If the letter we are looking for is not found then we backtrack and remove the cordinates from the stack. If the word is found the positions stack then contains the postion of every letter in the word.
  */
  public void lookingFor(Stack<Integer> positions, boolean[][] visited, int r, int c, String w)
  {
    
    if(r != matrix.length && r != -1 && c != matrix[0].length && c != -1 && !visited[r][c])
    {
      int numLettersFoundAlready = positions.size()/2;
      if(matrix[r][c] == w.charAt(numLettersFoundAlready))
      {
        positions.add(r);
        positions.add(c);
        visited[r][c] = true;


        if(positions.size()/2 == w.length())
        {
          return;
        }
        
        //search around position r,c
        Iterator<Integer> offsets = Arrays.asList(
          -1, -1, //upper left
          0, -1, //left
          1, -1, //lower left
          0, 1, //right
          1, 0, //down
          1, 1, //lower right
          -1, 1, //upper right
          -1, 0 //up
        ).iterator();
        while(offsets.hasNext())
        {
          int nextRow = r + offsets.next();
          int nextCol = c + offsets.next();
          lookingFor(positions, visited, nextRow, nextCol, w);
          if(positions.size()/2 == w.length())
          {
            return;
          }
        }
        //we've searched all around, found nothin
        positions.pop();
        positions.pop();
        visited[r][c] = false;
      }
    }
  }
  //Converts matrix into a string but with only the saved positions that contain the letters of the word.
  private String toString(Collection<Integer> positions)
  {
    char[][] matrixCopy = new char[matrix.length][matrix[0].length];
    for(int i = 0; i < matrix.length ; i++)
    {
      for(int j = 0; j < matrix[0].length; j++)
      {
        matrixCopy[i][j] = ' ' ;
      }
    }

    //Fill in given positions
    Iterator<Integer> positionsIterator = positions.iterator();
    while(positionsIterator.hasNext())
    {
      int r = positionsIterator.next();
      int c = positionsIterator.next();
      matrixCopy[r][c] = matrix[r][c];
    }

    StringBuilder str = new StringBuilder();
    for(char[] row : matrixCopy)
    {
      str.append(Arrays.toString(row));
      str.append("\n");
    }
    return str.toString();
  }
      
}