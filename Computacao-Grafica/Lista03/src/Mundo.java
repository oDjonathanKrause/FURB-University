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
	private ObjetoGrafico poligonoSelecionado;
	private ObjetoGrafico poligonoPai;
	private Ponto4D verticeSelecionado;
	private boolean desenho = true; //
	private boolean filho = false;

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

		desenhaSRU();
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glLineWidth(3.0f);
		gl.glPointSize(5.0F);

		desenhaObjetos();

		gl.glFlush();
	}

	// Chama o metodo para desenhar objeto de cada poligono
	public void desenhaObjetos() {
		for (ObjetoGrafico obj : listaObjetosGraficos) {
			obj.desenhar(gl);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_C: // Altera a cor
			if (poligonoSelecionado != null) {
				poligonoSelecionado.alteraCor();
			}
			break;
		case KeyEvent.VK_P: // Altera a primitiva
			if (poligonoAtual != null) {
				poligonoAtual.alternaPrimitiva();
			}
			break;
		case KeyEvent.VK_RIGHT: // Move poligono para direita
			if (poligonoSelecionado != null)
				poligonoSelecionado.translacaoXYZ(2.0, 0.0, 0.0);
			break;
		case KeyEvent.VK_LEFT: // Move para esquerda
			if (poligonoSelecionado != null)
				poligonoSelecionado.translacaoXYZ(-2.0, 0.0, 0.0);
			break;
		case KeyEvent.VK_UP: // Move para cima
			if (poligonoSelecionado != null)
				poligonoSelecionado.translacaoXYZ(0.0, 2.0, 0.0);
			break;
		case KeyEvent.VK_DOWN: // Move para baixo
			if (poligonoSelecionado != null)
				poligonoSelecionado.translacaoXYZ(0.0, -2.0, 0.0);
			break;
		case KeyEvent.VK_1: // Diminui tamanho
			if (poligonoSelecionado != null)
				poligonoSelecionado.escalaXYZPtoFixo(0.5, poligonoSelecionado.getCentroBBox());
			break;
		case KeyEvent.VK_2: // Aumenta tamanho
			if (poligonoSelecionado != null)
				poligonoSelecionado.escalaXYZPtoFixo(2.0, poligonoSelecionado.getCentroBBox());
			break;
		case KeyEvent.VK_3:// Rotaciona para a esquerda
			if (poligonoSelecionado != null)
				poligonoSelecionado.rotacaoZPtoFixo(10.0, poligonoSelecionado.getCentroBBox());
			break;
		case KeyEvent.VK_4: // Rotaciona direita
			if (poligonoSelecionado != null)
				poligonoSelecionado.rotacaoZPtoFixo(-10.0, poligonoSelecionado.getCentroBBox());
			break;
		case KeyEvent.VK_R: // Remove o poligono selecionado
			if (poligonoSelecionado.getPai() == null) {
				listaObjetosGraficos.remove(poligonoSelecionado);
			} else {
				ObjetoGrafico pai = poligonoSelecionado.getPai();
				pai.removeFilho(poligonoSelecionado);
			}
			break;
		case KeyEvent.VK_F: // Seta poligono como pai, para desenhar seus filhos
			if (!filho) {
				poligonoPai = poligonoSelecionado;
				poligonoPai.desenharBBoxPai();
				poligonoSelecionado = null;
			} else {
				poligonoPai.desenharBBoxPai();
				poligonoPai = null;
			}
			filho = !filho;
			break;
		case KeyEvent.VK_V: // Remove o vertice selecionado
			if (verticeSelecionado != null) {
				verticeSelecionado = null;
				poligonoSelecionado.removeVerticeSelecionado();
			}
			glDrawable.display();
			break;
		case KeyEvent.VK_M: // Altera o modo (edicao, manipulacao)
			desenho = !desenho;
			break;
		}
		glDrawable.display();

	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Deixa o rastro enquanto move o mouse
		if (poligonoAtual != null) {
			poligonoAtual.ultimoVerticeUpdate(e.getX() - 391, -e.getY() + 391);
			glDrawable.display();
		}

		// System.out.println("X: " + e.getX() + " Y: " + e.getY());
	}

	// Scanline, retorna o objeto se detectado
	public ObjetoGrafico poligonoScanline(int x, int y) {
		for (ObjetoGrafico obj : listaObjetosGraficos) {
			if (obj.pontosCentroBBox(x, y)) {
				if (obj.processaScanLine(x, y)) {
					return obj;
				}
			}
			ObjetoGrafico filho = obj.processaFilho(x, y);
			if (filho != null) {
				return filho;
			}
		}
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		verticeSelecionado = null;

	}

	public void mousePressed(MouseEvent e) {
		if (desenho) { // Crialão de poligonos
			if (e.getButton() == 1) { // Click esquerdo
				if (poligonoAtual == null) {
					poligonoAtual = new ObjetoGrafico(e.getX() - 391, -e.getY() + 391);
					if (filho) {
						poligonoPai.adicionaFilho(poligonoAtual);
					} else {
						listaObjetosGraficos.add(poligonoAtual);
					}
					// System.out.println(poligonoAtual.size());
				} else {
					poligonoAtual.inserirVertice(e.getX() - 391, -e.getY() + 391);
				}
			} else { // Click direito finaliza o poligono
				if (poligonoSelecionado != null) {
					poligonoSelecionado.desenharBBox();
				}
				poligonoSelecionado = poligonoAtual;
				if (poligonoSelecionado != null) {
					poligonoSelecionado.desenharBBox();
				}
				poligonoAtual.finalizaPoligono();
				poligonoAtual = null;

			}
		} else { // Manipulação de vertices e poligonos
			if (e.getButton() == 1) { // Click esquerdo -> selecionar poligonos
				if (poligonoSelecionado != null) {
					poligonoSelecionado.desenharBBox();
				}
				poligonoSelecionado = poligonoScanline(e.getX() - 391, -e.getY() + 391);
				if (poligonoSelecionado != null) {
					poligonoSelecionado.desenharBBox();
				}
			} else { // Direito -> modificar vertices
				if (poligonoSelecionado != null) {
					verticeSelecionado = poligonoSelecionado.procuraVertice(e.getX() - 391, -e.getY() + 391);
					System.out.println(verticeSelecionado);
				}
			}
		}
		// System.out.println("X: " + e.getX() + " Y: " + e.getY());

		glDrawable.display();

	}

	public void desenhaSRU() {
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(-200.0f, 0.0f);
		gl.glVertex2f(200.0f, 0.0f);
		gl.glEnd();
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex2f(0.0f, -200.0f);
		gl.glVertex2f(0.0f, 200.0f);
		gl.glEnd();
	}

	public void mouseDragged(MouseEvent e) {
		// Mover vertice de poligono
		if (verticeSelecionado != null) {
			poligonoSelecionado.verticeSelecionadoUpdate(e.getX() - 391, -e.getY() + 391);
			poligonoSelecionado.atualizaBBox();
		}
		glDrawable.display();
	}

}
