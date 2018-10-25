import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.Motor;

public class Main {
	static char[] ENTRADA = { 'E', 'B', 'E', 'E', 'B', 'E', 'B', 'B', 'D', 'D', 'D', 'D', 'C', 'C' };

	private static char ultimoMovimento = ' ';
	private static int VEL = 400;
	private static int SLEEP = 1450;
	private static int ANGULO = 365;

	public static void main(String[] args) throws InterruptedException {
		Button.ENTER.waitForPress();
		Motor.A.setSpeed(VEL);
		Motor.B.setSpeed(VEL);
		for (char proxMovimento : ENTRADA) {
			switch (proxMovimento) {
			case 'C':
				System.out.println("CIMA");
				cima(ultimoMovimento);
				break;
			case 'B':
				System.out.println("BAIXA");
				baixo(ultimoMovimento);
				break;
			case 'D':
				System.out.println("DIREITA");
				direita(ultimoMovimento);
				break;
			case 'E':
				System.out.println("ESQUERDA");
				esquerda(ultimoMovimento);
				break;
			default:
				break;
			}
			ultimoMovimento = proxMovimento;
		}
	}

	private static void virarDireita() {
		Motor.A.stop(true);
		Motor.B.stop(true);
		Motor.A.rotate(ANGULO + 65);
	}

	private static void virarEsquerda() {
		Motor.A.stop();
		Motor.B.stop();
		Motor.B.rotate(ANGULO);
	}

	private static void forward() {
		Motor.A.forward();
		Motor.B.forward();
		try {
			Thread.sleep(SLEEP);			
		} catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void cima(char ultimoMovimento) {
		switch (ultimoMovimento) {
		case 'C':
			break;
		case 'D':
			virarEsquerda();
			break;
		case 'E':
			virarDireita();
			break;
		case 'B':
			virarDireita();
			virarDireita();
			break;
		default:
			break;
		}
		forward();
	}

	private static void direita(char ultimoMovimento) {
		switch (ultimoMovimento) {
		case 'C':
			virarDireita();
			break;
		case 'D':
			break;
		case 'E':
			virarDireita();
			virarDireita();
			break;
		case 'B':
			virarEsquerda();
			break;
		default:
			break;
		}
		forward();
	}

	private static void esquerda(char ultimoMovimento) {
		switch (ultimoMovimento) {
		case 'C':
			virarEsquerda();
			break;
		case 'D':
			virarDireita();
			virarDireita();
			break;
		case 'E':
			break;
		case 'B':
			virarDireita();
			break;
		default:
			break;
		}
		forward();
	}
	private static void baixo(char ultimoMovimento) {
		switch (ultimoMovimento) {
		case 'C':
			virarDireita();
			virarDireita();
			break;
		case 'D':
			virarDireita();
			break;
		case 'E':
			virarEsquerda();
			break;
		case 'B':
			break;
		default:
			break;
		}
		forward();
	}
}