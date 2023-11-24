
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import java.io.File;
import java.lang.Math;

import java.awt.Image;
import javax.imageio.ImageIO;
/**
 *
 * @author Noah
 */
public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;    
    GLU glu;
    private float startCarXN;
    private float startCarXP;
    private float carX = 0; // Posição inicial do objeto
    private float carY = 0; // Posição inicial do objeto
    private float ballY = 0; // Posição inicial da bola branca
    private float ballX = 0; // Posição inicial da bola branca
    private float ballVelocity = 1; // Velocidade inicial da bola branca
    private float ballAccelerationY = -0.05f; // Aceleração da gravidade
    

    public void updateBall() {
        ballY += ballVelocity; // Atualiza a posição da bola adicionando a velocidade
        ballVelocity += ballAccelerationY; // Atualiza a velocidade adicionando a aceleração

        if (ballY <= -170 && ballX >= startCarXN && ballX <= startCarXP) {
            System.out.println("Posição da bola:" + ballX +"\n"+ "Posição Negativa e Positiva do carro:" + startCarXN + "||" + startCarXP);
            ballY = -170;
            ballVelocity *= -0.8f; // Inverte a velocidade para simular uma colisão/elástico
        }

        // Restrição para evitar que a bola saia da tela no eixo Y
        if (ballY < -225) {
            ballY = -225;
            ballVelocity *= -0.8f; // Inverte a velocidade para simular uma colisão/elástico
        }
    }
//
    public void moveObjectLeft() {
        float halfWidth = 70; // Metade da largura do objeto (140 / 2)
        carX -= 5; // Movendo o objeto para a esquerda
        startCarXN = carX - halfWidth;
        startCarXP = carX + halfWidth;
        if (carX <= xMin + halfWidth) {
            carX = xMin + halfWidth;
        }
    }
    
    public void moveObjectRight() {
        float halfWidth = 70; // Metade da largura do objeto (140 / 2)
        carX += 5; // Movendo o objeto para a direita
        startCarXN = carX - halfWidth;
        startCarXP = carX + halfWidth;
        if (carX >= xMax - halfWidth) {
            carX = xMax - halfWidth;
        }
    }
    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -250;
        xMax = yMax = zMax = 250;
        GL2 gl = drawable.getGL().getGL2();
    }

    @Override
    public void display(GLAutoDrawable drawable) {  
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();

        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(1, 1, 1, 0.3F);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);       
        gl.glLoadIdentity(); //lê a matriz identidade
        
        gl.glPushMatrix();//Incio
        gl.glTranslatef(carX, -225, 0);
        car(gl);
        gl.glPopMatrix();//Fim

        updateBall();
        gl.glPushMatrix();
        gl.glTranslatef(-40, ballY, 0); // Posição vertical da bola branca
        bolaBranca(gl);
        gl.glPopMatrix();

        inimigos(gl);
        
        //gl.glColor3f(1,1,1); //cor branca        
        gl.glColor3f(0,0,0);      
        //desenha um retangulo
        
        gl.glFlush();      
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();  
        
        //evita a divisão por zero
        if(height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;
        
        //seta o viewport para abranger a janela inteira
        gl.glViewport(0, 0, width, height);
                
        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);      
        gl.glLoadIdentity(); //lê a matriz identidade
        
        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
        if(width >= height)            
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else        
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
                
        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }    

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    public static void car(GL2 gl){
        parteCima(gl);
        rodaEsquerda(gl);
        rodaDireita(gl);
        parteBaixo(gl);

    }
    
    public static void parteBaixo(GL2 gl){
        gl.glColor3f(1f,1f,0f);
            gl.glBegin(GL2.GL_POLYGON);
                //PARTE DE BAIXO
                gl.glVertex2f(-70, 0);
                gl.glVertex2f(70, 0);
                gl.glVertex2f(70, 25);
                gl.glVertex2f(-70, 25);
            gl.glEnd();
    }

    public static void parteCima(GL2 gl){
        gl.glColor3f(1f,1f,0f);
            gl.glBegin(GL2.GL_POLYGON);
                //PARTE DE CIMA
                gl.glVertex2f(-45, 25);
                gl.glVertex2f(45, 25);
                gl.glVertex2f(45, 45);
                gl.glVertex2f(-45, 45);
            gl.glEnd();
    }

    public static void rodaEsquerda(GL2 gl){
        gl.glColor3f(0f,0f,0f);
            gl.glBegin(GL2.GL_POLYGON);
                //RODA ESQUERDA
                float the;
                for(int i=0;i<360;i++){
                    the = i*3.14F/180;
                    gl.glVertex2f(-45+10*(float)Math.cos(the), 0+10*(float)Math.sin(the));
                }
            gl.glEnd();
    }

    public static void rodaDireita(GL2 gl){
        gl.glColor3f(0f,0f,0f);
            gl.glBegin(GL2.GL_POLYGON);
                //RODA DIREITA
                float thd;
                for(int i=0;i<360;i++){
                    thd = i*3.14F/180;
                    gl.glVertex2f(45+10*(float)Math.cos(thd), 0+10*(float)Math.sin(thd));
                }
            gl.glEnd();
    }

    public static void bolaBranca(GL2 gl){
        gl.glColor3f(1f,1f,0f);
            gl.glBegin(GL2.GL_POLYGON);
                //RODA DIREITA
                float thd;
                for(int i=0;i<360;i++){
                    thd = i*3.14F/180;
                    gl.glVertex2f(45+10*(float)Math.cos(thd), 0+10*(float)Math.sin(thd));
                }
            gl.glEnd();
    }

    public static void inimigos(GL2 gl){
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(10, 20);
            gl.glVertex2f(20, 20);
            gl.glVertex2f(20, 10);
            gl.glVertex2f(10, 10);
        gl.glEnd();     

            
    }
}
