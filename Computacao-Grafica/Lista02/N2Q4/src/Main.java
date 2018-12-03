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

public class Main implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private float ortho2D_minX = -400.0f, ortho2D_maxX = 400.0f, ortho2D_minY = -400.0f, ortho2D_maxY = 400.0f;
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;
	private double x1 = 0.0, y1 = 0.0, x2, y2, angulo = 45.0, raio = 100.0, Xinicial = 0.0, Yinicial = 0.0;
	private int antigoX, antigoY = 0;

	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		System.out.println("Espaco de desenho com tamanho: " + drawable.getWidth() + " x " + drawable.getHeight());
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
		gl.glPointSize(7.0F);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);

		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x1 + RetornaX(angulo, raio), y1 + RetornaY(angulo, raio));

		gl.glEnd();

		gl.glBegin(GL.GL_POINTS);

		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x1 + RetornaX(angulo, raio), y1 + RetornaY(angulo, raio));

		gl.glEnd();

		gl.glFlush();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_Q:
			x1 -= 10;
			x2 -= 10;

			glDrawable.display();
			break;
		case KeyEvent.VK_W:
			x1 += 10;
			x2 += 10;

			glDrawable.display();
			break;
		case KeyEvent.VK_A:
			raio -= 10;

			glDrawable.display();
			break;
		case KeyEvent.VK_S:
			raio += 10;

			glDrawable.display();
			break;
		case KeyEvent.VK_Z:
			angulo -= 5;

			glDrawable.display();
			break;
		case KeyEvent.VK_X:
			angulo += 5;

			glDrawable.display();
			break;

		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		System.out.println(" --- reshape ---");
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		System.out.println(" --- displayChanged ---");
	}

	public void keyReleased(KeyEvent arg0) {
		System.out.println(" --- keyReleased ---");
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(" --- keyTyped ---");
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {

		antigoX = e.getX();
		antigoY = e.getY();
		Xinicial = x1;
		Yinicial = y1;

	}

	public void mouseReleased(MouseEvent e) {
		x1 = Xinicial;
		y1 = Yinicial;

		glDrawable.display();

	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("ASIOdhAs");
	}

	public void mouseDragged(MouseEvent e) {
		int movtoX = e.getX() - antigoX;
		int movtoY = e.getY() - antigoY;
		x1 += movtoX;
		y1 -= movtoY;

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
