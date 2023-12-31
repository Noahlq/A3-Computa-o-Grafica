
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import java.lang.Math;

/**
 *
 * @author Noah
 */

public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;    
    GLU glu;
    Player player1 = Backend.getPlayer1();
    Player player2 = Backend.getPlayer2();
    Ball ball = Backend.getBall();

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
        //Funcoes para lembrar:
        //gl.glTranslatef(x, y, z);
        //gl.glPushMatrix();
        //gl.glPopMatrix();
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();

        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 1, 0, 0.3F);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);       
        gl.glLoadIdentity(); //lê a matriz identidade

        wallsHor(gl);
        wallsVert(gl);

        gl.glPushMatrix();
        gl.glTranslatef(player1.cordX, player1.cordY, 0);
        player1(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(player2.cordX, player2.cordY, 0);
        player2(gl);
        gl.glPopMatrix();

        if(ball.isStart == true){
            gl.glPushMatrix();
            gl.glTranslatef(0, -220, 0);
            drawCircle(gl, 10, 30);
            gl.glPopMatrix();
        } else {
            Backend.checkCollisionPlayer();
            Backend.checkCollisionWallRight();
            Backend.checkCollisionWallLeft();
            Backend.checkCollisionWallUp();
            Backend.checkCollisionWallDown();
            gl.glPushMatrix();
            ball.startBall();
            gl.glTranslatef(ball.cordX, ball.cordY, 0);
            drawCircle(gl, 10, 30);
            gl.glPopMatrix();
        }
        
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

    public void player1(GL2 gl){
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-30, -230);
            gl.glVertex2f(30, -230);
            gl.glVertex2f(30, -240);
            gl.glVertex2f(-30, -240);
        gl.glEnd();
    }
    public void player2(GL2 gl){
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-30, 230);
            gl.glVertex2f(30, 230);
            gl.glVertex2f(30, 240);
            gl.glVertex2f(-30, 240);
        gl.glEnd();
    }

    public static void joyStick1(GL2 gl){
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-35, -210); //1
            gl.glVertex2f(0, -210); //2
            gl.glVertex2f(0, -220); //3
            gl.glVertex2f(-35, -220); //4
        gl.glEnd();
    }
    public static void joyStick2(GL2 gl){
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(35, -210); //1
            gl.glVertex2f(0, -210); //2
            gl.glVertex2f(0, -220); //3
            gl.glVertex2f(35, -220); //4
        gl.glEnd();                  
    }

    public static void bolaAmarela(GL2 gl){
        gl.glColor3f(1f,1f,0f);
            gl.glBegin(GL2.GL_POLYGON);
                float thd;
                for(int i=0;i<360;i++){
                    thd = i*3.14F/180;
                    gl.glVertex2f(45+10*(float)Math.cos(thd), 0+10*(float)Math.sin(thd));
                }
            gl.glEnd();
    }

    public static void drawCircle(GL2 gl, float radius, int numSegments) {
        gl.glColor3f(0f, 0f, 0f);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < numSegments; i++) {
            double theta = 2.0 * Math.PI * i / numSegments;
            float x = radius * (float)Math.cos(theta);
            float y = radius * (float)Math.sin(theta);
            gl.glVertex2f(x, y);
        }
        gl.glEnd();
    }

    public static void bordaBaixo(GL2 gl){
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-250, -230); //1
            gl.glVertex2f(-35, -230); //2
            gl.glVertex2f(-35, -240); //3
            gl.glVertex2f(-250, -240); //4
        gl.glEnd();

        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(250, -230); //1
            gl.glVertex2f(35, -230); //2
            gl.glVertex2f(35, -240); //3
            gl.glVertex2f(250, -240); //4
        gl.glEnd();                  
    }

    public static void wallsHor(GL2 gl){
        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-260, -250); //1
            gl.glVertex2f(-260, 250); //2
            gl.glVertex2f(-250, 250); //3
            gl.glVertex2f(-250, -250); //4
        gl.glEnd();

        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(250, -250); //1
            gl.glVertex2f(250, 250); //2
            gl.glVertex2f(260, 250); //3
            gl.glVertex2f(260, -250); //4
        gl.glEnd();
    }

    public static void wallsVert(GL2 gl) {//
        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-250, 240); //1
            gl.glVertex2f(-70, 240); //2
            gl.glVertex2f(-70, 250); //3
            gl.glVertex2f(-250, 250); //4
        gl.glEnd();

        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(250, 240); //1
            gl.glVertex2f(70, 240); //2
            gl.glVertex2f(70, 250); //3
            gl.glVertex2f(250, 250); //4
        gl.glEnd();

        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-250, -250); //1
            gl.glVertex2f(-70, -250); //2
            gl.glVertex2f(-70, -240); //3
            gl.glVertex2f(-250, -240); //4
        gl.glEnd();

        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(250, -250); //1
            gl.glVertex2f(70, -250); //2
            gl.glVertex2f(70, -240); //3
            gl.glVertex2f(250, -240); //4
        gl.glEnd();
    }
}
