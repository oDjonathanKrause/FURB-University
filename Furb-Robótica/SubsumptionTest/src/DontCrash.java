import lejos.nxt.Motor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;


public class DontCrash implements Behavior {
	UltrasonicSensor sensor;
	
	public DontCrash(UltrasonicSensor sensor) {
		this.sensor = sensor;
	}
	
	public void action() {
		System.out.println("VIRANDO");
		Motor.A.rotate(Main.DEFAULT_ANGLE, true);	
		Motor.B.rotate(-Main.DEFAULT_ANGLE);
	}
	
	public void suppress() { }

	public boolean takeControl() {
		return this.sensor.getDistance() < Main.LIMIT_DIST;
	}
}
