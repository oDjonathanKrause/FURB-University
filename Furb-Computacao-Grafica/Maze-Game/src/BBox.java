import javax.media.opengl.GL;

public final class BBox {
	private double menorX;
	private double menorY;
	private double menorZ;
	private double maiorX;
	private double maiorY;
	private double maiorZ;
	private Ponto4D centro = new Ponto4D();
	// private Cor cor;

	public BBox() {
		
	}

	public BBox(double smallerX, double smallerY, double smallerZ, double greaterX, double greaterY, double greaterZ) {
		this.menorX = smallerX;
		this.menorY = smallerY;
		this.menorZ = smallerZ;
		this.maiorX = greaterX;
		this.maiorY = greaterY;
		this.maiorZ = greaterZ;
	}

	public void atribuirBoundingBox(double smallerX, double smallerY, double smallerZ, double greaterX, double greaterY,
		double greaterZ) {
		this.menorX = smallerX;
		this.menorY = smallerY;
		this.menorZ = smallerZ;
		this.maiorX = greaterX;
		this.maiorY = greaterY;
		this.maiorZ = greaterZ;
		processarCentroBBox();
	}

	public void atualizarBBox(Ponto4D point) {
		atualizarBBox(point.getX(), point.getY(), point.getZ());
	}

	public void resetBBox() {
		this.menorX = Double.MAX_VALUE;
		this.menorY = Double.MAX_VALUE;
		this.menorZ = Double.MAX_VALUE;
		this.maiorX = -Double.MAX_VALUE;
		this.maiorY = -Double.MAX_VALUE;
		this.maiorZ = -Double.MAX_VALUE;
	}
	
	public void atualizarBBox(double x, double y, double z) {
		
		if (x > maiorX) {
			maiorX = x;
		}
		if (x < menorX) {
			menorX = x;
		}
		if (y > maiorY) {
			maiorY = y;
		}
		if (y < menorY) {
			menorY = y;
		}
		if (z > maiorZ) {
			maiorZ = z;
		}
		if (z < menorZ) {
			menorZ = z;
		}

	}

	public void processarCentroBBox() {
		centro.setX((maiorX + menorX) / 2);
		centro.setY((maiorY + menorY) / 2);
		centro.setZ((maiorZ + menorZ) / 2);
	}

	public void desenharOpenGLBBox(GL gl, int color) {
		if (color == 1) {
			gl.glColor3f(0.4f, 0.5f, 0.6f);
		} else {
			gl.glColor3f(0.8f, 0.2f, 0.5f);
		}

		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex3d(menorX, maiorY, menorZ);
		gl.glVertex3d(maiorX, maiorY, menorZ);
		gl.glVertex3d(maiorX, menorY, menorZ);
		gl.glVertex3d(menorX, menorY, menorZ);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex3d(menorX, menorY, menorZ);
		gl.glVertex3d(menorX, menorY, maiorZ);
		gl.glVertex3d(menorX, maiorY, maiorZ);
		gl.glVertex3d(menorX, maiorY, menorZ);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex3d(maiorX, maiorY, maiorZ);
		gl.glVertex3d(menorX, maiorY, maiorZ);
		gl.glVertex3d(menorX, menorY, maiorZ);
		gl.glVertex3d(maiorX, menorY, maiorZ);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex3d(maiorX, menorY, menorZ);
		gl.glVertex3d(maiorX, maiorY, menorZ);
		gl.glVertex3d(maiorX, maiorY, maiorZ);
		gl.glVertex3d(maiorX, menorY, maiorZ);
		gl.glEnd();
	}

	// gets e sets
	/// Obter menor valor X da BBox.
	public double obterMenorX() {
		return menorX;
	}

	/// Obter menor valor Y da BBox.
	public double obterMenorY() {
		return menorY;
	}

	/// Obter menor valor Z da BBox.
	public double obterMenorZ() {
		return menorZ;
	}

	/// Obter maior valor X da BBox.
	public double obterMaiorX() {
		return maiorX;
	}

	/// Obter maior valor Y da BBox.
	public double obterMaiorY() {
		return maiorY;
	}

	/// Obter maior valor Z da BBox.
	public double obterMaiorZ() {
		return maiorZ;
	}

	/// Obter ponto do centro da BBox.
	public Ponto4D obterCentro() {
		return centro;
	}

}