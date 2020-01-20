// Name: Wenbo Ye
// USC NetID: wenboye
// CS 455 PA4
// Fall 2019

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
	/**
	    * Representation Invariant:
	    * 1. LexiToWords is a map holding the reordered anagram words as the key and original words lists as its value.
	    * 2. value includes all words in input dictionary (without any other words).
	    **/
    private Map<String, List<String>> LexiToWords;


    /**
       Create an anagram dictionary from the list of words given in the file
       indicated by fileName.  
       PRE: The strings in the file are unique.
       @param fileName  the name of the file to read from
       @throws FileNotFoundException  if the file is not found
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException {
	    LexiToWords = new HashMap<>();
	    
	    //readfile
	    File inFile = new File(fileName);
	    Scanner in = new Scanner(inFile);
	    // read all words and put into the hash map
	    while(in.hasNext()) {
	 	    String str = in.nextLine().trim();
		    String orderedStr = AnagramDictionary.toLexiString(str);
		    if (LexiToWords.containsKey(orderedStr)) {
			    LexiToWords.get(orderedStr).add(str);
		    } else {
			    List<String> anaList = new ArrayList<>();
			    anaList.add(str);
			    LexiToWords.put(orderedStr, anaList);
		    }
	    }
    }
   
    /**
     * Rearrange its chars in lexicographical order
     * @param word the word to be transformed
     * @return new lexicographical ordered word
     */
    private static String toLexiString(String word) {
	    char[] charArray = word.toCharArray();
	    Arrays.sort(charArray);
	    return new String(charArray);
    }

    /**
       Get all anagrams of the given string. This method is case-sensitive.
       E.g. "CARE" and "race" would not be recognized as anagrams.
       @param s string to process
       @return a list of the anagrams of s
     */
    public List<String> getAnagramsOf(String s) {
	    String oString = AnagramDictionary.toLexiString(s);
        return LexiToWords.getOrDefault(oString, new ArrayList<>()); // DUMMY CODE TO GET IT TO COMPILE
    }
    /**
     * override the toString() method in object class for debug.
     */
    public String toString() {
	    String res = "";
	    for(Map.Entry<String, List<String>> entry : LexiToWords.entrySet()) {
	   		res += entry.getKey() + ":" + entry.getValue() + "\n";
	   		System.out.println(entry.getKey());
	   	}
	   	return res;
   }
   
}
