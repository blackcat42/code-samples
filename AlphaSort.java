import java.io.*;

/* Code by Patricia M. Brent (patriciambrent@gmail.com | blackcat42 on GitHub)
   Completed for a CodeEval challenge 03/14/15 [Happy Pi Day!]
   This class generates an alphabetized, comma-delimited list of permutations
   for a given substring, using ASCII character sort order 1Aa.
   This project is my first implementation of QuickSort.
   It became expedient to give several methods access to the same array,
   so I opted to have the class instantiate itself.  */

public class AlphaSort {

  char[] sorted;
  int count;

  public static void main(String[] args) {

    try {

      AlphaSort sorty = new AlphaSort();

      sorty.run(args);

    } catch (Exception e) {

      e.printStackTrace();

    }

  }

  public void run(String[] args) {

    try{

      BufferedReader in = new BufferedReader(new FileReader(args[0]));

      String input = in.readLine();

      while (input != null) {

        input = input.trim();

        sorted = new char[input.length()];

// After alphabetizing, print that string without a comma to avoid formatting issues.

        String sortedStr = (sort(input));

        System.out.print(sortedStr);

// Then print other variations with commas.

        vary(sortedStr,"");        

        System.out.println();

        input = in.readLine();

        count = 0;

      }

      in.close();

      System.exit(0);

    } catch(IOException e) {

      System.out.println("I/O Error!");

    }

  }

  public String sort(String text) {

// Manages sorting of the given characters and converts the array to String

      char[] letters = new char[text.length()];
      for (int i = 0; i < text.length(); i++) {
        letters[i] = text.charAt(i);
      }

      quicksort(letters, 0, letters.length - 1);

      StringBuilder output = new StringBuilder();

      for (int k = 0; k < sorted.length; k++) {

        output.append(sorted[k]);

      }

      return output.toString();

  }

  public void quicksort(char[] A, int lo, int hi) {

// Quickly alphabetizes the list of characters using pivots

    if (lo < hi) {

      char setPivot = A[hi];

      int p = partition(A, lo, hi);

      sorted[p] = setPivot;
      quicksort(A, lo, p-1);
      quicksort(A, p+1, hi);

    } else if (lo == hi) {

      sorted[lo] = A[lo]; 

    }

  }

  public static int partition(char[] B, int lo2, int hi2) {

// Determines the final sorted index for the chosen pivot value

      int pivotIndex = hi2;
      int storeIndex = lo2;
      int pivotVal = (int)B[pivotIndex];

      char tmp = '!';

      for (int j = lo2; j < hi2; j++) {

        if( (int)B[j] < pivotVal ) {

          tmp = B[j];
          B[j] = B[storeIndex];
          B[storeIndex] = tmp;
          storeIndex++;

        }

      }

      tmp = B[hi2];
      B[hi2] = B[storeIndex];
      B[storeIndex] = tmp;

      return storeIndex;

  }

  public void vary(String alpha, String word) {

// Takes an alphabetized String, generates and prints each permutation in alphabetical order

    if (alpha.length() > 0) {

      for (int m = 0; m < alpha.length(); m++) {

        StringBuilder beta = new StringBuilder(alpha);
        StringBuilder chooser = new StringBuilder(word);

        chooser.append(alpha.charAt(m));
        beta.deleteCharAt(m);

        vary(beta.toString(),chooser.toString());

      }

    } else if(count == 0) {

      count++;
      return;

    } else {

      System.out.print(","+word);

    }

    return;

  }

}