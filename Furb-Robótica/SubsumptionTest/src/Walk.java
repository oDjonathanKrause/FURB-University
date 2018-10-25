import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class Walk implements Behavior {

	/**
	 * Move o robô para frente. Se a velocidade atual do motor A ou B for zero,
	 * Seta para velocidade padrão e move para frente. 
	 */
	public void action() {
		System.out.println("FRENTE");
		Motor.A.forward();
        Motor.B.forward();
	}

	/**
	 * Para os motores A e B do robô sem frear bruscamente.
	 */
	public void suppress() {
		Motor.A.stop();
		Motor.B.stop();
	}

	public boolean takeControl() {
		return true;
	}
}
