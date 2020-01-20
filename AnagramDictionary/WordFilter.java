// Name: Wenbo Ye
// USC NetID: wenboye
// CS 455 PA4
// Fall 2019

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class help receive the words and return the sorted results.
 * 
 */
public class WordFilter {
    
	Set<Result> wordResult;
	/**
	 * Representation Invariant:
	 * words in wordFilter can be rearanged in letter orders and scores.
	 * first sorted by scores, if same, then sorted by letter.
	 * 
	 */
	public WordFilter() {
		wordResult = new TreeSet<>();
	}
	
	public void addWords(List<String> words) {
		for (String str : words) {
			wordResult.add(new Result(str));
		}
	}
	
	public String getResults(String input) {
		String str = "We can make " + wordResult.size() + " words"
				+ " from \"" + input +"\"\n";
		if (wordResult.size() != 0) {
			str += "All of the words with their scores (sorted by score):\n";
			for(Result res : wordResult) {
				str += res.toString() + "\n";
			}
		}
		     
		return str;
	}
	
   /**
    * This private class is used to override the compareTo method for the self-contruct class "Result".
    * 
    */
	private class Result implements Comparable<Result>{
		private String _word;
		private int _score;
		public Result(String word) {
			_word = word;
			_score = ScoreTable.calculateScore(word);
		}
		@Override
		public int compareTo(Result other) {
			// TODO Auto-generated method stub
			if (this._score == other._score) {
				return this._word.compareTo(other._word);
			} else {
				return other._score - this._score;
			}
		}
		@Override
		public String toString() {
			String str = "";
			str += this._score + ": " + this._word;
			return str;
		}
	}
}
