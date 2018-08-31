package HelloWorld;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class HelloWorld {
	private static final int LIMITE_DISTANCIA_OBSTACULO = 25;
	static UltrasonicSensor sonic;
	static int count = 0;
	
	public static void main(String[] args) throws InterruptedException {
		sonic = new UltrasonicSensor(SensorPort.S1);

		Motor.A.setSpeed(500);
		Motor.B.setSpeed(500);

		while(!Button.ESCAPE.isDown()) {
			vaiPraFrente();
			
			if(achouObstaculo()) {
				mudaDirecao();
			}
		}
		crazy();
	}
	
	public static void vaiPraFrente(){
		Motor.A.forward();
		Motor.B.forward();
	}
	
	public static boolean achouObstaculo() {
		return sonic.getDistance() <= LIMITE_DISTANCIA_OBSTACULO;
	}
	
	public static void mudaDirecao() {
		Motor.A.rotate(345, true);
		Motor.B.rotate(-345);
	}
	
	public static void crazy() throws InterruptedException {
		Motor.A.setSpeed(900);
		Motor.B.setSpeed(900);

		int a = 0;
		
		while(a < 10) {
			Motor.A.rotate(345, true);
			Motor.B.rotate(-345);
			Thread.sleep(50);
			a++;
		}
		
		Motor.A.setSpeed(500);
		Motor.B.setSpeed(500);
	}
}
