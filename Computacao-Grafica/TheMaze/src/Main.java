
/**
 * Objetivo: trabalha com conceitos da camera sintetica
 * https://www.opengl.org/sdk/docs/man2/xhtml/
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
//import javax.media.opengl.glu.GLUquadric;
import javax.swing.JOptionPane;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.TextureData;

public class Main implements GLEventListener, KeyListener {
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable; // Era .2f ali em baixo no y
	private double xEye, yEye, zEye;
	private double xCenter, yCenter, zCenter;
	private float inicio[] = new float[2];
	private float fim[] = new float[2];
	private int width = 0, height = 0;
	private int widthImage = 0, heightImage = 0;
	private float translacaoCubo2[] = { 0.0f, 0.0f, 0.0f };
	private float translacaoCubo1[] = { 0.0f, 0.0f, 0.0f };
	private float escalaCubo1[] = { 2.0f, 2.0f, 2.0f };
	private float translacaoCuboEu[] = new float[3];
	private float escalaCuboEu[] = { 1.0f, 1.0f, 1.0f };
	private float corRed[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	private float corBlue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
	private boolean eHMaterial = true;
	private int direcao = 1;
	private String mapa = "11111111112" + "10000000012" + "13001000112" + "11010010012" + "10010110112" + "11110100112"
			+ "10000010112" + "10101000112" + "10101010042" + "1111111111";

 	private char[][] restricoesMapa = new char[10][10];
	
	private int idTexture[];
	
	private BufferedImage image;
	private TextureData td;
	private ByteBuffer buffer[];	
	
	
	public void loadImage(int ind, String fileName)
	{
		// Tenta carregar o arquivo		
		image = null;
		try {
			image = ImageIO.read(new File(fileName));
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro na leitura do arquivo "+fileName);
		}

		// Obtem largura e altura
		widthImage  = image.getWidth();
		heightImage = image.getHeight();
		// Gera uma nova TextureData...
		td = new TextureData(0,0,false,image);
		// ...e obtem um ByteBuffer a partir dela
		buffer[ind] = (ByteBuffer) td.getBuffer();
	}

	// Metodo inicial
	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glut = new GLUT();
		glDrawable.setGL(new DebugGL(gl));

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
				
		gl.glEnable(GL.GL_LIGHT0);
		gl.glEnable(GL.GL_LIGHT1);
		gl.glEnable(GL.GL_LIGHTING);
		
		
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE);	
		
		// Habilita o modelo de colorizacao de Gouraud
		gl.glShadeModel(GL.GL_SMOOTH);
		
		//===== Comandos de inicializacao para textura
		idTexture = new int[3]; // lista de identificadores de textura
		gl.glGenTextures(1, idTexture, 2); 		// Gera identificador de textura
		// Define os filtros de magnificacao e minificacao 
		gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MIN_FILTER,GL.GL_LINEAR);	
		gl.glTexParameteri(GL.GL_TEXTURE_2D,GL.GL_TEXTURE_MAG_FILTER,GL.GL_LINEAR);
		buffer = new ByteBuffer [2]; // buffer da imagem
		loadImage(0,"logoGCG.jpg"); // carrega as texturas		
		loadImage(1,"madeira.jpg");
		


		ligarLuz();


		int countLinha = 0;
		int countColuna = 0;
		
		// Cria o array do mapa
		for (int i = 0; i < mapa.length(); i++) {
			// Se for 2, é fim da linha
			if (mapa.charAt(i) == '2') {
				countColuna = 0;
				countLinha++;
			} else {
				// Senão, seta restrição
				restricoesMapa[countLinha][countColuna] = mapa.charAt(i);
				
				// Se for 3, inicio do boneco
				if (mapa.charAt(i) == '3') {
					inicio[0] = countLinha;
					inicio[1] = countColuna;
				}

				// Se for 4, objetivo
				if (mapa.charAt(i) == '4') {
					fim[0] = countLinha;
					fim[1] = countColuna;

				} else {
					countColuna++;
				}
			}
		}

		
		// Posiciona a camera no inicio
		xEye = inicio[0] * 2;
		yEye =  -0.5f;
		zEye = inicio[1] * 2;

		// Seta a direção da camera
		xCenter =  inicio[0] * 2 + 2;
		yCenter = -0.5f;
		zCenter = inicio[1] * 2;

	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		this.width = width;
		this.height = height;
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
		glu.gluPerspective(60, width / height, 0.1, 100); // projecao Perpectiva
	}

	public void display(GLAutoDrawable drawable) {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
		glu.gluPerspective(60, width / height, 0.1, 100);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluLookAt(xEye, yEye, zEye, xCenter, yCenter, zCenter, 0.0f, 1.0f, 0.0f);

		drawAxis();

		gl.glColor3f(1.0f, 0.0f, 1.0f);

		// Desenha o mapa
		for (int i = 0; i < restricoesMapa.length; i++) {
			for (int j = 0; j < restricoesMapa.length; j++) {
				if (restricoesMapa[i][j] == '1') {
					translacaoCubo1[0] = i*2;
					translacaoCubo1[2] = j*2;


					translacaoCubo2[0] = i*2;
					translacaoCubo2[2] = j*2;
					translacaoCubo2[1] = 1.0f;

					
					gl.glPushMatrix();
					gl.glEnable(GL.GL_TEXTURE_2D);	// Primeiro habilita uso de textura
					gl.glBindTexture(GL.GL_TEXTURE_2D, idTexture[1]); 		// Especifica qual e a textura corrente pelo identificador 
					gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, widthImage, heightImage, 0, GL.GL_BGR,GL.GL_UNSIGNED_BYTE, buffer[1]); 		// Envio da textura para OpenGL
					
					
					drawCube(translacaoCubo1, escalaCubo1, 1);
					System.out.println(translacaoCubo1[0]);
					System.out.println(translacaoCubo1[1]);
					System.out.println(translacaoCubo1[2]);
					gl.glDisable(GL.GL_TEXTURE_2D);	//	Desabilita uso de textura
					gl.glPopMatrix();
					//drawCube(translacaoCubo2, escalaCubo1, 1);
				}
			}
		}


		// Desenha o chão
		gl.glPushMatrix();
		gl.glEnable(GL.GL_TEXTURE_2D);	// Primeiro habilita uso de textura
		gl.glBindTexture(GL.GL_TEXTURE_2D, idTexture[0]); 		// Especifica qual e a textura corrente pelo identificador 
		gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, 3, widthImage, heightImage, 0, GL.GL_BGR,GL.GL_UNSIGNED_BYTE, buffer[0]); 		// Envio da textura para OpenGL
		
		//gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_QUADS);
		
		
		gl.glNormal3f(0.0f,-1.0f,0.0f);
		gl.glTexCoord2f(-1.0f, -1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
		gl.glTexCoord2f(-1.0f, 19.0f); gl.glVertex3f(-1.0f, -1.0f, 19.0f);
		gl.glTexCoord2f(19.0f, 19.0f); gl.glVertex3f(19.0f, -1.0f, 19.0f);
		gl.glTexCoord2f(19.0f, -1.0f); gl.glVertex3f(19.0f, -1.0f, -1.0f);

		gl.glEnd();
		
		gl.glDisable(GL.GL_TEXTURE_2D);	//	Desabilita uso de textura
		gl.glPopMatrix();

		// Desenha marcação inicio
		desenhaQuadrado(inicio[0], inicio[1], 1.0f, 1.0f, 0.0f);

		// Desenha maração fim 
		desenhaQuadrado(fim[0], fim[1], 0.0f, 1.0f, 1.0f);

		desenhaMapa2D();

		gl.glFlush();
	}

	// Desenha mapinha 
	private void desenhaMapa2D() {
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(0, 1000.0, 0, 1000.0);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		int x = (int) xEye / 2;
		int z = (int) zEye / 2;

		gl.glLineWidth(6.0f);
		gl.glColor3f(0, 1.0f, 0);
		gl.glBegin(GL.GL_LINES);

		//Desenha indicador da direção no minimapa
		switch (direcao) {
		case 1:
			gl.glVertex2d(x * 30 + 27, 1000 - z * 30 - 30);
			gl.glVertex2d(x * 30 + 27, 1000 - z * 30);
			break;
		
		case 2:
			gl.glVertex2d(x * 30, 1000 - z * 30 - 27);
			gl.glVertex2d(x * 30 + 30, 1000 - z * 30 - 27);
			break;

		case 3:
			gl.glVertex2d(x * 30 + 4, 1000 - z * 30 - 30);
			gl.glVertex2d(x * 30 + 4, 1000 - z * 30);
			break;
			
		case 4:
			gl.glVertex2d(x * 30, 1000 - z * 30 - 4);
			gl.glVertex2d(x * 30 + 30, 1000 - z * 30 - 4);
			break;

		}

		gl.glEnd();

		gl.glBegin(GL.GL_QUADS);
		
		//Desenha personagem no minimapa
		gl.glColor3f(0, 0, 1.0f);
		gl.glVertex2d(x * 30, 1000 - z * 30 - 30);
		gl.glVertex2d(x * 30 + 30, 1000 - z * 30 - 30);
		gl.glVertex2d(x * 30 + 30, 1000 - z * 30);
		gl.glVertex2d(x * 30, 1000 - z * 30);

		//Desenha o minimapa
		for (int i = 0; i < restricoesMapa.length; i++) {
			for (int j = 0; j < restricoesMapa.length; j++) {
				switch (restricoesMapa[i][j]) {

				case '1':
					gl.glColor3f(1.0f, 0, 0);
					break;
				case '0':
					gl.glColor3f(.5f, .5f, .5f);
					break;
				case '3':
					gl.glColor3f(1.0f, 1.0f, 0);
					break;
				case '4':
					gl.glColor3f(0, 1.0f, 1.05f);
					break;
				}
				gl.glVertex2d(i * 30, 1000 - j * 30 - 30);
				gl.glVertex2d(i * 30 + 30, 1000 - j * 30 - 30);
				gl.glVertex2d(i * 30 + 30, 1000 - j * 30);
				gl.glVertex2d(i * 30, 1000 - j * 30);
			}
		}

		gl.glEnd();

	}

	private void desenhaQuadrado(float x, float z, float r, float g, float b) {
		gl.glColor3f(r, g, b);
		gl.glBegin(GL.GL_QUADS);

		gl.glVertex3f(x * 2 - 1, -0.999f, z * 2 - 1);
		gl.glVertex3f(x * 2 - 1, -0.999f, z * 2 + 1);
		gl.glVertex3f(x * 2 + 1, -0.999f, z * 2 + 1);
		gl.glVertex3f(x * 2 + 1, -0.999f, z * 2 - 1);

		gl.glEnd();
	}
/*
	private void drawCube(float translacao[], float escala[], int cor) {
		if (eHMaterial) {
			if (cor == 1) {
				gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corRed, 0);
				gl.glEnable(GL.GL_LIGHTING);
				gl.glPushMatrix();
				gl.glScalef(escala[0], escala[1], escala[2]);
				gl.glTranslated(translacao[0], translacao[1], translacao[2]);

				glut.glutSolidCube(1.0f);
				gl.glPopMatrix();

			} else {
				gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corBlue, 0);
				gl.glEnable(GL.GL_LIGHTING);
				gl.glPushMatrix();
				gl.glScalef(escala[0], escala[1], escala[2]);
				gl.glTranslated(translacao[0], translacao[1], translacao[2]);

				glut.glutSolidSphere(0.5, 100, 100);
				gl.glPopMatrix();

			}
		}

		if (eHMaterial) {
			gl.glDisable(GL.GL_LIGHTING);
		}
	}
	*/
	
	public void drawCube(float translacao[], float escala[], int cor) {
		gl.glPushMatrix();
		gl.glTranslatef(translacao[0], translacao[1], translacao[2]);
		gl.glScalef(escala[0], escala[1], escala[2]);
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glBegin (GL.GL_QUADS );
			// Especifica a coordenada de textura para cada vertice
			// Face frontal
		gl.glNormal3f(0.0f,0.0f,-0.5f);
		gl.glTexCoord2f(0.0f, 0.5f); gl.glVertex3f(-0.5f, -0.5f,  0.5f);
		gl.glTexCoord2f(0.5f, 0.5f); gl.glVertex3f( 0.5f, -0.5f,  0.5f);
		gl.glTexCoord2f(0.5f, 0.0f); gl.glVertex3f( 0.5f,  0.5f,  0.5f);
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.5f,  0.5f,  0.5f);				
		// Face posterior
		gl.glNormal3f(0.0f,0.0f,0.5f);
		gl.glTexCoord2f(0.5f, 0.0f); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(0.5f, 0.5f); gl.glVertex3f(-0.5f,  0.5f, -0.5f);
		gl.glTexCoord2f(0.0f, 0.5f); gl.glVertex3f( 0.5f,  0.5f, -0.5f);
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.5f, -0.5f, -0.5f);
		// Face superior
		gl.glNormal3f(0.0f,0.5f,0.0f);
		gl.glTexCoord2f(0.0f, 0.5f); gl.glVertex3f(-0.5f,  0.5f, -0.5f);
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.5f,  0.5f,  0.5f);
		gl.glTexCoord2f(0.5f, 0.0f); gl.glVertex3f( 0.5f,  0.5f,  0.5f);
		gl.glTexCoord2f(0.5f, 0.5f); gl.glVertex3f( 0.5f,  0.5f, -0.5f);
		// Face inferior
		gl.glNormal3f(0.0f,-0.5f,0.0f);
		gl.glTexCoord2f(0.5f, 0.5f); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(0.0f, 0.5f); gl.glVertex3f( 0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.5f, -0.5f,  0.5f);
		gl.glTexCoord2f(0.5f, 0.0f); gl.glVertex3f(-0.5f, -0.5f,  0.5f);
		// Face lateral direita
		gl.glNormal3f(0.5f,0.0f,0.0f);
		gl.glTexCoord2f(0.5f, 0.0f); gl.glVertex3f( 0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(0.5f, 0.5f); gl.glVertex3f( 0.5f,  0.5f, -0.5f);
		gl.glTexCoord2f(0.0f, 0.5f); gl.glVertex3f( 0.5f,  0.5f,  0.5f);
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 0.5f, -0.5f,  0.5f);
		// Face lateral esquerda
		gl.glNormal3f(-0.5f,0.0f,0.0f);
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(0.5f, 0.0f); gl.glVertex3f(-0.5f, -0.5f,  0.5f);
		gl.glTexCoord2f(0.5f, 0.5f); gl.glVertex3f(-0.5f,  0.5f,  0.5f);
		gl.glTexCoord2f(0.0f, 0.5f); gl.glVertex3f(-0.5f,  0.5f, -0.5f);
		gl.glEnd();
	gl.glPopMatrix();
		
	}

	private void ligarLuz() {
		if (eHMaterial) {
			float posLight[] = { 2.0f, 10.0f, 2.0f, 0.0f };
			gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0);
			gl.glEnable(GL.GL_LIGHT0);
		} else
			gl.glDisable(GL.GL_LIGHT0);
	}

	public void move(int direcao, int frente) {
		float num = 0;
		int count = 0;

		while (count < 20) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException ie) {
			}
			num += 0.01;
			count++;

			switch (direcao) {
			case 1:
				xEye += num * frente;
				xCenter += num * frente;
				break;
			case 2:
				zEye += num * frente;
				zCenter += num * frente;
				break;
			case 3:
				xEye += num * -frente;
				xCenter = num * -frente;
				break;
			case 4:
				zEye += num * -frente;
				zCenter += num * -frente;
				break;
			}

			glDrawable.display();

		}

	}

	public void viraDireita(int direcao) {
		float num = 0;
		int count = 0;

		while (count < 20) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException ie) {
			}
			num += 0.01;
			count++;

			switch (direcao) {
			case 1:
				xCenter -= num;
				zCenter += num;
				break;
			case 2:
				xCenter -= num;
				zCenter -= num;
				break;
			case 3:
				xCenter += num;
				zCenter -= num;
				break;
			case 4:
				xCenter += num;
				zCenter += num;
				break;
			}

			glDrawable.display();

		}
	}

	public void viraEsquerda(int direcao) {
		float num = 0;
		int count = 0;

		while (count < 20) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException ie) {
			}
			num += 0.01;
			count++;

			switch (direcao) {
			case 1:
				xCenter -= num;
				zCenter -= num;
				break;
			case 2:
				xCenter += num;
				zCenter -= num;
				break;
			case 3:
				xCenter += num;
				zCenter += num;
				break;
			case 4:
				xCenter -= num;
				zCenter += num;
				break;
			}

			glDrawable.display();

		}
	}

	public void keyPressed(KeyEvent e) {
		int proxPosicao;

		double inicialXEye = xEye;
		double inicialXCenter = xCenter;
		double inicialZEye = zEye;
		double inicialZCenter = zCenter;

		switch (e.getKeyCode()) {

		case KeyEvent.VK_1:
			yCenter = 0;
			xCenter = 0;
			zCenter = 0;
			xEye = 4;
			yEye = 30;
			zEye = 4;
			break;
		
		case KeyEvent.VK_DOWN:
			//System.out.println(xEye + " ZEYE " + zEye);
			switch (direcao) {

			// ir para X positivo
			case 1:
				inicialXEye = xEye;
				inicialXCenter = xCenter;
				proxPosicao = (int) (xEye - 2) / 2;
				if (proxPosicao != 0 && restricoesMapa[proxPosicao][(int) zEye / 2] != '1') {

					move(1, -1);

					xEye = -2 + inicialXEye;
					xCenter = -2 + inicialXCenter;
				}

				break;

			// ir para Z positivo
			case 2:
				inicialZEye = zEye;
				inicialZCenter = zCenter;
				proxPosicao = (int) (zEye - 2) / 2;
				if (proxPosicao != 0 && restricoesMapa[(int) xCenter / 2][proxPosicao] != '1') {

					move(2, -1);

					zEye = -2 + inicialZEye;
					zCenter = -2 + inicialZCenter;
				}

				break;

			// ir para X negativo
			case 3:
				inicialXEye = xEye;
				inicialXCenter = xCenter;
				proxPosicao = (int) (xEye + 2) / 2;
				if (proxPosicao != 0 && restricoesMapa[proxPosicao][(int) zCenter / 2] != '1') {

					move(3, -1);

					xEye = 2 + inicialXEye;
					xCenter = 2 + inicialXCenter;
				}

				break;

			// ir para Z negativo
			case 4:
				inicialZEye = zEye;
				inicialZCenter = zCenter;
				proxPosicao = (int) (zEye + 2) / 2;
				if (proxPosicao != 0 && restricoesMapa[(int) xCenter / 2][proxPosicao] != '1') {

					move(4, -1);

					zEye = 2 + inicialZEye;
					zCenter = 2 + inicialZCenter;

				}

				break;
			}

			break;

		case KeyEvent.VK_UP:
			switch (direcao) {

			// ir para X positivo
			case 1:
				inicialXEye = xEye;
				inicialXCenter = xCenter;
				proxPosicao = (int) xCenter / 2;
				if (proxPosicao != 0 && restricoesMapa[proxPosicao][(int) zCenter / 2] != '1') {

					move(1, 1);

					xEye = inicialXCenter;
					xCenter = 2 + inicialXCenter;

				}

				break;

			// ir para Z positivo
			case 2:
				inicialZEye = zEye;
				inicialZCenter = zCenter;
				proxPosicao = (int) zCenter / 2;
				if (proxPosicao != 0 && restricoesMapa[(int) xCenter / 2][proxPosicao] != '1') {

					move(2, 1);

					zEye = inicialZCenter;
					zCenter = 2 + inicialZCenter;

				}

				break;

			// ir para X negativo
			case 3:
				inicialXEye = xEye;
				inicialXCenter = xCenter;
				proxPosicao = (int) xCenter / 2;
				if (proxPosicao != 0 && restricoesMapa[proxPosicao][(int) zCenter / 2] != '1') {

					move(3, 1);

					xEye = inicialXCenter;
					xCenter = -2 + inicialXCenter;

				}

				break;

			// ir para Z negativo
			case 4:
				inicialZEye = zEye;
				inicialZCenter = zCenter;
				proxPosicao = (int) zCenter / 2;
				if (proxPosicao != 0 && restricoesMapa[(int) xCenter / 2][proxPosicao] != '1') {

					move(4, 1);

					zEye = inicialZCenter;
					zCenter = -2 + inicialZCenter;

				}

				break;
			}

			if (restricoesMapa[(int) xEye / 2][(int) zEye / 2] == '4') {
				JOptionPane.showMessageDialog(null, "Você venceu");
			}

			break;

		case KeyEvent.VK_RIGHT:
			float num = 0;
			int count = 0;

			switch (direcao) {
			case 1:
				viraDireita(1);

				xCenter = xEye;
				zCenter = zEye + 2;
				direcao++;

				break;

			case 2:
				viraDireita(2);
				zCenter = zEye;
				xCenter = xEye - 2;
				direcao++;
				break;

			case 3:

				viraDireita(3);
				xCenter = xEye;

				zCenter = zEye - 2;

				direcao++;
				break;

			case 4:

				viraDireita(4);

				zCenter = zEye;
				xCenter = xEye + 2;
				direcao = 1;
				break;
			}
			break;
		case KeyEvent.VK_LEFT:
			//System.out.println(direcao);
			switch (direcao) {
			case 1:
				viraEsquerda(1);
				xCenter = xEye;
				zCenter = zEye - 2;
				direcao = 4;
				break;

			case 2:
				viraEsquerda(2);
				zCenter = zEye;
				xCenter = xEye + 2;
				direcao--;
				break;

			case 3:
				viraEsquerda(3);
				xCenter = xEye;
				zCenter = zEye + 2;
				direcao--;
				break;

			case 4:
				viraEsquerda(4);
				zCenter = zEye;
				xCenter = xEye - 2;
				direcao--;
				break;
			}

			break;

		case KeyEvent.VK_M:
			eHMaterial = !eHMaterial;
			ligarLuz();
			break;
		}

		glDrawable.display();
	}

	public void drawAxis() {
		// eixo X - Red
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(10.0f, 0.0f, 0.0f);
		gl.glEnd();
		// eixo Y - Green
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f, 10.0f, 0.0f);
		gl.glEnd();
		// eixo Z - Blue
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f, 0.0f, 10.0f);
		gl.glEnd();
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void Debug() {
	}

}
