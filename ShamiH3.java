import java.util.*;
import java.io.*;
// CSUN COMP 282 Sp 21, Homework-3
// Implementing external sort:
// For sort phase using replacement selection sort
// and for merge phase using kway merge.
// Author: G. Dastghaiby Fard
public class ShamiH3{
	// class variables
	int heap[][], minheap[], M; // M is largest array size
	
	//use a short word for System.out to save typing
	PrintStream prt = System.out;
	
	//print file formatted k integers per line
	private void prtfile(String fn, int k){
		//declare variables
		if(fn == "F.txt")
			k=M;
		int i=0, x;
		prt.printf("\n\t%s:", fn);
		try{
			Scanner inf = new Scanner(new File(fn));
			while (inf.hasNext()) {
				//read an input from fname
				x = inf.nextInt();
				prt.printf("%3d ", x);
				i++;
				if(i % k == 0) prt.printf("\n\t");
			} // endwhile
			// close file
			inf.close();
		} catch (Exception e){
			prt.printf("\nOoops! Read Exception: %s", e);}
	} // end prtfile
	
	// print n sorted files
	private void prtfiles(int n, int k){
		int i;
		String fname ;
		for (i = 1; i <= n; i++){
			fname = "F" + i +".txt";
			prtfile(fname, k);
		}
	} // end prtfiles
	
	//replacement selection sort, creating files
	// larger than array size M
	
	private int repselsort() throws FileNotFoundException{
        Scanner inf = new Scanner(System.in);
        M = inf.nextInt();
        //System.out.println("There are " + M-1 + " numbers in each array");
        minheap = new int[M+1];
        int j = 0;
        for(j=1; j<=M && inf.hasNext(); j++){
            minheap[j] = inf.nextInt();
            //System.out.println("Hello. Number was added!");
        }
        //System.out.println("Array is length " + minheap.length + " long");
        int heapsize = j-1;
        int i=1;
        while(heapsize > 0) {
            String fname = "F" + i + ".txt";
            PrintWriter opf = new PrintWriter(new File(fname));
            for(j = heapsize/2; j>0; j--) 
                minheapdn(j, heapsize);
            int lastwritten = Integer.MIN_VALUE;
            int inheap = 0;
            while(heapsize>0) {
                if(minheap[1]<lastwritten) {
                    swap(1, heapsize); //minheap[1], minheap[heapsize]
                    heapsize--;
                    inheap++;
                }else {
                    lastwritten = minheap[1];
                    opf.printf("%d ", minheap[1]);
                    if(!inf.hasNext()) {
                       for(j = 1; j < heapsize+inheap; j++) {
                           minheap[j] = minheap[j+1];
                       }
                       heapsize--;
                   }else {
                        minheap[1] = inf.nextInt();
                   }
                }
                minheapdn(1, heapsize);
            }
            opf.close();
            if(inheap == 0)
                break;
            heapsize = inheap;
            i++;
        }
        inf.close();
        return i;
    } // end repselsort
	// k-way merge n sorted files
	// F1.txt, F2.txt, ..., Fn.txt into F.txt
	
	private void kwaymerge(int k) throws FileNotFoundException{
		Scanner[] inf =new Scanner[k+1];
		heap = new int[k+1][2];		
		for(int j=1; j <= k; j++) {
			inf[j] = new Scanner(new File("F" + j +".txt"));
			//opf = new PrintWriter(new File("F" + j + ".txt"));			
		}		
		for(int j = 1; j <= k; j++){
			heap[j][0] = inf[j].nextInt();
			heap[j][1] = j; // store file number
		}
		PrintWriter opf = new PrintWriter(new File("F" + ".txt"));
		int heapsize = k;
		for(int j = heapsize/2; j > 0; j--) {
			minheapdn(j, heapsize);
		}
		while(heapsize > 0){
			opf.printf("%5d ", heap[1][0]);
			int j = heap[1][1];
			if(!inf[j].hasNext()){
				heap[1] = heap[heapsize];
				heapsize--;
			}else{
				heap[1][0] = inf[j].nextInt();
				heap[1][1]= j;
			}
			minheapdn(1, k);
		}
		for(int j = 1; j <= k; j++ ) {
			inf[j].close();
		}
		opf.close();
	} // end kwaymerge

	/*
	private void heapsort(int m, int n) {
		for(int i = n/2; i >= m+1; i--) { 
			System.out.println("hello");
				//ORIGINAL: m was 1
			int j = i; //root
			while(j*2 <= n) {
				int k = 2*j; //left child
				int minval = heap[k][0];
				if(k+1 <= n && minheap[k+1] < minval) {
					minval = heap[k+1][0];
					k += 1;
				}
				if(heap[k][0] < heap[j][0]) {
					
					//swap method below
					int temp[] = heap[k];
					heap[j]  = heap[k];
					heap[k]= temp;
					
					
					j = k;
				}else {
					break;
				}
			}
		}
	}
*/
	private void minheapdn(int m, int n) {
		for(int i = n/2; i >= m; i--) { 			//ORIGINAL: m was 1
			int j = i; //root
			while(j*2 <= n) {
				int k = 2*j; //left child
				int minval = minheap[k];
				if(k+1 <= n && minheap[k+1] < minval) {
					minval = minheap[k+1];
					k += 1;
				}
				if(minheap[k] < minheap[j]) {
					swap(k, j);
					j = k;
				}else {
					break;
				}
			}
		}
	}
	
	/*
	//Heapsort heap[][] with n integers
	private void heapsort(int n){
		for(int j=n/2; j>=1; j--) {
			minheapdn(j,n);
		}
		for(int j = n/2; j>= 1; j--) {
			swap(heap[1], heap[j]);
			minheapdn(1, j-1);
		}
	} // end sort
	*/
	
	
	private void swap(int j, int k){
		int tmp = minheap[j];
		minheap[j]  = minheap[k];
		minheap[k]= tmp;
	}
	
	public static void main(String[] args) throws Exception{
		int n, k = 20;//print k data per line
		ShamiH3 srt = new ShamiH3();
		
		//apply replacement selection sort,
		n = srt.repselsort();
		
		//print n sorted files
		srt.prtfiles(n, k);
		System.out.printf("\n Overall %4d files are created", n);
		
		//apply kwaymerge to merge n sorted files
		//F1.txt, F2.txt, ..., Fn.txt into F.txt
		srt.kwaymerge(n);
		
		//print final sorted file
		srt.prtfile("F.txt", k);
		
		//MAKE SURE TO WRITE YOUR NAME IN NEXT LINE
		System.out.printf("\n\tAuthor: Sami Shami          Date: " + java.time.LocalDate.now());
	}// end main
} // end ShamiH3