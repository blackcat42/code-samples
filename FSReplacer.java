import java.io.*;

/* Code by Patricia M. Brent (patriciambrent@gmail.com | blackcat42 on GitHub)
   Completed for a CodeEval challenge 4/8/2015
   The challenge asked to replace all instances of substrings with other substrings, 
   without replacing text that was the result of a prior replacement  */

public class FSReplacer {

  public static void main(String[] args) {

    try {

      BufferedReader in = new BufferedReader(new FileReader(args[0]));

      String input = in.readLine();

      while(input != null) {

        System.out.println(replaced(input));

        input = in.readLine();

      }

      in.close();


    } catch(IOException e) {

      System.out.println("I/O Exception!");

    }

  }

  public static String replaced(String text) {

    String[] chunk = text.split("[\\W]");

    StringBuilder toCheck = new StringBuilder(chunk[0]);
    StringBuilder toReturn = new StringBuilder(chunk[0]);

    int j = -1;

//Iterate over each chunk to replace

    for (int i = 1; i < chunk.length; i +=2) {

//Check if the chunk to replace is a substring of the main string
//And, if so, begin replacing it

      j = toCheck.toString().indexOf(chunk[i]);

      while(j != -1) {

//Delete each character in the chunk to be replaced

        int k = chunk[i].length();

        toCheck.delete(j,j+k);
        toReturn.delete(j,j+k);

//Insert the replacement string
        toReturn.insert(j,chunk[i+1]);

//Insert a dummy value to keep indexes the same
        for (int m = 0; m < chunk[i+1].length(); m++) {

          toCheck.insert(j,"A");

        }

//System.out.println("toReturn: " + toReturn.toString());
//System.out.println("toCheck: " + toCheck.toString());

        j = toCheck.toString().indexOf(chunk[i]);

      }

    }

    return toReturn.toString();

  }

}