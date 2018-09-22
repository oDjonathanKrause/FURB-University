import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;


public class PrimeiraVersao {
	static ColorSensor sensorCor = new ColorSensor(SensorPort.S1);
	
	static String direcaoAtual = "RETO";
	static int ANGULO = 35;
	
	// Preto  < 70
	// Branco > 175
	// 
	

	private static boolean isBlack(ColorSensor sensor) {
		System.out.println(sensor.getLightValue());
        return sensor.getLightValue() <= 70;
	}
	
	public static void main(String[] args) {
        Motor.A.setSpeed(400);
        Motor.B.setSpeed(400);
		
		while(true) {
			printarCorAtual();
			
			if(isBlack(sensorCor)) {
				andarReto();
			} else {
				virarEsquerda(1);
				
				if(isBlack(sensorCor)) {
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
	}

	private static void virarEsquerda(int peso) {
		direcaoAtual = "ESQUERDA";
		
        Motor.A.rotate(-ANGULO * peso, true);
        Motor.B.rotate(ANGULO * peso);
	}
	
	private static void virarDireita(int peso) {
		direcaoAtual = "DIREITA";
		Motor.A.rotate(ANGULO * peso, true);
        Motor.B.rotate(-ANGULO * peso);
	}

	private static void printarCorAtual() {
		
	}
	
	
}
