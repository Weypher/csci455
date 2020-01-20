// Name: Wenbo Ye
// USC NetID: wenboye
// CS 455 PA4
// Fall 2019

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


/**
   A Rack of Scrabble tiles
 */

public class Rack {
	
	/**
	 * Representation Invariants:
	 * mult[i] denotes the numbers of characters appeared in unique String.
	 * unique denotes the unique letters of String
	 **/
	private String unique;
	private int[] mult;
	
	public Rack(String word) {
		// TODO Auto-generated constructor stub
		//get rid of non-letter symbols.
		word = Rack.pureLetterString(word);
		
		//set a TreeMap to get the character to frequency by lexicographical orders.
		Map<Character, Integer> charToFreq = new TreeMap<>();
		final int n = word.length();
		for (int i = 0; i < n; i++) {
			char c = word.charAt(i);
			charToFreq.put(c, charToFreq.getOrDefault(c, 0) + 1);
 		}
		
		//build instance variant of "unique" & "mult"
		unique = "";
		mult = new int[word.length()];
		int point = 0; //pointer in mult
		for (Map.Entry<Character, Integer> entry : charToFreq.entrySet()) {
			unique += entry.getKey();
			mult[point] = entry.getValue();
			point++;
		}
	}

	/**
	 * get rid of the non-letter characters
	 * @param s input words with some other symbol
	 * @return pure letter without any other symbol.
	 */
	private static String pureLetterString(String s) {
		final int n = s.length();
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
				sBuilder.append(c);
			}
		}
		return sBuilder.toString();
	}
	
	


	/**
	 * Get all the subset of the rack.
	 */
	public ArrayList<String> gerAllSubSets() {
		return Rack.allSubsets(this.unique, this.mult, 0);
	}
	
    /**
       Finds all subsets of the multiset starting at position k in unique and mult.
       unique and mult describe a multiset such that mult[i] is the multiplicity of the char
            unique.charAt(i).
       PRE: mult.length must be at least as big as unique.length()
            0 <= k <= unique.length()
       @param unique a string of unique letters
       @param mult the multiplicity of each letter from unique.  
       @param k the smallest index of unique and mult to consider.
       @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
       each subset is represented as a String that can have repeated characters in it.
       @author Claire Bono
     */
    private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
       ArrayList<String> allCombos = new ArrayList<>();
       
       if (k == unique.length()) {  // multiset is empty
          allCombos.add("");
          return allCombos;
       }
      
       // get all subsets of the multiset without the first unique char
       ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
       
       // prepend all possible numbers of the first char (i.e., the one at position k) 
       // to the front of each string in restCombos.  Suppose that char is 'a'...
       
       String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
       for (int n = 0; n <= mult[k]; n++) {   
          for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                         // we found in the recursive call
             // create and add a new string with n 'a's in front of that subset
             allCombos.add(firstPart + restCombos.get(i));  
          }
          firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
       }
      
       return allCombos;
    }


    /**
     * get the string representation of this rack.
     */
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	String res = "";
       	res += "unique:" + unique + "\n";
       	res += "mult:" + Arrays.toString(mult);
    	return res;
    }
}
