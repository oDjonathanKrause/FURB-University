import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;


public class SegueLinhaPID {
    
    private static final int  VELOCIDADE_BASE = 400;
    private static final int  QTD_LEITURAS_AO_CALIBRAR = 450;
    
    private static final int  MAX_VEL = 800;
	
    private static ColorSensor sensor = new ColorSensor(SensorPort.S1);
    private static float escuro = 0;
    private static float claro = 0;
	
	public static void main(String[] args) throws InterruptedException {
        SegueLinhaPID segueLinha = new SegueLinhaPID();

        segueLinha.calibrar();
        int mediaDesejada = segueLinha.calcMedia();
        int Kp = 5;
        int Kd = 5;
        int curva, derivada = 0, proporcional = 0;
        int ultimoErro = 0, erro, leituraAtual, esquerda, direita;
        
        System.out.println("CLARO: " + claro);
        System.out.println("ESCURO: " + escuro);
        System.out.println("MEDIA: " + mediaDesejada);
        Button.ENTER.waitForPress();
		
		while(true) {
            leituraAtual = sensor.getLightValue(); 
            
            erro = leituraAtual - mediaDesejada;

            proporcional = erro;
            derivada = erro - ultimoErro;
            
            ultimoErro = erro;
            curva = Kp*proporcional + Kd*derivada;
            
            esquerda = VELOCIDADE_BASE + curva > MAX_VEL ? MAX_VEL : VELOCIDADE_BASE + curva;
            direita = VELOCIDADE_BASE - curva < -MAX_VEL ? -MAX_VEL : VELOCIDADE_BASE - curva;
                    
            Motor.A.setSpeed(esquerda);
            Motor.B.setSpeed(direita);			

            Motor.A.forward();
            Motor.B.forward();
        }
    }
    
    public int calcMedia() {
        if(this.escuro == 0 || this.claro == 0) {
            calibrar();
        }

        return (int) (this.claro + this.escuro) / 2;   
    }

    /**
     * 
     */
    public void calibrar() {
        // Calcula o valor do escuro
        System.out.println("Ler Escuro");
        
        // Aguarda o usuário pressionar o btn
        Button.ENTER.waitForPress(); 
        
        // Faz calibração no NXT
        sensor.calibrateLow(); 
        
        // Le o valor n vezes e soma no atributo "escuro"
        for (int i = 0; i < QTD_LEITURAS_AO_CALIBRAR; i++) {
            this.escuro += sensor.getLightValue();
        }
        
        // Calcula o claro
        System.out.println("Ler Claro");
        Button.ENTER.waitForPress();
        sensor.calibrateHigh();
        for (int i = 0; i < QTD_LEITURAS_AO_CALIBRAR; i++) {
            this.claro += sensor.getLightValue();
        }
        
        this.escuro = this.escuro / QTD_LEITURAS_AO_CALIBRAR;
        this.claro = (float) ((this.claro / QTD_LEITURAS_AO_CALIBRAR) * 0.7);

    }
}
