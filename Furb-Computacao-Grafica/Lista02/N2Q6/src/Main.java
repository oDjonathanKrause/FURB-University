//Acadêmicos: Djonathan Krause e Rodrigo Orthmann Nielson

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Main implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private float ortho2D_minX = -400.0f, ortho2D_maxX = 400.0f, ortho2D_minY = -400.0f, ortho2D_maxY = 400.0f;
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;

	private Ponto4D pto1 = new Ponto4D(100, -100, 0, 1);
	private Ponto4D pto2 = new Ponto4D(100, 100, 0, 1);
	private Ponto4D pto3 = new Ponto4D(-100, 100, 0, 1);
	private Ponto4D pto4 = new Ponto4D(-100, -100, 0, 1);
	private int ptoAtual = 1, antigoX, antigoY;;
	private ArrayList<Double> ptoX = new ArrayList();
	private ArrayList<Double> ptoY = new ArrayList();

	public void init(GLAutoDrawable drawable) {

		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));

		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	}

	// exibicaoPrincipal
	public void display(GLAutoDrawable arg0) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		glu.gluOrtho2D(ortho2D_minX, ortho2D_maxX, ortho2D_minY, ortho2D_maxY);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		SRU();

		gl.glLineWidth(4.0f);
		gl.glPointSize(10.0F);

		gl.glColor3f(0.0f, 1.0f, 1.0f);
		gl.glBegin(GL.GL_LINE_STRIP);

		gl.glVertex2d(pto1.obterX(), pto1.obterY());
		gl.glVertex2d(pto2.obterX(), pto2.obterY());
		gl.glVertex2d(pto3.obterX(), pto3.obterY());
		gl.glVertex2d(pto4.obterX(), pto4.obterY());

		gl.glEnd();

		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glColor3f(1.0f, 1.0f, 0.0f);

		ptoX.clear();
		ptoY.clear();

		for (float i = 0; i < 100; i++) {
			getPonto(i / 100.0);
		}
		
		ptoX.add(pto4.obterX());
		ptoY.add(pto4.obterY());
		
		for (int i = 0; i < ptoX.size(); i++) {
			gl.glVertex2d(ptoX.get(i), ptoY.get(i));
		}

		gl.glEnd();

		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_POINTS);
		switch (ptoAtual) {
		case 1:
			gl.glVertex2d(pto1.obterX(), pto1.obterY());
			break;

		case 2:
			gl.glVertex2d(pto2.obterX(), pto2.obterY());
			break;

		case 3:
			gl.glVertex2d(pto3.obterX(), pto3.obterY());
			break;

		case 4:
			gl.glVertex2d(pto4.obterX(), pto4.obterY());
			break;

		}

		gl.glEnd();

		gl.glFlush();
	}

	public void getPonto(double t) {
		double xAB = pto1.obterX() + (pto2.obterX() - pto1.obterX()) * t;
		double yAB = pto1.obterY() + (pto2.obterY() - pto1.obterY()) * t;
		double xBC = pto2.obterX() + (pto3.obterX() - pto2.obterX()) * t;
		double yBC = pto2.obterY() + (pto3.obterY() - pto2.obterY()) * t;
		double xCD = pto3.obterX() + (pto4.obterX() - pto3.obterX()) * t;
		double yCD = pto3.obterY() + (pto4.obterY() - pto3.obterY()) * t;

		double xABC = xAB + (xBC - xAB) * t;
		double yABC = yAB + (yBC - yAB) * t;
		double xBCD = xBC + (xCD - xBC) * t;
		double yBCD = yBC + (yCD - yBC) * t;

		double xABCD = xABC + (xBCD - xABC) * t;
		double yABCD = yABC + (yBCD - yABC) * t;

		ptoX.add(xABCD);
		ptoY.add(yABCD);

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_1:
			ptoAtual = 1;
			glDrawable.display();
			break;

		case KeyEvent.VK_2:
			ptoAtual = 2;
			glDrawable.display();
			break;

		case KeyEvent.VK_3:
			ptoAtual = 3;
			glDrawable.display();
			break;

		case KeyEvent.VK_4:
			ptoAtual = 4;
			glDrawable.display();
			break;
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		antigoX = e.getX();
		antigoY = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		int movtoX = e.getX() - antigoX;
		int movtoY = e.getY() - antigoY;

		switch (ptoAtual) {
		case 1:
			pto1.atribuirX(pto1.obterX() + movtoX);
			pto1.atribuirY(pto1.obterY() - movtoY);
			break;

		case 2:
			pto2.atribuirX(pto2.obterX() + movtoX);
			pto2.atribuirY(pto2.obterY() - movtoY);
			break;

		case 3:
			pto3.atribuirX(pto3.obterX() + movtoX);
			pto3.atribuirY(pto3.obterY() - movtoY);
			break;

		case 4:
			pto4.atribuirX(pto4.obterX() + movtoX);
			pto4.atribuirY(pto4.obterY() - movtoY);
			break;
		}
		antigoX = e.getX();
		antigoY = e.getY();

		glDrawable.display();
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void SRU() {

		// eixo x
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glLineWidth(3.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(-200.0f, 0.0f);
		gl.glVertex2f(200.0f, 0.0f);
		gl.glEnd();
		// eixo y
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(0.0f, -200.0f);
		gl.glVertex2f(0.0f, 200.0f);
		gl.glEnd();
	}

	public double RetornaX(double angulo, double raio) {

		return (raio * Math.cos(Math.PI * angulo / 180.0));
	}

	public double RetornaY(double angulo, double raio) {

		return (raio * Math.sin(Math.PI * angulo / 180.0));

	}

}
