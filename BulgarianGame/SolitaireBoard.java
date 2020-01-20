// Name:Wenbo Ye
// USC NetID:wenboye
// CSCI455 PA2
// Fall 2019

import java.util.ArrayList;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total 
  number of cards is for the game by changing NUM_FINAL_PILES, below.  
  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.  (See comments 
  below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt 
   // description for details.
   public static final int ARRAY_UPPER_BOUND = 45;
   // set up the number of array of upper bound.
   
   public static final int OFFSET = 1;
   //set for the numToFreq array to cast the number.
   
   /**
      Representation invariant:
      The number in the piles must satisfy the following requirements:
      1. All numbers put in are range of (0, 45].
      2. All separate numbers can be added up to 45;
      3. The pileSize is range in (0, 45].
      3. Inputs must be numbers and integers without any other punctuation but can use the space characters to separate them.
      <put rep. invar. comment here>
   */

   public static int[] _pileCards;
   //pileCards is the array load the different piles of the cards
   public static int _pileSize;
   //pileSize used to keep track of the array numbers.
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of 
      cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to 
      SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	   _pileCards = new int[ARRAY_UPPER_BOUND];
	   _pileSize = 0;
	   
	   //add all the numbers in arrayList into array and flash _pileSize.
	   for(int i = 0; i < piles.size(); i++) {
		   _pileCards[i] = piles.get(i);
		   _pileSize++;
	   }

      // sample assert statement (you will be adding more of these calls)
      // this statement stays at the end of the constructor.
      assert isValidSolitaireBoard();   
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   _pileCards = new int[ARRAY_UPPER_BOUND];
	   _pileSize = 0;
	   Random numGenerator = new Random();
	   
	   //use totalSum to record the current sum of the numbers in array.
	   int totalSum = 0;
	   
	   while(totalSum != CARD_TOTAL) {
		   _pileCards[_pileSize] = numGenerator.nextInt(CARD_TOTAL - totalSum + 1);
		   if(_pileCards[_pileSize] != 0) {
			   totalSum += _pileCards[_pileSize];
			   _pileSize++;
		   }
		   
	   }
	   
	   assert isValidSolitaireBoard();


   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration 
      according to the rules of Bulgarian solitaire: Takes one card from each
      pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	   //from 0 to pileSize, extracted 1 cards and put them in the another unit.
	   //set a new sizeTracking.
	   int pileTrack = 0;
	   
	   //every unit minus 1
	   for (int i = 0; i < _pileSize; i++) {
		   if(_pileCards[i] != 0) {
			   _pileCards[i]--;
		   }
		   if(_pileCards[i] != 0) {
			   _pileCards[pileTrack] = _pileCards[i];
			   pileTrack++;
		   }
	   }
	   //add the final number into another unit
	   _pileCards[pileTrack] = _pileSize;
	   
	   //renew the pileSize
	   _pileSize = pileTrack + 1;
      assert isValidSolitaireBoard();  

   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, 
      there are NUM_FINAL_PILES piles that are of sizes 
      1, 2, 3, . . . , NUM_FINAL_PILES, 
      in any order.
   */
   
   public boolean isDone() {
       //if pileSize is the same as set, then test in next step; else return false
	   if(_pileSize == NUM_FINAL_PILES) {
		   //create an new Array to scan the pile and record the numbers' frequency in array.
		   int[] numToFreq = new int[CARD_TOTAL];
		   for(int i = 0; i < _pileSize; i++) {
			   numToFreq[_pileCards[i] - OFFSET]++;
			   //check the frequency.
			   if(numToFreq[_pileCards[i] - OFFSET] > 1) {
				   return false;
			   }
		   }
		   return true;
	   }
      assert isValidSolitaireBoard();  
      return false;  // dummy code to get stub to compile
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
	   String result = _pileCards[0] + "";
	   for(int i = 1; i < _pileSize; i++) {
		   result += (" " + _pileCards[i]);
	   }
       assert isValidSolitaireBoard();  

       return result;   // dummy code to get stub to compile
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      int sumOfPiles = 0;
      for (int i = 0; i < _pileSize; i++) {
         if (_pileCards[i] < 0) {
            return false;
         }
		 sumOfPiles += _pileCards[i];
      }
      return sumOfPiles == CARD_TOTAL;  
	}
   

   // <add any additional private methods here>

}
