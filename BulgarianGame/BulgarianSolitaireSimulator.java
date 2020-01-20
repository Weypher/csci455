import java.util.*;


// Name: Wenbo Ye
// USC NetID: wenboye
// CSCI455 PA2
// Fall 2019


/*
  class SolitaireBoard
  The board to launch SolitaireBorad Class.  the total sum of the game in rule is 45, which is limited
  by the SolitaireBorad Class. In this class, you can use "-u" for manually input the array you want; 
  "- s" is to realize the game round step by step. you should check your input as numbers and splited by 
  " "(space), without any other letters or punctuations. Also the sum of input numbers is 45. Every single 
  numbers are non-positive.
*/

public class BulgarianSolitaireSimulator {
   public static final int TOTAL_SUM = 45;//Total number which is same as the SolitaireBoard
   public static void main(String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }
      
      Scanner scanner = new Scanner(System.in);
      SolitaireBoard getBoard = initilizeBoard(userConfig, scanner);
      SolitaireBoardSim(getBoard, singleStep, scanner);
      
   }
   
   /**
    * The main is used to simulate the SolitaireBoard Class. You can input the SolitaireBoard by yourself
    * or just generate an new array automatically. SingleStep is used to realize the function that every round
    * in the game can be separated by entering return. The Type of getBoard must input as array which is separated
    * by the " " space and no more than 45. the sum of array numbers needs to be 45.
    * @param getBoard: create the arrays in piles which numbers are added up to 45.
    * @param singleStep: mode of step. if turn true, we can see the round step by step.
    * @param scanner: the input of array which are separated by the space " ".
    */
   private static void SolitaireBoardSim(SolitaireBoard getBoard, boolean singleStep, Scanner scanner) {
	   
	   System.out.println("Initial configuration: " + getBoard.configString());
	   int runTimes = 0;
	   if(getBoard.isDone()) {
		   System.out.println("Done!");
	   }
	   while(!getBoard.isDone()) {
		   getBoard.playRound();
		   runTimes++;
		   System.out.println("[" + runTimes + "] " + "Current configuration: " + getBoard.configString());
		   if(singleStep) {
			   System.out.print("<Type return to continue>");
			   scanner.nextLine(); 
		   }
           if(getBoard.isDone()) {
			   System.out.println("Done!");
		   }
	   }
   }

    /**
	 * Initialize the Solitaire Board based on userConfig-mode or auto-generate-mode.
	 * @param userConfig: whether user set the user's mode.
	 * @param scannder: scanner used for input numbers as initial arrayList.
	 * @return user-self built arrayList or random-generated arrayList.
	 */
	private static SolitaireBoard initilizeBoard(boolean userConfig, Scanner scanner) {
		if (userConfig) {
			System.out.println("Number of total cards is 45");
		    System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
		    System.out.println("Please enter a space-separated list of positive integers followed by newline:");

			// read input
			String readLine = scanner.nextLine();
			while (!inputIsTrue(readLine)) {
				System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
				System.out.println("Please enter a space-separated list of positive integers followed by newline:");
				readLine = scanner.nextLine();
			}

			ArrayList<Integer> inputLine = readToArrayList(readLine);
			return new SolitaireBoard(inputLine);
			
		} else {
			return new SolitaireBoard();
		}
	}
	
	
	/**
	 * This method is used to generate the new ArrayList
	 * @param readLine: the Line scanned by scanner.
	 * @return a new line in form of ArrayList
	 */
	private static ArrayList<Integer> readToArrayList(String readLine) {
		ArrayList<Integer> result = new ArrayList<>();
		String[] splitItem = readLine.split(" ");
		for(String val : splitItem) {
			int tempNum = Integer.parseInt(val);
			result.add(tempNum);
		}
		return result;
	}

	
	
	/**
	 * This method is used to check if user input correct form of line.
	 * @param input: users' input.
	 * @return: return true when the input is correct.
	 */
    private static boolean inputIsTrue(String input) {
	   int sum = 0;
	   String[] piles = input.split(" ");
       for (String content : piles){
		   try {//if the sum of input numbers are non-positive, return false
			   int pile = Integer.parseInt(content);
			   if (pile <= 0) {
				   return false; 
			   }
			   sum += pile;
		   }
		   catch (NumberFormatException ex) {// if input is letters or punctuation or some other invalid items, return false
			   return false; 
		   }
	   }
	   return sum == TOTAL_SUM; //check the sum is 45 or not.
    }
	
	
	
   

  
}
