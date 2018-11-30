import lejos.nxt.Motor;

/**
 * Motor A = y. 4 Steps
 * Motor B = x. 8 Steps.
 * Motor C = z
 */
public class Bot {
	public static final char MOTOR_Ay = 'A';
	public static final char MOTOR_Bx = 'B';
	public static final char MOTOR_Cz = 'C';
	
	public static final int MOTOR_Ay_STEP_SIZE = 5;
	public static final int MOTOR_Bx_STEP_SIZE = 6;
	public static final int MOTOR_Cz_STEP_SIZE = 45;
	
	public static final int MOTOR_Ay_SPEED = 200;
	public static final int MOTOR_Bx_SPEED = 200;
	public static final int MOTOR_Cz_SPEED = 200;
	
	public Bot() { 	
		Motor.A.setSpeed(MOTOR_Ay_SPEED);
		Motor.B.setSpeed(MOTOR_Bx_SPEED);
		Motor.C.setSpeed(MOTOR_Cz_SPEED);
	}
	
	public void stepForward(char motor) {
		if (motor == 'A') {
			Motor.A.rotate(MOTOR_Ay_STEP_SIZE);
		} else if (motor == 'B') {
			Motor.B.rotate(MOTOR_Bx_STEP_SIZE);
		} else if (motor == 'C') {
			Motor.C.rotate(MOTOR_Cz_STEP_SIZE);
		}
	}
	
	public void stepBackward(char motor) {
		if (motor == 'A') {
			Motor.A.rotate(-MOTOR_Ay_STEP_SIZE);
		} else if (motor == 'B') {
			Motor.B.rotate(-MOTOR_Bx_STEP_SIZE);
		} else if (motor == 'C') {
			Motor.C.rotate(-MOTOR_Cz_STEP_SIZE);
		}
	}
	
	public void stop() {
		Motor.A.stop();
		Motor.B.stop();
		Motor.C.stop();
	}
	
	public void print() {
		this.stepBackward(MOTOR_Cz);
		this.stepForward(MOTOR_Cz);
	}
	
	public void printContent(int[][] content) {
		// Percorre coluna por coluna
		for (int y = 0; y < content.length; y++) {
			// Se for par, printa da esquerda pra direita, senão da esquerda pra direita
			if (y % 2 == 0) {
				printRight(content, y);
			} else {
				printLeft(content, y);
			}

			// Vai para a próxima linha
			this.stepBackward(MOTOR_Ay);
		}
	}
	
	public void printRight(int[][] content, int y) {
		// Percorre toda a linha da direita para esquerda
		for (int x = 0; x < content[0].length; x++) {
			// Se for 1, move o Z para printar
			if (content[y][x] == 1) {
				this.print();
			}
			
			// Da um passo para frente avançando uma casa no X
			this.stepForward(MOTOR_Bx);
		}
	}
	
	public void printLeft(int[][] content, int y) {
		// Percorre toda a linha atual indo da esqueda para direita
		for (int x = content[0].length - 1; x >= 0; x--) {
			// Se for valor for 1, move o motor Z para printar
			if (content[y][x] == 1) {
				this.print();
			}
			
			// Da um passo voltando uma casa no X
			this.stepBackward(MOTOR_Bx);
		}
	}
}











