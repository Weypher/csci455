import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

// Name: Wenbo Ye
// USC NetID: wenboye
// CS 455 PA4
// Fall 2018

/**
 * 
 * This program simulate Scrabble game, 
 * receive user's input as a string of rack letters (stop if input '.')
 * print all valid result combinations in score decreasing order,
 * if two words have the same score, print in lexicographical order
 * 
 * run program as: java WordFinder [dictionaryFile]
 * [dictionaryFile] dictionary to look up from, default: sowpods.txt
 * 
 */
public class WordFinder {
	public static void main(String[] args) {
		//set default dictionary
		String defaultDic = "sowpods.txt";
		Scanner in = new Scanner(System.in);
		AnagramDictionary anagramDic;
		Rack rack;
		WordFilter wordFilter;
		
		try {
			//the dictionary can be initialized
			if (args.length > 0) {
				defaultDic = args[0];
			}
			anagramDic = new AnagramDictionary(defaultDic);
			
			System.out.println("Type . to quit.");
			System.out.print("Rack? ");
			String inputStr = in.next();
			
			while (!inputStr.trim().equals(".")) {
				rack = new Rack(inputStr);
				wordFilter = new WordFilter();
				for (String subset : rack.gerAllSubSets()) {
					wordFilter.addWords(anagramDic.getAnagramsOf(subset));
				}
			
				//print result
				System.out.print(wordFilter.getResults(inputStr));
				
				//next round input
				System.out.print("Rack? ");
				inputStr = in.next();
			}
		} catch (FileNotFoundException exception) {
			System.out.println(exception);
		}
	}
}
