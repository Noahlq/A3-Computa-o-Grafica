
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GLAutoDrawable;
/**
 *
 * @author Noah
 */
public class KeyBoard implements KeyListener{
    private Cena cena;
    private GLAutoDrawable drawable;
    Ball ball = Backend.getBall();
    
    public KeyBoard(Cena cena){
        this.cena = cena; 
    }

    public void setGLAutoDrawable(GLAutoDrawable drawable) {
        this.drawable = drawable;
    }

    public void setCenaReference(Cena cena) {
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {//Player1

           Backend.movePlayer1Left();

            if (drawable != null) { // Verificar se drawable foi configurado corretamente
                drawable.display(); // Redesenha a cena para refletir o movimento do objeto
            }
            
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {

            Backend.movePlayer1Right();

            if (drawable != null) { // Verificar se drawable foi configurado corretamente
                drawable.display(); // Redesenha a cena para refletir o movimento do objeto
            }
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){ //Player2

            Backend.movePlayer2Left();

            if (drawable!= null) { // Verificar se drawable foi configurado corretamente
                drawable.display(); // Redesenha a cena para refletir o movimento do objeto
            }
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){

            Backend.movePlayer2Right();
            
            if (drawable!= null) { // Verificar se drawable foi configurado corretamente
                drawable.display(); // Redesenha a cena para refletir o movimento do objeto
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (ball.isStart == true) {
                ball.isntStart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}
