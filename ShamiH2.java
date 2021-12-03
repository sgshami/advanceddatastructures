
import java.util.*;
import java.io.*;

// CSUN COMP 282 Sp 21, Homework-2
// Implementing external sort:
// For sort phase using normal sort and
// for merge phase using 2way merge.
// Author: G. Dastghaiby Fard
public class ShamiH2 {
	// class used for search
	int heap[], M;// M is largest array size

	// use a short word for System.out to save typing
	PrintStream prt = System.out;

	// print file formatted k integers per line
	private void prtfile(String fn, int k) {
		// declare variables
		int i = 0, x;
		prt.printf("\n\t%s:", fn);
		try {
			Scanner inf = new Scanner(new File(fn));
			while (inf.hasNext()) {
				// read an input from fname
				x = inf.nextInt();
				prt.printf("%3d ", x);
				i++;
				if (i % k == 0)
					prt.printf("\n\t");
			} // endwhile
				// close file
			inf.close();
		} catch (Exception e) {
			prt.printf("\nOoops! Read Exception: %s", e);
		}
	} // end prtfile

	// print n files
	private void prtfiles(int n, int k) {
		int i;
		String fname;
		for (i = 1; i <= n; i++) {
			fname = "F" + i + ".txt";
			prtfile(fname, k);
		}
	}

	private int normalsort(){
		Scanner inf = new Scanner(System.in);  // open inputfile
		M = inf.nextInt();   // read array size M
		heap = new int[M+1];  // allocate array spaceone extra space
		int i = 1;   // set file no.
		int j = 0;
		int k = 0;
		try {
			while(inf.hasNext()){  // while more input 
				// read  from input file
				for(j = 1; j <= M && inf.hasNext(); j++){
					heap[j] = inf.nextInt(); 
					//arr[0] is not used
				}
				k = j - 1;
				heapsort(k);  // apply any internal sorting algorithm
				// create output file name
				String fname = "F" + i;
				// open output file i
				PrintWriter opf = new PrintWriter(new File(fname + ".txt"));
				// store array elements in output Fi.txt
				for (j = 1; j < k; j++){
					opf.printf("%5d  ", heap[j]);  // print formatted
				}  // end for
			    opf.close(); // close outfile i
			    i++;   // start next output file
			}     // end while
			inf.close();   // close input file
		}catch(Exception e){
			System.out.println("something went wrong");
		}
		return i;
	} // end normalsort
		
	// merge n sorted files by calling merge2way one or more times
	private void merge(int n) {
		try {
			while(n>2) {
				File file1 = new File("F" + (n-1) + ".txt");
				File file2 = new File("F" + (n-2) + ".txt");
				File output = new File("F" + n + ".txt");
		
				BufferedReader file1Reader = new BufferedReader(new FileReader(file1));
				BufferedReader file2Reader = new BufferedReader(new FileReader(file2));
				PrintWriter writer = new PrintWriter(output);
		
				String file1Num = file1Reader.readLine();
				String file2Num = file2Reader.readLine();
		
				while (file1Num != null && file2Num != null) {
				    int num1 = Integer.parseInt(file1Num.trim());
				    int num2 = Integer.parseInt(file2Num.trim());
		
				    if (num1 < num2) {
				        writer.println(num1);
				        file1Num = file1Reader.readLine();
		
				    } else if (num2 < num1) {
				        writer.println(num2);
				        file2Num = file2Reader.readLine();
				    } else {
				        writer.println(num1);
				        file1Num = file1Reader.readLine();
				    }
				}
				while (file1Num != null) {
		
				    writer.println(file1Num);
				    file1Num = file1Reader.readLine();
				}
				while (file2Num != null) {
				    writer.println(file2Num);
				    file2Num = file2Reader.readLine();
				}
				file1Reader.close();
				file2Reader.close();
				writer.close();
				n--;
				}
			}catch(Exception e){
				System.out.println("something went wrong");
			}
	} // end merge

	// OPTIONAL merge n sorted files using n-waymerge
	private void mergen(int n) {
		// complete this method
	} // end mergen

	// merge 2 sorted files f1, f2 into f3
	private void merge2way(String f1, String f2, String f3) throws FileNotFoundException {
		Scanner F1 = new Scanner(new File(f1));
		Scanner F2 = new Scanner(new File(f2));
		PrintWriter F3 = new PrintWriter(new File(f3));
		int x = F1.nextInt();
		int y = F2.nextInt();
		while (F1.hasNext() && F2.hasNext()) {
			if (x < y) {
				F3.printf("%3d ", x);
				x = F1.nextInt();
			} else {
				F3.printf("%3d ", y);
				y = F2.nextInt();
			}
		}
		while (F1.hasNext()) {
			F3.printf("%3d ", x);
			x = F1.nextInt();
		}
		F3.printf("%d ", x);
		while (F2.hasNext()) {
			F3.printf("%3d ", y);
			y = F2.nextInt();
		}
		F3.printf("%d ", y);
		F1.close();
		F2.close();
		F3.close();
	} // end merge2way
	

	
/*
	// Heapsort heap[] with n integers	
	private void heapsort(int k) {
		for (int i = M / 2 - 1; i >= 0; i--) {
			heapify(M, i);
		}
		for (int i=M-1; i>=0; i--) {
			int x = heap[0];
			heap[0] = heap[i];
			heap[i] = x;
			heapify(i, 0);
		}
	}
	private void heapify(int M, int i) {
		int largest = i;
		int leftChild = 2*i+1;
		int rightChild = 2*i+2;
		if(leftChild<M && heap[leftChild] > heap[largest])
			largest = leftChild;
		if(rightChild < M && heap[rightChild]> heap[largest])
			largest = rightChild;
		if(largest != i) {
			int temp = heap[i];
			heap [i] = heap[largest];
			heap[largest] = temp;
			heapify(M, i);
		}
	}
	
	*/

	private void heapsort(int n){
		for (int i = n / 2 - 1; i >= 0; i--)
		heapify(heap, n, i);
		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			int temp = heap[0];
			heap[0] = heap[i];
			heap[i] = temp;
			// call max heapify on the reduced heap
			heapify(heap, i, 0);
		}
	}//end sort
	
	void heapify(int heap[], int n, int i){
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2
		// If left child is larger than root
		if (l < n && heap[l] > heap[largest])
			largest = l;
		// If right child is larger than largest so far
		if (r < n && heap[r] > heap[largest])
			largest = r;
		// If largest is not root
		if (largest != i) {
			int swap = heap[i];
		heap[i] = heap[largest];
		heap[largest] = swap;
		// Recursively heapify the affected sub-tree
		heapify(heap, n, largest);
		}
	}
	
	
	public static void main(String[] args) {
		int n, k = 15; // print 15 integers per line
		ShamiH2 srt = new ShamiH2();

		n = srt.normalsort(); // n is no. of sorted files created
		srt.prtfiles(n, k);

		System.out.printf("\n Overall %4d sorted files are created", n);
		// Merging n sorted files by calling merge2way one or more times
		srt.merge(n);

		// OPTIONAL srt.mergen(n); Merging n sorted files using n-way merge
		// OPTIONAL Print execution time of both merges
		// MAKE SURE TO WRITE YOUR NAME IN NEXT LINE
		System.out.printf("\n\tAuthor: Sami Shami Date: " + java.time.LocalDate.now());
	} // end main
} // end ShamiH2