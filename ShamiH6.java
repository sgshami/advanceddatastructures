import java.io.*;
import java.util.*;

public class ShamiH6 {
	int nwords, seq;
	node root = null;

	// class node
	private class node {
		String wrd;
		int freq;
		node ll, rl;

		node(String x) {
			wrd = x;
			freq = 1;
			rl = ll = null;
		}
	}// end class node

	// use a short word for prt to save typing
	PrintStream prt = System.out;

	// inorder traversal that prints frequency and probability of each word
	public void inorder() {
		prt.printf("\n\tNo. of Words: %d ", nwords);
		prt.print("\n\tNo.\tWord\tFreq\tProb");
		prt.print("\n\t======================================" + "\n");
		seq = 1;
		inorder(root);
	} // end inorder()

	// Inorder Traversal from (node t)
	private void inorder(node t) {
		if (t == null)
			return;
		node temp = t;
		inorder(temp.ll);
		double prob = (temp.freq / Double.parseDouble((nwords) + ".0"));
		prt.print(seq++ + " - " + temp.wrd + "  " + temp.freq + "  ");
		prt.printf("%.4f", prob);
		prt.println();
		// System.out.printf("%.3f", prob);
		inorder(temp.rl);
		/*
		 * for (int i = 1; i <= nwords; i++) { prt.printf(i + "- " + temp.wrd + "  " +
		 * temp.freq + "  " + (temp.freq / nwords) + " \n"); }
		 */
	} // end inorder(node t)

	// insert x into a BST
	public void insert(String x) {
		if (root == null) // Allocate space to store x
			root = new node(x);
		else
			insert(x, root);
	} // end insert (String x)

	// insert x into a BST with root t(non-recursive)
	public void insert(String x, node t) {
		node new1 = new node(x); // Node to be added
		node pointer1 = t; // pointer for traverse tree
		node pointer2 = null; // Previous pointer
		while (pointer1 != null) {
			pointer2 = pointer1;
			if (x.equals(pointer1.wrd)) {
				pointer1.freq = pointer1.freq + 1;
				// System.out.println("Same: " + pointer2.wrd + " -- " + pointer2.freq);
				return;
			} else if (x.compareTo(pointer1.wrd) < 0)
				pointer1 = pointer1.ll;
			else
				pointer1 = pointer1.rl;
		}
		if (pointer2 == null) {
			pointer2 = new1;
		} else if (x.compareTo(pointer2.wrd) > 0) {
			pointer2.rl = new1;
		} else {
			pointer2.ll = new1;
		}
		/*
		 * if(x.compareTo(pointer2.wrd) < 0) { pointer2.ll = new1; }else
		 * if(x.compareTo(pointer2.wrd) > 0){ pointer2.rl = new1; }else { pointer2.freq
		 * = pointer2.freq++; System.out.println("Same"); }
		 */
	} // end insert (String x, node t)

	private void process() { // read input
		int j, k, n, x;
		String wrd;
		prt.printf("\n\tTEXT MINING: Computing frequency of words in a document           "
				+ "\n\talong with their probabilties.");
		try {
			Scanner inf = new Scanner(System.in);
			nwords = 0; // initialize no. of words
			while (inf.hasNext()) {
				wrd = inf.next(); // read next idno
				insert(wrd);
				nwords++;
			} // end while
				// print inorder traversal of bst with freq and probability
			inorder();
			inf.close();
		} catch (Exception e) {
			prt.printf("\nI/O Error %d", e);
		}
	} // end process method

	public static void main(String args[]) throws Exception {
		// create an instance of ShamiH6
		ShamiH6 t = new ShamiH6();
		// process to insert words in a BST and printing inorder along
		// with frequency and probability
		t.process();
		// MAKE SURE TO WRITE YOUR NAME IN NEXT LINE
		System.out.printf("\n\tAuthor: Sami Shami Date: " + java.time.LocalDate.now());
	} // end main
}// end class xxxxxH6