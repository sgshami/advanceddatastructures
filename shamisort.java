import java.io.*;
import java.util.*;
 // CSUN COMP 282  Spring 21 shamisort.java
 // Author: G. Dastghaiby Fard
 // Java program to sort n intergers
 // Goal: Learning to:
 //    reading input from anyinputfile  
 //    anyinputfile should be created using any IDE
 //    an input file using any IDE 
 //    Stores input numbers in arr[]
 //    Prints arr[] formatted k numbers per line
 //    before and after bubble sort
 //    Compile and execute at the command prompt
  public class shamisort{
	// class variables
	private int n, a, b, k, arr[];
	
	//use a short word for System.out to save typing
	PrintStream prt = System.out;
	
	// class constructor
	shamisort(){
	  int j;
	  try{
		// open file inf
		Scanner inf = new Scanner(System.in); 
		prt.print("\tJava program to sort n > 0 integers using bubble sort. \n\tReads:" +
		"\n\t  integer n (n > 0) no. of input" +
		"\n\t  integer k (n > 0) to print k lines per line" +
		"\n\t  and n integers " +
		"from any input file, " + 
		"stores them in arr[]. " +
		"\n\tPrints arr[] formatted k numbers per line, " +
		"before and after bubble sort."+
		"\n\t\tTo compile: javac shamisort.java " +
		"\n\t\tTo execute: java  shamisort < anyinputfile");
		
		n = inf.nextInt();// read n, no. of data
		k = inf.nextInt();// read k, how many per line
	 
		// It’s a good practice to print input in program output!
		prt.printf("\n\tINPUT: n = %3d, k = %4d", n,  k);
		
		// Allocate space for array
		arr = new int[n];
		
		//Read n integers ans store in array		
		for (j = 0; j < n; j++)
		    //Read jth data		
			arr[j] = inf.nextInt();	
			
		// close file inf
		inf.close();  
	  } catch (Exception e){
		prt.printf("\nOops! Read Exception: %s" , e);
	  }
	} // end constructor

	//Print arr[] formatted, k elements per line
	private void printarr1(){ 
		//declare variables
		int j;		
		for (j = 0; j < n ; j ++){
		  prt.printf("%4d ", arr[j]);
		  if ((j+1) % k == 0) 
			  prt.printf("\n\t");
		} // end for
	} // end printarr1
  
	// print arr, k numbers per line
	private void printarr2(){
		//declare variables
		int j = 0;
		for (int i : arr){
			prt.printf("%4d ", i);
			j++;
			if (j % k == 0) prt.printf("\n\t");
		}
	} // end printarr2

	// sort arr[] using bubble sort
	private void bubblesort(){
		int i, j;
		for (i = 1; i < n; i++)
		  for (j = 0; j < n-i; j++)
			if(arr[j] > arr[j+1])
				swap(j, j+1);	
	} // end bubble sort
	private void swap(int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	} // end swap

	public static void main(String[] args) throws Exception {
		//create an instance of a class
		 shamisort srt = new shamisort();// private variables
		 
		 System.out.printf("\n\tInput before sorting:\n\t");
		 //print arr[]
		 srt.printarr1();
		 
		 // perform bubblesorsort 
		 srt.bubblesort();
		 
		 System.out.printf("\n\tInput after sorting:\n\t");
		 //print arr[]
		 srt.printarr2();

// MAKE SURE TO WRITE YOUR NAME IN LINE 111		
		System.out.printf("\n\tAuthor: samishami Date: " +
		java.time.LocalDate.now()); 
	 } // end main
 }  //end shamisort