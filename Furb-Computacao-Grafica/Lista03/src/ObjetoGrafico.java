import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class ObjetoGrafico {
	private ArrayList<Ponto4D> listaPontos4D = new ArrayList();
	private float r = 0.0f,g = 0.0f,b = 0.0f;
	private int primitiva = GL.GL_POINTS;
	private BBox bBox;
	private Transform transform;
	private Ponto4D ultimoVertice;

	public ObjetoGrafico(float x, float y) {
		r = 0.0f; g = 0.0f; b = 1.0f;
		Ponto4D ponto = new Ponto4D(x, y, 0, 1);
		ultimoVertice = new Ponto4D(x, y, 0, 1);
		listaPontos4D.add(ponto);
		ponto.setCor(r, g, b);
		listaPontos4D.add(ultimoVertice);
		ultimoVertice.setCor(r, g, b);
	}

	public void inserirVertice(float x, float y) {
		Ponto4D ponto = new Ponto4D(x, y, 0, 1);
		ultimoVertice = new Ponto4D(x, y, 0, 1);
		listaPontos4D.remove(listaPontos4D.size() - 1);
		listaPontos4D.add(ponto);
		ponto.setCor(r, g, b);
		listaPontos4D.add(ultimoVertice);
		ultimoVertice.setCor(r, g, b);
	}
	
	public void ultimoVerticeUpdate(float x, float y) {
		if (primitiva == GL.GL_POINTS) {
			primitiva = GL.GL_LINE_LOOP;
		}
		ultimoVertice.setX(x);
		ultimoVertice.setY(y);
	}
	
	public void finalizaPoligono() {
		primitiva = GL.GL_POLYGON;
	}
	
	public void alteraCor() {
		Random random = new Random();
		r = random.nextFloat();
		g = random.nextFloat();
		b = random.nextFloat();
		ultimoVertice.setCor(r, g, b);
	}
	
	public void alternaPrimitiva() {
		if (primitiva == GL.GL_LINE_LOOP) {
			primitiva = GL.GL_LINE_STRIP;
		} else {
			primitiva = GL.GL_LINE_LOOP;
		}
		
	}

	public void removerVertice() {

	}
	
	public int size() {
		return listaPontos4D.size();
	}

	public void desenhar(GL gl) {
		gl.glBegin(primitiva);
		
		for (Ponto4D pto : listaPontos4D) {
			gl.glColor3d(pto.getR(), pto.getG(), pto.getB());
			gl.glVertex2d(pto.getX(), pto.getY());
		}
		gl.glEnd();
	}

}
