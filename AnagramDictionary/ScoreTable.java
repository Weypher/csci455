// Name: Wenbo Ye	
// USC NetID: wenboye
// CS 455 PA4
// Fall 2018

/**
 * This class provide the score for all the letters.
 * 
 */
public class ScoreTable {
	
  /**
	 * calculate the given word's score according to the given rule
	 * @return the score of the given word
	 * 
	 * Invariant Representation:
	 * charToScore table's i th value represents the score of the i th
	 * letter in the alphabet
	 **/
	static final int[] scoreTable = {1, 3, 3, 2, 1, 4, 2, 
									 4, 1, 8, 5, 1, 3, 1,
									 1, 3, 10, 1, 1, 1, 1, 
									 4, 4, 8, 4, 10};

	
	/**
	 * calculate the score of the given word according to the charToScore table
	 * @param word the word that 
	 * @return the score of the word according to the score charToScore table
	 * 
	 */
	public static int calculateScore(String word) {
		final int n = word.length();
		String lowerCaseString = word.toLowerCase();
		int sum = 0;
		for (int i = 0; i < n; i++) {
			char c = lowerCaseString.charAt(i);
			if ('a' <= c && c <= 'z' ) {
				sum += scoreTable[c - 'a'];
			}
		}
		return sum;
	}
}
