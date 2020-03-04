/**
 * @author 233
 *
 */
public class GeneAnalysis
{
	/** Wrapper method. Returns the length of the longest 
	 * common subsequence
	 */
	public static int score(String gene1, String gene2)
	{	
		if(gene1.length() == 0 || gene2.length() == 0){
			return 0;
		}
		int result = score(gene1.toCharArray(),gene2.toCharArray(),gene1.length()-1,gene2.length()-1);
		if(result == 0) return result;
		else return result+1;
		//throw new IllegalArgumentException("Not Yet Implemented");
		// Hint: Use toCharArray() to convert each string to a char array.
		 // call your recursive implementation here with
		// the genes as char arrays, starting at the end of each gene.
	}
	

	/** Implements longest common subsequence recursive search
The recursive case is defined as
					S(i-1, j)
S(i,j) = max {		S(i,j-1)
					S(i-1,j-1)
					S(i-1,j-1) +1 if gene1[i] = gene2[j]

NB  0<=i < gene1.length
    0<=j < gene2.length

You need to figure out the base case.
	 * */
	private static int score(char[] gene1,char[] gene2,int i, int j){
		if(i == 0 || j == 0) return 0;
		int d = 0;
		if (gene1[i] == gene2[j]) d = score(gene1,gene2,i-1,j-1)+1;
		return Math.max(score(gene1,gene2,i-1,j), Math.max(score(gene1, gene2,i,j-1),Math.max(score(gene1,gene2,i-1,j-1),d)));
		/*
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		if (i == 0 || j == 0) {
			if (i== 0 && j==0) { 
				if( gene1[0] == gene2[0]) return 1;
				else return 0;
			}
			
			if (i==0 && j==0) {
				if( gene1[0] != gene2[0]) return 0;
				else return 0;
			}
			
			if (i==1 && j==0) {
				if( gene1[1] == gene2[0]) return 1;
				else return 0;
			}
			if (i==0 && j==1) {
				if( gene1[0] == gene2[1]) return 1;
				else return 0;
			}
			if (i==1 && j==1) {
				if( gene1[1] == gene2[1] && gene1[0]==gene2[0]) return 2;
				else return 0;
			}
			else return 0;
		}
		//
		
		if (gene1[i] == gene2[j-1]) a = 1;
		if (gene1[i-1] == gene2[j]) b = 1;
		if (gene1[i-1] == gene2[j-1]) c = 1;
		if (gene1[i] == gene2[j] && gene1[i-1] == gene2[j-1]) d = 2;
		return Math.max(a,Math.max(b,Math.max(c, d)));//+score(gene1,gene2,i-1,j-1);
		//if(gene1[i] == gene2[j]) return Math.max(score(gene1,gene2,i,j-1), Math.max(score(gene1,gene2,i-1,j),score(gene1,gene2,i-1,j-1)))+1;
		//else return Math.max(score(gene1,gene2,i,j-1), Math.max(score(gene1,gene2,i-1,j),score(gene1,gene2,i-1,j-1))) ;
		//Math.max(a,Math.max(b,Math.max(c, d)))
		*/
		
	}
//	define a private recursive Class method 'score' that 
//	returns an integer the score.
//	The method should take four parameters- 
//	two char arrays and two integers (gene1,gene2,i,j)
//	i and j are the indices into gene1 and gene2 respectively.

}
// Use local variables to store a recursive result so that you  do not need to calculate it again.

// Do not use a loops.
