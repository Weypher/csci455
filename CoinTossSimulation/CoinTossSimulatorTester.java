public class CoinTossSimulatorTester {
	private int expTimes;
	private CoinTossSimulator simulator;
	
	public CoinTossSimulatorTester() {
		expTimes = 0;
		simulator = new CoinTossSimulator();
	}
	
	private void testConst() {
		System.out.println("After constructors:");
		testSum();
	}
	
	private void testRun(int runTimes) {
		simulator.run(runTimes);
		if (runTimes <= 0) return;
		else if (runTimes > Integer.MAX_VALUE - simulator.getNumTrials()) return;
		expTimes += runTimes;
		System.out.println("After run("+ runTimes +"):");
		testSum();
	}
	
	private void testReset(){
		simulator.reset();
		expTimes = 0;
		System.out.println("After reset:");
		testSum();
	}	
	
	private void testSum() {
		System.out.println("Number of trials [exp:" + simulator.getNumTrials() + "]:" + expTimes);
		System.out.println("Two-head tosses: " + simulator.getTwoHeads());
		System.out.println("Two-tail tosses: "+ simulator.getTwoTails());
		System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
		System.out.println("Tosses add up correctly?" + (simulator.getNumTrials() == expTimes));
	}
	
	public void runIt() {
		System.out.println("Construct the methods.");
		testConst();
		
		System.out.println("\nCheck the negative input, such as run(-1)");
		testRun(-1);
		
		System.out.println("\nCheck the normal input, such as run(10)");
		testRun(10);
		
		System.out.println("\nCheck the normal input, such as run(1000)");
		testRun(1000);
		
		System.out.println("\nCheck the zore input in this state, run(0)");
		testRun(0);
		
		System.out.println("\nCheck the overflow input, such as run(Integer.MAX_VALUE)");
		testRun(Integer.MAX_VALUE);
		
		System.out.println("\nCheck the reset function");
		testReset();

		System.out.println("\nCheck another normal number after reset, such as run(20000)");
		testRun(20000);
		
	}
	
	public static void main(String[] args) {
		CoinTossSimulatorTester test = new CoinTossSimulatorTester();
		test.runIt();
	}
	
}
