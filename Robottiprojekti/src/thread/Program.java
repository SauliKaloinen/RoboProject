package thread;
//github testi
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import thread.DataExchange;
import thread.LineFollowerV2;
//import thread.ObstacleDetector;

public class Program {
	private static ObstacleDetector OB;
	private static DataExchange DE;
	private static LineFollowerV2 LFObj;
	// private static ObstacleDetector ODObj;

	public static void main(String[] args) {
		DE = new DataExchange();
		// ODObj = new ObstacleDetector(DE);
		LFObj = new LineFollowerV2(DE);
		OB = new ObstacleDetector(DE);
		// ODObj.start();
		OB.start();
		LFObj.start();

		while (!Button.ESCAPE.isDown()) {

		}
		LCD.drawString("Finished", 0, 7);
		LCD.refresh();
		System.exit(0);
	}

}