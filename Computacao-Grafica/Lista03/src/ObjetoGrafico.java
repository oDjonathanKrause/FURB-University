import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class ObjetoGrafico {
	private ArrayList<Ponto4D> listaPontos4D = new ArrayList(); //Lista de pontos do poligono
	private ArrayList<ObjetoGrafico> filhos = new ArrayList(); //Lista de filhos do poligono
	private ObjetoGrafico pai; //Pai do poligono
	private int primitiva = GL.GL_POINTS;
	private int corAtual = 0;
	private float cores[][] = { { 1.0f, 0.0f, 0.0f }, { 0.0f, 1.0f, 0.0f }, { 0.0f, 0.0f, 1.0f } };
	private BBox bBox;
	private int desenharBbox = 0; //Variavel que indica se sera desenhada BBox
	private Ponto4D ultimoVertice, verticeSelecionado;

	private Transformacao4D matrizObjeto = new Transformacao4D();
	/// Matrizes temporarias que sempre sao inicializadas com matriz Identidade
	/// entao podem ser "static".
	private static Transformacao4D matrizTmpTranslacao = new Transformacao4D();
	private static Transformacao4D matrizTmpTranslacaoInversa = new Transformacao4D();
	private static Transformacao4D matrizTmpEscala = new Transformacao4D();
	// private static Transformacao4D matrizTmpRotacaoZ = new Transformacao4D();
	private static Transformacao4D matrizGlobal = new Transformacao4D();

	//Construtor
	public ObjetoGrafico(float x, float y) {
		Ponto4D ponto = new Ponto4D(x, y, 0, 1);
		ultimoVertice = new Ponto4D(x, y, 0, 1);
		listaPontos4D.add(ponto);
		listaPontos4D.add(ultimoVertice);
	}

	//Adiciona vértice ao poligono
	public void inserirVertice(float x, float y) {
		Ponto4D ponto = new Ponto4D(x, y, 0, 1);
		ultimoVertice = new Ponto4D(x, y, 0, 1);
		listaPontos4D.remove(listaPontos4D.size() - 1);
		listaPontos4D.add(ponto);
		listaPontos4D.add(ultimoVertice);
	}

	//Adiciona filho ao poligono
	public void adicionaFilho(ObjetoGrafico objeto) {
		objeto.setPai(this);
		this.filhos.add(objeto);
	}
	
	//Retorna o pai do poligono
	public ObjetoGrafico getPai() {
		return this.pai;
	}
	
	//Remove determinado filho
	public void removeFilho(ObjetoGrafico filho) {
		this.filhos.remove(filho);
	}
	
	//Define o pai
	public void setPai(ObjetoGrafico obj) {
		this.pai = obj;
	}

	//Atualiza a bbox
	public void atualizaBBox() {
		bBox.resetBBox();
		System.out.println("\n\n");
		for (Ponto4D pto : listaPontos4D) {
			this.bBox.atualizarBBox(pto);
		}
	}
	
	//Finaliza o poligono, cria a bbox
	public void finalizaPoligono() {
		this.bBox = new BBox();
		bBox.resetBBox();
		atualizaBBox();
	}

	//Desenha bbox
	public void desenharBBox() {
		if (desenharBbox == 0) {
			desenharBbox = 1;
		} else {
			this.desenharBbox = 0;
		}
	}

	//Desenha bbox com aparencia de pai
	public void desenharBBoxPai() {
		if (desenharBbox == 2) {
			desenharBbox = 0;
		} else {
			desenharBbox = 2;
		}
	}
	
	//Remove vertice sleecionado
	public void removeVerticeSelecionado() {
		listaPontos4D.remove(verticeSelecionado);
		atualizaBBox();
	}

	//Atualiza x e y do ponto selecionado
	public void verticeSelecionadoUpdate(int x, int y) {
		verticeSelecionado.setX(x);
		verticeSelecionado.setY(y);
	}

	//Atualiza o ponto do ultimo vertice (vertice na hora da criação)
	public void ultimoVerticeUpdate(float x, float y) {
		if (primitiva == GL.GL_POINTS) {
			primitiva = GL.GL_LINE_LOOP;
		}
		ultimoVertice.setX(x);
		ultimoVertice.setY(y);
	}

	//Altera a cor
	public void alteraCor() {
		corAtual++;
		if (corAtual > 2) {
			corAtual = 0;
		}
	}

	//Processa o filho (para seleção atraves da scanline)
	public ObjetoGrafico processaFilho(int x, int y) {
		for (ObjetoGrafico filho : filhos) {
			if (filho.pontosCentroBBox(x, y)) {
				if (filho.processaScanLine(x, y)) {
					return filho;
				}
			}
			ObjetoGrafico neto = filho.processaFilho(x, y);
			if (neto != null) {
				return neto;
			}
		}
		return null;
	}

	//Processa a scanline
	public boolean processaScanLine(int x, int y) {
		int countIntersec = 0;
		for (int i = 0; i < listaPontos4D.size() - 1; i++) {
			Ponto4D pto1 = listaPontos4D.get(i);
			Ponto4D pto2 = listaPontos4D.get(i + 1);

			double t = (y - pto1.getY()) / (pto2.getY() - pto1.getY());
			double xt = pto1.getX() + ((pto2.getX() - pto1.getX()) * t);

			if (xt < x && t > 0 && t < 1) {
				countIntersec++;
			}
		}

		Ponto4D pto1 = listaPontos4D.get(listaPontos4D.size() - 1);
		Ponto4D pto2 = listaPontos4D.get(0);

		double t = (y - pto1.getY()) / (pto2.getY() - pto1.getY());
		double xt = pto1.getX() + ((pto2.getX() - pto1.getX()) * t);

		if (xt < x && t > 0 && t < 1) {
			countIntersec++;
		}
		if (countIntersec % 2 == 0) {
			return false;
		}
		return true;
	}

	//Verifica se o click está dentro da bbox do poligono
	public boolean pontosCentroBBox(int x, int y) {
		if (x < bBox.obterMaiorX() && y < bBox.obterMaiorY() && x > bBox.obterMenorX() && y > bBox.obterMenorY()) {
			return true;
		}
		return false;
	}

	public Ponto4D getCentroBBox() {
		this.bBox.processarCentroBBox();
		Ponto4D pto = bBox.obterCentro();
		return bBox.obterCentro();
	}

	//Altera a primitiva
	public void alternaPrimitiva() {
		if (primitiva == GL.GL_LINE_LOOP) {
			primitiva = GL.GL_LINE_STRIP;
			System.out.println("Alterada primitiva para LINE STRIP");
		} else {
			primitiva = GL.GL_LINE_LOOP;
			System.out.println("Alterada primitiva para LINE LOOP");
		}

	}

	public int size() {
		return listaPontos4D.size();
	}

	public void desenhar(GL gl) {
		gl.glPushMatrix();
		gl.glMultMatrixd(matrizObjeto.GetDate(), 0);

		
		gl.glBegin(primitiva);

		for (Ponto4D pto : listaPontos4D) {
			gl.glColor3d(cores[corAtual][0], cores[corAtual][1], cores[corAtual][2]);
			gl.glVertex2d(pto.getX(), pto.getY());
		}
		gl.glEnd();

		if (desenharBbox == 1) {
			this.bBox.desenharOpenGLBBox(gl, 1);
		} else if (desenharBbox == 2) {
			this.bBox.desenharOpenGLBBox(gl, 0);
		}

		for (ObjetoGrafico obj : filhos) {
			obj.desenhar(gl);
		}

		gl.glPopMatrix();
		
		if(verticeSelecionado != null) {
			gl.glBegin(GL.GL_POINTS);
			gl.glColor3f(0.5f, 0.0f, 0.9f);
			gl.glVertex2d(verticeSelecionado.getX(), verticeSelecionado.getY());
			gl.glEnd();
		}
		
	}
	
	

	public void translacaoXYZ(double tx, double ty, double tz) {
		Transformacao4D matrizTranslate = new Transformacao4D();
		matrizTranslate.atribuirTranslacao(tx, ty, tz);
		matrizObjeto = matrizTranslate.transformMatrix(matrizObjeto);
	}

	public void escalaXYZ(double Sx, double Sy) {
		Transformacao4D matrizScale = new Transformacao4D();
		matrizScale.atribuirEscala(Sx, Sy, 1.0);
		matrizObjeto = matrizScale.transformMatrix(matrizObjeto);
	}

	public void atribuirIdentidade() {
		// anguloGlobal = 0.0;
		matrizObjeto.atribuirIdentidade();
	}

	//Procura ao vertice com menor distancia em relacao ao clic
	public Ponto4D procuraVertice(int x, int y) {
		double menorDistancia = Double.MAX_VALUE;
		Ponto4D pontoMenorDistancia = null;
		for (Ponto4D pto : listaPontos4D) {
			double dist = Math.pow((x - pto.getX()),2) + Math.pow((y - pto.getY()), 2);
			if (dist < menorDistancia) {
				menorDistancia = dist;
				pontoMenorDistancia = pto;
			}
		}
		verticeSelecionado = pontoMenorDistancia;
		return verticeSelecionado;
	}

	public void escalaXYZPtoFixo(double escala, Ponto4D ptoFixo) {
		ptoFixo.inverterSinal(ptoFixo);
		matrizGlobal.atribuirIdentidade();

		matrizTmpTranslacao.atribuirTranslacao(ptoFixo.getX(), ptoFixo.getY(), ptoFixo.getZ());
		matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

		matrizTmpEscala.atribuirEscala(escala, escala, 1.0);
		matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

		ptoFixo.inverterSinal(ptoFixo);
		matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.getX(), ptoFixo.getY(), ptoFixo.getZ());
		matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);

		matrizObjeto = matrizObjeto.transformMatrix(matrizGlobal);
	}

	public void rotacaoZPtoFixo(double angulo, Ponto4D ptoFixo) {
		ptoFixo.inverterSinal(ptoFixo);
		matrizGlobal.atribuirIdentidade();

		matrizTmpTranslacao.atribuirTranslacao(ptoFixo.getX(), ptoFixo.getY(), ptoFixo.getZ());
		matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

		matrizTmpEscala.atribuirRotacaoZ(Transformacao4D.DEG_TO_RAD * angulo);
		matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

		ptoFixo.inverterSinal(ptoFixo);
		matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.getX(), ptoFixo.getY(), ptoFixo.getZ());
		matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);

		matrizObjeto = matrizObjeto.transformMatrix(matrizGlobal);
	}

}
