
//Acadêmicos: Djonathan Krause e Rodrigo Orthmann Nielson

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class Main implements GLEventListener, KeyListener {
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;

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

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-400.0f, 400.0f, -400.0f, 400.0f);

		SRU();

		gl.glColor3f(0.0f, 1.0f, 1.0f);
		gl.glLineWidth(3.0f);
		gl.glPointSize(5.0F);
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2d(-100, 100);
		gl.glVertex2d(100, 100);
		gl.glVertex2d(0, -100);
		gl.glEnd();
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINE_LOOP);
		for (int i = 0; i <= 360; i++) {
			gl.glVertex2d(RetornaX(i, 100) - 100, RetornaY(i, 100) + 100);
		}
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		for (int i = 0; i <= 360; i++) {
			gl.glVertex2d(RetornaX(i, 100) + 100, RetornaY(i, 100) + 100);
		}
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		for (int i = 0; i <= 360; i++) {
			gl.glVertex2d(RetornaX(i, 100), RetornaY(i, 100) - 100);
		}
		gl.glEnd();

		gl.glFlush();
	}

	public void keyPressed(KeyEvent e) {

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
