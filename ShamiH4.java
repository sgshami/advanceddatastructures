import java.util.*;
import java.io.*;

public class ShamiH4 { // btree degree m
    // class variable
    int deg; // degree of Btree
    node root = null; // root of the btree
    // use a short word for prt to save typing
    PrintStream prt = System.out;

    // class node
    private class node {
        int count; // current no. of data in the node
        int[] id;
        String[] name;
        node[] link;
        node parent;
        // node Constructor

        node(int myid, String myname, int degree) {
            count = 1; // Allocate k spaces for id, name and link arrays
            id = new int[degree];
            name = new String[degree];
            link = new node[degree];
            // NOTE: id[0] & name[0] are not used
            id[1] = myid;
            name[1] = myname;
        } // end node Constructor
    } // end class node
    // class used for search

    private class SearchResult { //Failure
        node leaf;
        boolean found;

        // SearchResult Constructor
        SearchResult(node t, boolean k) {
            leaf = t;
            found = k;
            if(found){
            	//System.out.println("We FOUND IT");
            } else {
               //System.out.println("We DIDN'T find it");
            }
        } // endSearchResult Constructor
    } // end class SearchResult

    // SEARCH FOR myid in BTree
    private SearchResult search(int myid) {
        return search(myid, root);
    } // end search

   // Search for myid in BTree with node t
   private SearchResult search(int myid, node t) {
      int k = 0;
      if (t == null)  // tree in empty
         return new SearchResult(null, false);
      // end if t == null

      // t is not null, so search current node for x and proper link
      node cur = t;
      while(t != null)
      {
         cur = t; // save t
         k = t.count;
         // k contains current no. of data in node t
         // check if xis in current node t
         int i;
         for(i = 1; i <= k; i++)
            if (myid == t.id[i])  // x is found
               return new SearchResult(t, true);
            // end if
         // end for

         // x does not exist in node t, next check proper link of to search
         if(myid < t.id[1]) 
            t = t.link[0];
         else 
         { 
            // find proper link from right to search for x i.e. from largest data
            while(myid < t.id[k]) // similar to insertion sort
               k--;
            // end while
            t = t.link[k];
         }  // end else
      }  // end while(n != null)
      // x is not found, so x should be inserted in
      // leaf node pointed by cur
      return new SearchResult(cur, false);
   } // end search method



    // Inorder Traversal of BTree starting from root
    private void inorder() {
        prt.printf("\n\tInorder: "); // contents of the BTree
        inorder(root);
        prt.printf("\n");
    } // end inorder method

    // Inorder Traversal, BTree starting from node t
    private void inorder(node t) {
        if (t == null)
            return;
        inorder(t.link[0]);
        for (int i = 1; i <= t.count; i++) {
            prt.printf(" %d,  %s; ", t.id[i], t.name[i]);
            inorder(t.link[i]);
        } // end for
    } // end inorder method

