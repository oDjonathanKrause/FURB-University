import javax.media.opengl.GL;

public class Circulo {
	
	
	public Circulo() {
		
	}
	
	public void desenha(GL gl) {
		gl.glBegin(GL.GL_POINTS);

		gl.glColor3f(0.0f, 0.0f, 1.0f);
		for (double i = 0.0; i < 360.0; i += 5) {
			gl.glVertex2d(RetornaX(i, 100), RetornaY(i, 100));
		}

		gl.glEnd();
		
		
	}
	
	
	public double RetornaX(double angulo, double raio) {

		return (raio * Math.cos(Math.PI * angulo / 180.0));
	}

	public double RetornaY(double angulo, double raio) {

		return (raio * Math.sin(Math.PI * angulo / 180.0));

	}
	
	
	
	
}
