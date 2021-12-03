import java.io.*;
import java.util.*;
// CSUN COMP 282 Sp 21, Homework-5
// Matrix chain multiplication Recursive and Dynamic Programming
// Author: G. Dastghaiby Fard

public class ShamiH5{
   int n, d[];
   int[][] MCM, fac;

   //use a short word for prt to save typing
   PrintStream prt = System.out;

   //recursive computation of Matrix chain multiplication 
   public int recMCM(int i, int j) { //j >= i > 0
      //Complete this partd
      if(i==j)
         return 0;
      int min = Integer.MAX_VALUE;
      int m;
      for(int k=i; k<j; k++){
        m = recMCM(i, k) + recMCM(k+1, j) + d[i-1]*d[k]*d[j];
        fac[i][j] = k;
        if(m < min){
            min = m;
        }
      }//end for
      return min;
   } // end recMCM(i, j)

   //Dynamic Programming computation of Matrix chain multiplication  
   public int MCM() {
      int i, j, k, diag, bestk;
      for (j = 1; j < n; j++) {
          MCM[j][j] = 0;
          fac[j][j] = 0;
      }
      for (diag = 1; diag <= n; diag++){
          for (i = 1; i < n - diag + 1; i++){
              j = i + diag;
              MCM[i][j] = Integer.MAX_VALUE;
              for (k = i; k < j ; k++){
                  bestk = MCM[i][k] + MCM[k + 1][j] + d[i - 1] * d[k] * d[j];
                  if (bestk < MCM[i][j]){
                      MCM[i][j] = bestk;
                      fac[i][j] = k;
                  }
              }
          }
      }
      return MCM[1][n];
   } // end MCM

   // Show order of Matrices to Multiply 

   public void showorder(int i, int j) {
      int k;
      if(i == j)
    	  System.out.printf("A%d", i);
      else {
    	  k = fac[i][j];
    	  System.out.print("(");
    	  showorder(i,k);
    	  System.out.print("*");
    	  showorder(k+1, j);
    	  System.out.print(")");
      }
   } // end showorder

   // process to read input for matrix chain multiplication and computing min. no.
   // multiplication and their order, using both Recursive and Dynamic Programming
   private void process() {
      int j, min;
      prt.printf("\n\tMarix chain multiplication: Recursive and Dynamic Programming.");
      try{ 
         Scanner inf = new Scanner(System.in);
         n = inf.nextInt(); //read no. of matrices
         d = new int[n+1];
         prt.printf("\n\tNo. of Matrices:%d, Dim:",n);
         for(j = 0; j <= n; j++)
         {
            d[j]= inf.nextInt(); //read dimensions
            prt.printf("%2d ", d[j]);
         } // end for
         inf.close();
      }
      catch(Exception e){
         prt.printf("\nI/O Error %d", e);
      }
      //CALLING RECURSIVE APPROACH
      long starttime1 = System.nanoTime();
      MCM = new int[n+1][n+1];
      fac = new int[n+1][n+1];
      min = recMCM(1, n);
      prt.printf("\nMin MPY = %d", min);
      //prtMCM();
      System.out.printf("\nOrder of Matrices to Multiply is as follows:\n");
      showorder(1, n);
      long endtime1 = System.nanoTime();
      long runtime1 = endtime1 - starttime1;
      runtime1 = runtime1 / 1000000;
      //For When n>30, remove //
      //if(n > 30){
         System.out.println();
         System.out.println(runtime1 + " is the runtime of the recursive approach in milliseconds");
      //}

      //CALLING DYNAMIC PROGRAMMING APPROACH
      long starttime2 = System.nanoTime();
      min = MCM();
      prt.printf("\nMin MPY = %d", min);
      System.out.printf("\nOrder of Matrices to Multiply is as follows:\n");
      showorder(1, n);
      //print exection time for n > 30 
      long endtime2 = System.nanoTime();
      long runtime2 = endtime2 - starttime2;
      runtime2 = runtime2 / 1000000;
      
      //For When n>30, remove //
      //if(n > 30){
         System.out.println();
         System.out.println(runtime2 + " is the runtime of the dynamic approach in milliseconds");
      //}
   } // end process method

   public static void main(String[] args) throws Exception {

      // create an instance of navarH5                  

      ShamiH5 p = new ShamiH5();

      // process to read input for matrix chain multiplication and computing min. no.

      // multiplication and their order, using both Recursive and Dynamic Programming

      p.process();

      //MAKE SURE TO WRITE YOUR NAME IN NEXT LINE                   

      System.out.printf("\n\tAuthor: Sami Shami Date: " + java.time.LocalDate.now());

   } // end main

} // end class ShamiH5