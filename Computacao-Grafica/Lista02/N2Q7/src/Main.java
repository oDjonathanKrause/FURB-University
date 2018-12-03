
//Acadêmicos: Djonathan Krause e Rodrigo Orthmann Nielson

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private float ortho2D_minX = -400.0f, ortho2D_maxX = 400.0f, ortho2D_minY = -400.0f, ortho2D_maxY = 400.0f;
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;
	private double valorX = 150.0, valorY = 150.0, Xinicial = 0.0, Yinicial = 0.0;
	private int antigoX, antigoY, color = 1;
	private double resultado = 0;
	private double bbx1, bbx2, bbx3, bbx4, bby1, bby2, bby3, bby4;
	private BoundingBox bb;

	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		double x = RetornaX(0, 120) + 150;
		double y = RetornaY(0, 120) + 150;

		resultado = (x - 150) * (x - 150) + (y - 150) * (y - 150);

		bbx1 = RetornaX(45, 120) + 150;
		bby1 = RetornaY(45, 120) + 150;
		bbx2 = RetornaX(135, 120) + 150;
		bby2 = RetornaY(135, 120) + 150;
		bbx3 = RetornaX(225, 120) + 150;
		bby3 = RetornaY(225, 120) + 150;
		bbx4 = RetornaX(315, 120) + 150;
		bby4 = RetornaY(315, 120) + 150;

		bb = new BoundingBox(bbx3, bby4, 0, bbx1, bby1, 0);

	}

	// exibicaoPrincipal
	public void display(GLAutoDrawable arg0) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		glu.gluOrtho2D(ortho2D_minX, ortho2D_maxX, ortho2D_minY, ortho2D_maxY);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		SRU();

		gl.glLineWidth(1.0f);
		gl.glPointSize(6.0F);

		/*
		 * gl.glBegin(GL.GL_LINE_LOOP); gl.glVertex2d(bbx1, bby1);
		 * gl.glVertex2d(bbx2, bby2); gl.glVertex2d(bbx3, bby3);
		 * gl.glVertex2d(bbx4, bby4); gl.glEnd();
		 */

		bb.desenharOpenGLBBox(gl, color);

		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_POINTS);

		gl.glVertex2d(valorX, valorY);

		gl.glEnd();

		gl.glBegin(GL.GL_LINE_LOOP);

		for (int i = 0; i < 360; i += 5) {
			gl.glVertex2d(RetornaX(i, 40) + valorX, RetornaY(i, 40) + valorY);
		}

		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);

		for (int i = 0; i < 360; i += 5) {
			gl.glVertex2d(RetornaX(i, 120) + 150, RetornaY(i, 120) + 150);
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

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// if ((e.getModifiers() & e.BUTTON1_MASK) != 0) {
		antigoX = e.getX();
		antigoY = e.getY();
		Xinicial = valorX;
		Yinicial = valorY;
		// }
	}

	public void mouseReleased(MouseEvent e) {
		valorX = Xinicial;
		valorY = Yinicial;
		color = 1;
		glDrawable.display();

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		int movtoX = e.getX() - antigoX;
		int movtoY = e.getY() - antigoY;

		double x = valorX + movtoX;
		double y = valorY - movtoY;

		if (x >= bb.obterMaiorX() || x <= bb.obterMenorX() || y >= bb.obterMaiorY() || y <= bb.obterMenorY()) {
			color = 2;

			double res = (x - 150) * (x - 150) + (y - 150) * (y - 150);

			if (res <= resultado) {
				valorX += movtoX;
				valorY -= movtoY;

			} else {
				color = 3;
			}
		} else {
			valorX += movtoX;
			valorY -= movtoY;
			color = 1;
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
		gl.glVertex2f(-100.0f, 0.0f);
		gl.glVertex2f(100.0f, 0.0f);
		gl.glEnd();
		// eixo y
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(0.0f, -100.0f);
		gl.glVertex2f(0.0f, 100.0f);
		gl.glEnd();
	}

	public double RetornaX(double angulo, double raio) {

		return (raio * Math.cos(Math.PI * angulo / 180.0));
	}

	public double RetornaY(double angulo, double raio) {

		return (raio * Math.sin(Math.PI * angulo / 180.0));

	}

}
