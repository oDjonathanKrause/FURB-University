import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class Main {
	
	public static final int DEFAULT_SPEED = 600;
	public static final int DEFAULT_ANGLE = 225;
	public static final int LIMIT_DIST = 15;
	
	public static void main(String[] args) {
		UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S4);
		Motor.A.setSpeed(Main.DEFAULT_SPEED);
		Motor.B.setSpeed(Main.DEFAULT_SPEED);
		
		Behavior walk = new Walk();
		Behavior dontCrash = new DontCrash(sensor);
		Behavior[] behaviors = { walk, dontCrash };
		
		Arbitrator arb = new Arbitrator(behaviors);
		
		System.out.println("PRESSIONE ENTER");
        Button.ENTER.waitForPress();
		arb.start();
	}

}
