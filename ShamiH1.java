import java.io.*;
import java.util.*;

// CSUN COMP 282 Sp 21, Homework-1
// Implementing mergesort and quicksort algorithms
// and printing no. of comparisons and swaps.
// Author: G. Dastghaiby Fard
public class ShamiH1{
	// class variables
	int arr[], barr[], n, k;
    //varibles to count no. of comparisons and swaps
    int ncomps, nswaps;        

    // class constructor
    ShamiH1(){
    int i;
    prt.print("\tThis Java program implements mergesort and quicksort algorithms\n\tand prints no. of comparisons and swaps." +
    		"The program first reads\n\tn, k and next reads n integers and stores" +
    		"them in array barr[].\n\tApplies mergesort and quicksort to the same" +
    		" data and\n\tprint no. of comparisons and swaps after sorting." +   
    		"\n\t\tTo compile: javac ShamiH1.java" +
    		"\n\t\tTo execute: java  ShamiH1 < any data file");

	    try{
			// open input file inf
			Scanner inf = new Scanner(System.in);
			// read from input file          
			n = inf.nextInt();//read n, no. of data
			k = inf.nextInt();//read k, how many per line
			
			// It’s a good practice to print input in program output!
			prt.printf("\n\tn = %3d, k = %4d\n", n,  k);
	
			// Allocate space for arr
			arr  = new int[n];
			barr = new int[n];
			//loop to read n integers
			for (i = 0; i < n ; i ++)
				barr[i] = inf.nextInt();
			// close input file inf
			inf.close(); 
		} catch (Exception e){ prt.printf("\nOoops! Read Exception: %s", e);}
	    	    
    } // end constructor
    

    //use a short word for System.out to save typing
    PrintStream prt = System.out;

    //
    // Method to print formatted array, k elements per line
    private void printarr(){
	int j;
	for (j = 0; j < n ; j ++){
		prt.printf("%4d ", arr[j]);
		if ((j+1) % k == 0)
			prt.printf("\n\t");
		} // end for
    } // end printarr

    //swap arr[j] and arr[k]
	private void swap(int j, int k){
		int tmp = arr[j];
		arr[j]  = arr[k];
		arr[k]= tmp;
	} // end swap
	private void mergesort(int p, int q) {
		int mid;
		if(p<q) {
			mid = (p+q)/2;
			mergesort(p, mid);
			mergesort(mid+1, q);
			merge(p, mid, q);
		}
	}
	private void merge(int p, int mid, int q) {
		//Creating temp arrays
		int left[] = new int[mid - p + 1];
	    int right[] = new int[q - mid];
	    for (int i = 0; i < left.length; i++)
	        left[i] = arr[p + i];
	    for (int i = 0; i < right.length; i++)
	        right[i] = arr[mid + i + 1];
	    int leftIndex = 0;
	    int rightIndex = 0;
	    
	    
	    for (int i = p; i < q + 1; i++) {
	        if (leftIndex < left.length && rightIndex < right.length) {
	            if (left[leftIndex] < right[rightIndex]) {
	               arr[i] = left[leftIndex];
	               leftIndex++;
	            } else {
	                arr[i] = right[rightIndex];
	                rightIndex++;
	                nswaps++;
	            }
	            ncomps++;
	        } else if (leftIndex < left.length) {
	            arr[i] = left[leftIndex];
	            leftIndex++;
	        } else if (rightIndex < right.length) {
	            arr[i] = right[rightIndex];
	            rightIndex++;
	        }
	    }
	}
	
	private void quicksort(int p, int q) {
		int j;
		if(p<q) {
			j = partition(p,q);
			if(p!=j) {
				swap(p,j);
				nswaps++;
			}
			quicksort(p, j-1);
			quicksort(j+1, q);
		}
	}
	private int partition(int p, int q) {
		int pivot, i, j;
		pivot = arr[p];
		j = p;
		for(i = j+1; i<=q; i++) {
			if(arr[i]<= pivot) {
				j++;
				swap(j,i);
				nswaps++;
			}
			ncomps++;
		}
		return j;
	}
	
	// main program
	public static void main(String args[]) throws Exception{
		// Create an instance of ShamiH1  class
		ShamiH1 h = new ShamiH1();

		//copy barr to arr for merge sort,
		h.arr = h.barr.clone();
		System.out.printf("\n\tInput before merge sort\n\t");
		h.printarr();
		//print no. of comparisons and swaps 
		h.ncomps = 0;       h.nswaps = 0;
		h.mergesort(0, h.n-1);
		System.out.printf("\n\tComparisons = %d, Swaps = %d", h.ncomps, h.nswaps);  
		System.out.printf("\n\tInput after merge sort\n\t");
		h.printarr();    
		//copy barr to arr, for quick sort
		h.arr = h.barr.clone();
		System.out.printf("\n\tInput before quick sort\n\t");
		h.printarr(); 
		h.ncomps = 0;       h.nswaps = 0; 
		h.quicksort(0, h.n-1);                     
		System.out.printf("\n\tComparisons = %d, Swaps = %d", h.ncomps, h.nswaps);  
		System.out.printf("\n\tInput after quick sort\n\t");
		h.printarr();    
		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE                   
		System.out.printf("\n\tAuthor: Sami Shami Date: " +
				java.time.LocalDate.now());
	} // end main
} // end class ShamiH1