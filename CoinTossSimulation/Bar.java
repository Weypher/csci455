// Name:Wenbo Ye
// USC NetID:wenboye
// CS 455 PA1
// Fall 2019

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * How to run:
 * Call the Bar constructor in another class and input the parameter variables
 * to initialize the object. Call Bar.draw(g) method to draw bars.
 * 
 * Instance variable:
 *		int bottomLoc : bottom  location of the bottom of the label;
 *		int leftLoc : left  location of the left side of the bar;
 *		int widthSize : width  width of the bar (in pixels);
 *		int Height : barHeight  height of the bar in application units;
 *		double scalePixel : barHeight  height of the bar in application units;
 *		Color fillColor : the color of the bar;
 *		String nameLabel : the label at the bottom of the bar;
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
   private int bottomLoc;
   private int leftLoc;
   private int widthSize;
   private int Height;
   private double scalePixel;
   private Color fillColor;
   private String nameLabel;	


   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight ,
              double scale, Color color, String label) {
	   bottomLoc = bottom;
	   leftLoc = left;
	   widthSize = width;
	   Height = barHeight;
	   scalePixel = scale;
	   fillColor = color;
	   nameLabel = label;
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
	   Font font = g2.getFont();
	   FontRenderContext context = g2.getFontRenderContext();
	   Rectangle2D labelBounds = font.getStringBounds(nameLabel, context);
	   int widthOfLabel = (int) labelBounds.getWidth();
	   int heightOfLabel = (int) labelBounds.getHeight();
	   
	   Rectangle box = new Rectangle(leftLoc, bottomLoc, widthSize, Height * (int)scalePixel);
	   g2.setColor(fillColor);
	   g2.fill(box);
	   g2.draw(box);
	    
	   g2.setColor(Color.BLACK);
	   g2.drawString(nameLabel, leftLoc + widthSize/2 - widthOfLabel/2,
	    		     bottomLoc + Height * (int)scalePixel + heightOfLabel);
	    

   }
}
