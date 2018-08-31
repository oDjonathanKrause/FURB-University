import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;


public class SegueLinha {
	static ColorSensor sensorCor = new ColorSensor(SensorPort.S1);
	
	static int angulo = 20;
	static boolean isReto = true;
	static int velAtual = 400;
	static int countCurva = 0;
	

	private static boolean isBlack() {
        return sensorCor.getLightValue() <= 150;
	}
	
	public static void main(String[] args) {
        Motor.A.setSpeed(velAtual);
        Motor.B.setSpeed(velAtual);
		
		while(true) {
			debug();
			
			if(isBlack()) {
				andarReto();
			} else {
				virarEsquerda(1);
				
				if(isBlack()) {
					andarReto();
				} else {
					virarDireita(2);
					andarReto();
				}
			}
		}		
	}
	
	private static void andarReto() {
		Motor.A.forward();
        Motor.B.forward();
        
        isReto = true;
        velAtual += 50;
	}

	private static void virarEsquerda(int peso) {
		velAtual = 400;
        Motor.A.rotate(-angulo * peso, true);
        Motor.B.rotate(angulo * peso);
	}
	
	private static void virarDireita(int peso) {
		velAtual = 400;
		Motor.A.rotate(angulo * peso, true);
        Motor.B.rotate(-angulo * peso);       
	}

	private static void debug() {
		if(isBlack()) {
			System.out.println(sensorCor.getLightValue());				
		} else {
			System.out.println(sensorCor.getLightValue());
		}
	}
	
	
}
