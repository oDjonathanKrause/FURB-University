//Acadêmicos: Djonathan Krause e Rodrigo Orthmann Nielson

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

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
	private int[] primitivas = { GL.GL_POINTS, GL.GL_LINES, GL.GL_LINE_LOOP, GL.GL_LINE_STRIP, GL.GL_TRIANGLES,
			GL.GL_TRIANGLE_FAN, GL.GL_TRIANGLE_STRIP, GL.GL_QUADS, GL.GL_QUAD_STRIP, GL.GL_POLYGON };
	private int cont = 0;

	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		gl.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);

	}

	public void display(GLAutoDrawable arg0) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-400.0f, 400.0f, -400.0f, 400.0f);
		SRU();

		gl.glLineWidth(5.0f);
		gl.glPointSize(5.0F);
		gl.glBegin(primitivas[cont]);

		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glVertex2d(200, -200);

		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glVertex2d(200, 200);

		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glVertex2d(-200, 200);

		gl.glColor3f(1.0f, 0.0f, 1.0f);
		gl.glVertex2d(-200, -200);

		gl.glEnd();
		gl.glFlush();
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			cont++;
			if (cont == 10) {
				cont = 0;
			}
			glDrawable.display();
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


}
