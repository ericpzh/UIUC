/** 
 * Test cases for GeneAnalysis.
 * You do not need to modify this file,
 * though you may find it useful to understand the test cases in detail.
 * Please see Part3-GeneAnalysisTasks for more information about this problem.
 * 
 */
import junit.framework.TestCase;
/**
 * Test file. You do do not need to modify this file.
 * @author angrave
 *
 */

public class GeneAnalysisTest extends TestCase
{              
  public void testEmptyIsZero() {
		 assertEquals(0, GeneAnalysis.score("",""));
		 assertEquals(0, GeneAnalysis.score("ATG",""));
		 assertEquals(0, GeneAnalysis.score("","ATG"));
  }

  public void testScore0() {
	  assertEquals(0, GeneAnalysis.score("AC","GT")); 
	  assertEquals(0, GeneAnalysis.score("GGTT","CCAA")); 	  
  }
  public void testScore2() {
	  assertEquals(2, GeneAnalysis.score("ACGT","AC")); 
	  assertEquals(2, GeneAnalysis.score("ACGT","AG")); 
	  assertEquals(2, GeneAnalysis.score("ACGT","AT")); 
	  assertEquals(2, GeneAnalysis.score("ACGT","CG"));   
	  assertEquals(2, GeneAnalysis.score("ACGT","CT"));   
	  assertEquals(2, GeneAnalysis.score("ACGT","GT"));   
// Symmetric cases:
	  assertEquals(2, GeneAnalysis.score("AC","ACGT")); 
	  assertEquals(2, GeneAnalysis.score("AG","ACGT")); 
	  assertEquals(2, GeneAnalysis.score("AT","ACGT")); 
	  assertEquals(2, GeneAnalysis.score("CG","ACGT"));   
	  assertEquals(2, GeneAnalysis.score("CT","ACGT"));   
	  assertEquals(2, GeneAnalysis.score("GT","ACGT"));   
  }
  
  public void testScore4() {
	  assertEquals(4, GeneAnalysis.score("ACCGGT","ACGT")); 
	  assertEquals(4, GeneAnalysis.score("ACGT","ACCGGT"));   
  }

  public void testScore5() {
	  assertEquals(5, GeneAnalysis.score("ATCTGATC","TGCATAC")); 
	  assertEquals(5, GeneAnalysis.score("ATCTGAT","ATCGTAC")); 
	  assertEquals(5, GeneAnalysis.score("AAACCCGGTTAA","ACGTA")); 
  }
  
  public void testScore8() {
	  // Your method should work in the case that a particular
	  // nucleotide is unknown.
	  // Treat ? as ajust another character (no special case logic is required)
	  assertEquals(8, GeneAnalysis.score("ACGT???ACGT","ACGTACGT")); 

	  assertEquals(8, GeneAnalysis.score("ACGTACGT","ACGT???????ACGT"));   
  }
	
	protected void tearDown() throws Exception {
		CheckInputOutput.resetInputOutput();
	}
}
