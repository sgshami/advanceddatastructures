import java.util.*;

public class Tester {
	public int[][] multiplier(int R[][], int S[][]){
		int[][] C = new int[R.length][S[0].length]; //Final matrix
		for(int i = 0; i<R.length; i++) { //For each row of C
			for(int j = 0; j<S[0].length; j++) { //For each column C
				C[i][j] = 0;
				for(int k = 0; k<S.length; k++) {
					C[i][j] = C[i][j] + R[i][k] * S[k][j]; //Formula to multiply both matrix
				}
			}
		}
		return C;
	}

	public static void main(String[] args) {
		Tester t = new Tester();
		int [][] A = {{2, 3}, {3, 4}};
		int [][] B = {{1, 2, 3}, {3, 1, 2}};
		int [][] C = t.multiplier(A, B);
		
		for(int i = 0; i<C.length; i++) {
			for(int j = 0; j<C[0].length; j++)
				System.out.print(C[i][j] + "  ");
			System.out.println();
		}
	}
}
