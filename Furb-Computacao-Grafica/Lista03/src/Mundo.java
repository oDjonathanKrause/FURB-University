import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Mundo implements KeyListener, GLEventListener, MouseListener, MouseMotionListener {
	private ArrayList<ObjetoGrafico> listaObjetosGraficos = new ArrayList();
	private Camera camera;
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;
	private ObjetoGrafico poligonoAtual;

	public void inserirPoligono() {

	}

	public void removerPoligono() {

	}

	// Necessário
	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		gl.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
	}

	// É o que vai aaprecer na tela
	public void display(GLAutoDrawable arg0) {

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-400.0f, 400.0f, -400.0f, 400.0f);

		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glLineWidth(3.0f);
		gl.glPointSize(5.0F);

		desenhaObjetos();

		gl.glFlush();
	}

	public void desenhaObjetos() {
		for (ObjetoGrafico obj : listaObjetosGraficos) {
			obj.desenhar(gl);
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyChar() == KeyEvent.VK_C) {
			poligonoAtual.alteraCor();
		}

		glDrawable.display();
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {

	}


	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);

	}


	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		if (poligonoAtual != null) {
			poligonoAtual.ultimoVerticeUpdate(e.getX() - 391, -e.getY() + 391);
			glDrawable.display();
		}

		// System.out.println("X: " + e.getX() + " Y: " + e.getY());
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			if (poligonoAtual == null) {
				poligonoAtual = new ObjetoGrafico(e.getX() - 391, -e.getY() + 391);
				listaObjetosGraficos.add(poligonoAtual);
				System.out.println(poligonoAtual.size());
			} else {
				poligonoAtual.inserirVertice(e.getX() - 391, -e.getY() + 391);
			}
		} else {
			poligonoAtual.finalizaPoligono();
			poligonoAtual = null;
		}
		// System.out.println("X: " + e.getX() + " Y: " + e.getY());
		
		glDrawable.display();
	}

	public void mouseDragged(MouseEvent e) {

	}

}
