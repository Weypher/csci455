import java.util.Scanner;
import javax.swing.JFrame;

/**
 * After tossing the coin twice, record the times and show the result in the bar-shaped statistical figure.
 * This class use the CoinSimComponent class and CoinTossSimulator class.
 * 
 * How to use:
 * 		Input the total time of tossing the coins and receive the result of statistical figures.
 * 
 * The CoinSimViewer has the following name constant:
 * 		int WIDTH_OF_FRAME : the width of the frame.
 * 		int HEIFHT_OF_FRAME : the height of the frame.
 * 		String TITLE : the name of the frame.
 * 
 * The CoinSimViewer has the following instance variable:
 * 		Scanner in : create an object to receive input number.
 * @author wenboye
 *
 */
public class CoinSimViewer {
	private static Scanner in;
	private static final int WIDTH_OF_FRAME = 800;
	private static final int HEIFHT_OF_FRAME = 500;
	private static final String TITLE = "Coing Tossing";

	public static void main(String[] args) {
		in = new Scanner(System.in);
		int trialNumber;
		do {
			System.out.print("Enter the number of :");
			trialNumber = in.nextInt();
			if (trialNumber <= 0) {
				System.out.println("Error: Number entered must be greater than 0");
			}
			else if (trialNumber - Integer.MAX_VALUE > 0) {
				System.out.println("Error: Number entered is over the calculating range.");
			}
		}
		while (trialNumber <= 0 || trialNumber - Integer.MAX_VALUE > 0);
		JFrame coin_frame = new JFrame();
		coin_frame.setSize(WIDTH_OF_FRAME, HEIFHT_OF_FRAME);
		coin_frame.setTitle(TITLE);
		coin_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    CoinSimComponent component = new CoinSimComponent(trialNumber);
	    coin_frame.add(component);
	    coin_frame.setVisible(true);
	}

}