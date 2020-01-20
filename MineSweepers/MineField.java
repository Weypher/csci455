// Name:Wenbo Ye
// USC NetID: wenboye
// CS 455 PA3
// Fall 2019
import java.util.Arrays;
import java.util.Random;


/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
   
	/**
	 * Representation invariant:
     * _mineFields is the total matrix of mine field.
	 * _numsMines is the total number of mines generate in fields. It must be non-positive number.
	 * _numsRows represents the total grids in fields of rows. It must be positive number.
	 * _numsCols represents the total grids in fields of columns. It must be positive number.
	 * _randomGenerator is used to generate the mines in fields randomly.
     * _isPopulated is used to test whether the populateMineField() is exercuted, value is true or false.
	 */
   
	private int _numsMines;
	private int _numsRows;
	private int _numsCols;
	private boolean[][] _mineFields;
	private Random _randomGenerator;
	//private boolean _isPopulated;
   
   
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
    * @param mineData  the data for the mines; must have at least one row and one col.
    */
   public MineField(boolean[][] mineData) {
	  _numsRows = mineData.length;
	  _numsCols = mineData[0].length;
	  _mineFields = new boolean[_numsRows][_numsCols];
	  //_isPopulated = true;
	  
	  //copy array
	  for (int i = 0; i < _numsRows; i++) {
		  _mineFields[i] = mineData[i].clone();
	  }
	  
	  //check input mineData's size
	  if (_numsRows == 0 || _numsCols == 0 ) {
	     throw new IllegalArgumentException("MineData must have at least one row and one col.");
	     }
	  
	  //count the numbers of mines
	  int count = 0;
	  for (int i = 0; i < _numsRows; i++) {
		  for (int j = 0; j < _numsCols; j++) {
			  if (_mineFields[i][j] == true) {
				  count++;
			  }
		  }
	  }
	  _numsMines = count;
	  
      
      
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      _numsCols = numCols;
      _numsRows = numRows;
      _mineFields = new boolean[_numsRows][_numsCols];
      _numsMines = numMines;
      //_isPopulated = false;
      
      //check input mineData's size
	  if (_numsRows <= 0 || _numsCols <= 0 || _numsMines < 0 || _numsMines > (_numsCols * _numsRows) / 3) {
	     throw new IllegalArgumentException("PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).");
	     }
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {
	  assert inRange(row, col);
	  //_isPopulated = true;
	  resetEmpty(); //clear the fields
	  
	  //generate the mines
	  _randomGenerator = new Random();
	  int currMineSums = 0;
	  int mineRow;
	  int mineCol;
	  while (currMineSums < _numsMines) {
		  mineRow = _randomGenerator.nextInt(_numsRows);
		  mineCol = _randomGenerator.nextInt(_numsCols);
		  if (_mineFields[mineRow][mineCol] == false && !(mineCol == col && mineRow == row)) {//only if the filed has no mine and is not the (row,col)
			  _mineFields[mineRow][mineCol] = true;
			  currMineSums++;
		  }
	  } 
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state the minefield is in at the beginning of a game.
    */
   public void resetEmpty() {
      for (int i = 0; i < _numsRows; i++) {
    	  for (int j = 0; j < _numsCols; j++) {
    		  _mineFields[i][j] = false;
    	  }
      }
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
	  assert inRange(row, col);
	  
	  int res = 0;
	  for (int i = row - 1; i <= row + 1; i++) {
		  for (int j = col - 1; j <= col + 1; j++) {
			  if (inRange(i, j) && (i != row || j != col) && _mineFields[i][j]) {
				  res++;
			  }
		  }
	  }
      return res;
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
	  boolean rowTester = (row >= 0 && row < _numsRows);
	  boolean colTester = (col >= 0 && col < _numsCols);
	  boolean res = rowTester && colTester;
      return res;
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return _numsRows;
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return _numsCols;
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
	  assert inRange(row, col);
      return _mineFields[row][col];
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
	  //if(_isPopulated == false) {
	  //  return 0;
	  //}
      return _numsMines;
   }

   
   // <put private methods here>
   /**
      Used to show the result in testers.
      @return str
    */
   public String toString() {
	   String str = "";
	   for (int i = 0; i < _numsRows; i++) {
		   str += Arrays.toString(_mineFields[i]) + "" + "\n";
	   }
	   return str;
   }
         
}

