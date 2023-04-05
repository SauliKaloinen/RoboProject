package thread;


import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LineFollowerV2 extends Thread {
	DataExchange DEObj;
	
	private int lap;

	private EV3ColorSensor cs;
	private EV3UltrasonicSensor us;

	public LineFollowerV2(DataExchange DE) {
		DEObj = DE;
		
		cs = new EV3ColorSensor(SensorPort.S3);

		float redSample[];
		SensorMode redMode = cs.getRedMode();
		redSample = new float[cs.sampleSize()];
	}
	
	// GITHUB BRANCH TEST.

	public void run() {
		float lower = 0.15f;
		float upper = 0.55f;
		float redSample[];
		SensorMode redMode = cs.getRedMode();
		redSample = new float[cs.sampleSize()];
		RegulatedMotor m1 = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor m2 = new EV3LargeRegulatedMotor(MotorPort.B);

		while (true) {
			redMode.fetchSample(redSample, 0);
			LCD.clear();
			LCD.drawString(String.valueOf(redSample[0]), 1, 3);
			if (DEObj.getCMD() == 1) {
				if (lower <= redSample[0] && redSample[0] <= upper) {
					m1.setSpeed(100);
					m1.forward();
					m2.setSpeed(150);
					m2.forward();

				} else if (redSample[0] < lower) {
					m1.setSpeed(150);
					m1.forward();
					m2.setSpeed(75);
					m2.forward();
				} else if (redSample[0] > upper) {
					m1.setSpeed(75);
					m1.forward();
					m2.setSpeed(150);
					m2.forward();
				}
				LCD.refresh();
			}
			
			else if (lap == 1)
			{
				m1.setSpeed(200);
				m1.backward();
				m2.setSpeed(200);
				m2.backward();
				Delay.msDelay(1000);
				m1.setSpeed(200);
				m1.forward();
				m2.setSpeed(200);
				m2.backward();
				Delay.msDelay(5000);
				System.exit(0);
				
			}
			
			else{
				lap++;
				LCD.drawString("Objects found: " + lap, 0, 1);
				//DEObj.setDodge(true);
				Sound.beep();
				LCD.refresh();
				m2.stop();
				m1.stop();
				Delay.msDelay(2000);
				m1.setSpeed(100);
				m1.forward();
				Delay.msDelay(1000);
				m1.setSpeed(140);
				m1.forward();
				m2.setSpeed(200);
				m2.forward();
				Delay.msDelay(4500);
				m1.setSpeed(100);
				m1.forward();
				m2.setSpeed(90);
				m2.forward();
				Delay.msDelay(2000);
				DEObj.setDodge(false);
				Sound.beepSequence();
			}
			
		}
	}
}