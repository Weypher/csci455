import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
/**
 * The CoinSimComponent Class extends JComponent.
 * This class use the Bar Class and CoinTossSimulator Class.
 * 
 * The CoinSimComponent has a constructor and three private methods
 * called BarTitles, draw, paintComponent.
 * 
 * How to run: Call a constructor and input the initial parameter variables.
 * 			   Set a frame and use the Frame method add(CoinSimComponent Object).
 * 
 * The CoinSimComponent use the following name constant:
 * 		HEAD_TAIL_COLOR : the color of bar of "one head and one tail".
 * 		TWO_HEADS_COLOR : the color of bar of "two heads tossing".
 * 		TWO_TAILS_COLOR : the color of bar of "two tails tossing".
 * 		VERTICAL_BUFFER : the vertical distance from bottom of frame to the bottom of the label.
 * 		BAR_WIDTH : the width of the bar.
 * 
 * The CoinSimComponent use the following instance variable:
 * 		int twoheadsNum : the trial number of two heads tossing.
 * 		int twotailsNum : the trial number of two tails tossing.
 * 		int headtailNum : the trial number of head-tail tossing.
 * 		int trialNum : the total trial times.
 * 		CoinTossSimulator simulator : create an object named as simulator to use the method in CoinTossSimulator class.
 * 
 */
public class CoinSimComponent extends JComponent {
	private static final Color HEAD_TAIL_COLOR = Color.RED;
    private static final Color TWO_HEADS_COLOR = Color.GREEN;
    private static final Color TWO_TAILS_COLOR = Color.BLUE;
    private static final int VERTICAL_BUFFER = 30;
    private static final int BAR_WIDTH = 50;
    
    private int twoheadsNum;
    private int twotailsNum;
    private int headtailNum;
    private int trialNum;
    private CoinTossSimulator simulator;

	    
	/**
	 * Creates a constructor. Give the total number of trials.
	 * 
	 * @param numTrials : the total times 
	 */
    public CoinSimComponent(int numTrials){
    	simulator = new CoinTossSimulator();
    	simulator.run(numTrials);
    	twoheadsNum = simulator.getTwoHeads();
    	twotailsNum = simulator.getTwoTails();
    	headtailNum = simulator.getHeadTails();
    	trialNum = simulator.getNumTrials();
    }
    
    
	/**
	 * Calculate the different percents of the different bars and output the labels under the bar image.
	 * 
	 * @param label : The name of the different labels such as "Twoheads", "Twotails" and "Tail-Head"
	 * @param caseNum : the number of trials in the specific case.
	 * @return : Output the String names.
	 */
    private String barTitle(String label, int caseNum) {
    	double perc = Math.round(((double)caseNum * 100 / (double)trialNum));
    	String a = label + " " + caseNum + " (" + (int) perc + " %)";
    	return a;
    }
    
    
	/**
	 * Calculate all the size, width, height of the bar in the frame. Make sure that the three label has their
	 * own names and evenly distributes in the frame.
	 * 
	 * @param g2 : awt.Graphics2D context. Used to draw bars.
	 * @param barColor : the color of the bars.
	 * @param position : make different locations between three bars.
	 * @param label : title of the bars
	 * @param caseTime : the number of trials in the specific case.
	 */
    private void draw(Graphics2D g2, Color barColor, int position, String label, int caseTime) {
        Font font = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBounds = font.getStringBounds(label,context);
        
        int heightOfLabel = (int)labelBounds.getHeight();
        int tallestBar = Math.max(Math.max(twoheadsNum, twotailsNum), headtailNum);
        int barHeight = (int)((double)caseTime/ (double)trialNum * 100); 
        double barScale = (getHeight() - heightOfLabel - VERTICAL_BUFFER) / 100;
        int coordinateX = getWidth()/6 - BAR_WIDTH/2;
        int coordinateY = getHeight() - VERTICAL_BUFFER - heightOfLabel - barHeight * (int)barScale;
        int barWidth = BAR_WIDTH;
        
        if (position == 2) {
        	coordinateX = coordinateX + getWidth()/3;
        }
        else if (position == 3) {
        	coordinateX = coordinateX + 2 * getWidth()/3;
        }
        Bar singleBar = new Bar(coordinateY, coordinateX, barWidth, barHeight,
                barScale, barColor, barTitle(label, caseTime));
        singleBar.draw(g2);

    }

	/**
	 * Draw the three bars and input the different conditions. 
	 */
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	draw(g2 ,TWO_HEADS_COLOR, 1, "TWO HEAD", twoheadsNum);
        draw(g2 ,HEAD_TAIL_COLOR, 2, "HEAD TAIL", headtailNum);
        draw(g2 ,TWO_TAILS_COLOR, 3, "TWO TAIL", twotailsNum);
    }
}
