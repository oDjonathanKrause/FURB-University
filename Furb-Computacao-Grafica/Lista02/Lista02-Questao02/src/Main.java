
/// \file Exemplo_N2_Jogl_Eclipse.java
/// \brief Exemplo_N2_Jogl_Eclipse: desenha uma linha na diagonal.
/// \version $Revision: 1.0 $
/// \author Dalton Reis.
/// \date 03/05/13.
/// Obs.: variaveis globais foram usadas por questoes didaticas mas nao sao recomendas para aplicacoes reais.

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class Main implements GLEventListener, KeyListener
{
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;
	private float xmin = -400.0f, xmax = 400.0f, ymin = -400.0f, ymax = 400.0f;

	public void init(GLAutoDrawable drawable)
	{
		System.out.println(" --- init ---");
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));
		System.out.println("Espaco de desenho com tamanho: " + drawable.getWidth() + " x " + drawable.getHeight());

		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
	}

	// exibicaoPrincipal
	public void display(GLAutoDrawable arg0)
	{
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluOrtho2D(xmin, xmax, ymin, ymax);
		/*
		 * Mexer no desenho, Aumentar os valores e manter a proporção = zoom out
		 * Diminuir os valores e manter a proporção = zoom in Alterar os valores
		 * e manter a diferença (800) = mover para os lados/cima/baixo
		 */

		SRU();

		// Desenho ...
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glLineWidth(3.0f);
		gl.glPointSize(5.0F);
		gl.glBegin(GL.GL_POINTS);

		gl.glColor3f(0.0f, 0.0f, 1.0f);
		for (double i = 0.0; i < 360.0; i += 5)
		{
			gl.glVertex2d(RetornaX(i, 100), RetornaY(i, 100));

		}

		gl.glEnd();

		gl.glFlush();
	}

	public void keyPressed(KeyEvent e)
	{
		// System.out.println(" --- keyPressed ---");
		switch (e.getKeyCode())
		{
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
		System.out.println(" --- reshape ---");
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);

	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2)
	{
		System.out.println(" --- displayChanged ---");
	}

	
	// Eventos de Zoom e Movimento
	public void keyReleased(KeyEvent e)
	{
		System.out.println("xmax: " + xmax);
		System.out.println(" ymax: " + ymax);
		
		

		switch (e.getKeyCode())
		{
		case KeyEvent.VK_I: // zoom in
			if (xmax >= 200 && ymax >= 200)
			{
				xmin += 10;
				xmax -= 10;
				ymin += 10;
				ymax -= 10;

				glDrawable.display();
			} else
			{
				System.out.println("Limite de zoom in " + xmax);
			}
			break;

		case KeyEvent.VK_O: // zoom out
			if (xmax <= 600 && ymax <= 600)
			{
				xmin -= 10;
				xmax += 10;
				ymin -= 10;
				ymax += 10;

				glDrawable.display();
			} else
			{
				System.out.println("Limite de zoom out " + xmax);
			}
			break;

		case KeyEvent.VK_E: // move esquerda
			if (xmin < 0 && xmax < 800)
			{
				xmin += 10;
				xmax += 10;

				glDrawable.display();
			} else
			{
				System.out.println("Limite de movimento para esquerda " + xmax);
			}
			break;

		case KeyEvent.VK_D: // move direita
			if (xmax > 0)
			{
				xmin -= 10;
				xmax -= 10;

				glDrawable.display();
			} else
			{
				System.out.println("Limite de movimento para direita " + xmax);
			}
			break;

		case KeyEvent.VK_B: // move baixo
			if (ymax < 800)
			{
				ymin += 10;
				ymax += 10;

				glDrawable.display();
			}
			break;

		case KeyEvent.VK_C: // move cima
			if (ymax > 0)
			{
				ymin -= 10;
				ymax -= 10;

				glDrawable.display();
			}
			break;

		case KeyEvent.VK_UP: //super zoom
			for (int i = 0; i < 10000; i++)
			{
				if(xmax <= 10000)
				{	
					xmin -= 10;
					xmax += 10;
					ymin -= 10;
					ymax += 10;
				
					glDrawable.display();
				}
			}

		}

	}

	public double RetornaX(double angulo, double raio)
	{

		return (raio * Math.cos(Math.PI * angulo / 180.0));
	}

	public double RetornaY(double angulo, double raio)
	{

		return (raio * Math.sin(Math.PI * angulo / 180.0));

	}

	public void keyTyped(KeyEvent arg0)
	{
		// System.out.println(" --- keyTyped ---");
	}

	public void SRU()
	{
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