    // insert myid & myname in BTree
    // INSERT WITH TWO PARAMTETS CHECKS IF THERE IS EELEMTS YET, IF THERE AREN'T THEN IT WILL 
    // MAKE THE FIRST ELEMENT THE ROOT, IF THERE IS ALREADY AN ELEMENT, ITS GOING TO CHECK IF
    // IT IS A DUPLICATE, IF NOT, THEN IT WILL CONTINUE TO INSERT.
    private void insert(int myid, String myname) {
        prt.printf("\nInsert %d, %s", myid, myname);
        if (root == null) { // insert x into empty BTree
            root = new node(myid, myname, deg);
            return;
        } // end if root == null
        SearchResult srch = search(myid);  // First search for myid
        if (srch.found == false){
            insert(myid, myname, srch.leaf);  // insert, if myid is not in btree
            }
        else
            prt.printf(" Ooops..Duplicate...");
    } // end insert method
    // insert myid and myname and pointer T2=null in leaf node t

    
    private void insert(int myid, String myname, node t) { // (150 Pts)
        int n = deg - 1;
        node T2 = null; // T2 is all the data right of the middle
        
        
        for(T2 = null; ;) {
            if(t.count < n) {
            	//int j = t.count+1;
            	t.id[t.count+1] = myid;
                t.name[t.count+1] = myname;
                t.link[t.count+1] = T2;
                t.count++;
                //j++;
                
            	/*
                t.id[j] = myid;
                t.name[j] = myname;
                t.link[j] = T2;
                t.count++;
                j++;
                */    
                
                int i, k, tmp;
                String stmp;
            	for(k = 1; k<t.count+1; k++){
            		tmp = t.id[k];
            		stmp = t.name[k];
            		i = k-1;
            		while(i >= 0 && t.id[i] > tmp) {
            			t.id[i+1] = t.id[i];
            			t.name[i+1] = t.name[i];
            			i--;
            		}
            		t.id[i+1] = tmp;
            		t.name[i+1] = stmp;
            	}
            	if(T2 != null) {
            		T2.parent = t.parent;
            		//return;
            	}
            	return;
            }

            node tmp = new node(myid, myname, deg+1);
            tmp.link[0] = t.link[0];
            for(int j=0; j <= tmp.count; j++) {		//added +1 in for loop
            	tmp.id[j] = t.id[j];
            	tmp.name[j] = t.name[j];
            	tmp.link[j] = t.link[j];
            }
            //insert myid and T2
            tmp.id[tmp.count+1] = myid;
            tmp.name[tmp.count+1] = myname;
            tmp.link[tmp.count+1] = T2;
            //tmp.count++;
            int i, k, temp;
            String stmp2;
        	for(k = 1; k<tmp.count+1; k++){
        		temp = tmp.id[k];
        		stmp2 = tmp.name[k];
        		i = k-1;
        		while(i >= 0 && t.id[i] > temp) {
        			tmp.id[i+1] = tmp.id[i];
        			tmp.name[i+1] = tmp.name[i];
        			i--;
        		}
        		tmp.id[i+1] = temp;
        		tmp.name[i+1] = stmp2;
        	}  

            
            int x = tmp.id[tmp.count/2+1];		//middle number; Possible division problem. Add 1 to offset integer division
            String m = tmp.name[tmp.count/2+1];
            T2 = new node(x, m, deg);
            for(int h = 0; h < deg; h++) {
             		T2.id[h] = myid;
                    T2.name[h] = myname;
                    T2.link[h] = T2;
                    return;
            	
            	
                //tmp.count++;
            }

            
            if(t==root) {
            	node T1 = new node(myid, myname, deg);
            	T1.link[0] = t;
            	T1.link[1] = T2;
            	root = T1;
            	T2.parent = T1;
            	t.parent = T1;
            	return;
            }
            //send my id and T2 to parent of t
            t = t.parent;
        }
    } // end insert method

    private int height(){
        if (root == null) return 0;
        int height = 0;
        node t = root;
        while(t.link[0] != null){
            t = t.link[0];
            height++;
        }
        return height;
    } // end height

    // process to insert in a btree, print btree, its height and query the btree
    private void process() {
        int j, n, myid, nsrch;
        String myname = "";
        try {
            Scanner inf = new Scanner(System.in);
            deg = inf.nextInt();    //read degree of btree
            n = inf.nextInt();    //read no. of data
            deg = deg * deg * n;
            // read input and create BTree
            for (j = 1; j <= n; j++) {
                myid = inf.nextInt();  //read next idno
                myname = inf.next(); //read next name
                insert(myid, myname);
            } // end for
            // print inorder traversal of btree
            inorder();
            // print height of btree
            prt.printf("\n\tHeight of btree =%d", height());
            // Query BTree
            nsrch = inf.nextInt();  //read no. of data to query
            for (j = 1; j <= nsrch; j++) {
                myid = inf.nextInt(); //read myid to search
                SearchResult t = search(myid);
                if (t.found == false)
                    prt.printf("\n %d does not EXIST.", myid);
                else {
                    prt.printf("\nName of %d is %s.", myid, null);
                }
            }  // end Query BTree
        } catch(Exception e){
                // WAS GIVEN TO ME AS CATCH...
        }
    } // end process method

    // main method
    public static void main(String[] args) throws Exception {
        // create an instance of btree
        ShamiH4 t = new ShamiH4();
        // process to insert in a btree, print btree, its height and query the btree
        t.process();
        // MAKE SURE TO WRITE YOUR NAME IN NEXT LINE
        System.out.printf("\n\tAuthor: Sami Shami Date: " + java.time.LocalDate.now());
    } // end main method
} // end ShamiH4 class