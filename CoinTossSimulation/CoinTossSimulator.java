// Name:Wenbo Ye
// USC NetID:wenboye
// CS 455 PA1
// Fall 2019

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

import java.util.Random;

public class CoinTossSimulator {
	private int numTrials, twoheads, twotails, headtails;
	private Random tossing;



   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   numTrials = 0;
	   twoheads = 0;
	   twotails = 0;
	   headtails = 0;
	   tossing = new Random();
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
	   int first = 0;
	   int second = 0;
	   if (numTrials <= 0) {
		   System.out.println("ERROR: Number entered must be greater than 0.");
	   }
	   
	   else if (this.numTrials > Integer.MAX_VALUE - numTrials) {
		   System.out.println("ERROR: Number entered is beyond the maximum testing times.");
	   }
	   else {
		   for (int i = 0; i < numTrials; i++) {
			   first = tossing.nextInt(2);
			   second = tossing.nextInt(2);
			   if (first + second == 0) {
				   twotails++;
			   }
			   else if (first + second == 1) {
				   headtails++;
			   }
			   else {
				   twoheads++;
			   }
		   }
		  this.numTrials += numTrials;
	    }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return numTrials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return twoheads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return twotails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return headtails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   numTrials = 0;
	   twoheads = 0;
	   twotails = 0;
	   headtails = 0;
   }

}
