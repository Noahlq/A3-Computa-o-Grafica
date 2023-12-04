

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
/**
 *
 * @author Noah
 */
public class Renderer {
    private static GLWindow window = null;
    public static int screenWidth = 1280;  //1280
    public static int screenHeight = 960; //960 

    //Cria a janela de rendeziração do JOGL
    public static void init(){    
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        window = GLWindow.create(caps);
        window.setSize(screenWidth, screenHeight);

        Cena cena = new Cena();
        KeyBoard keyboard = new KeyBoard(cena);
        keyboard.setGLAutoDrawable(window);

        window.addGLEventListener(cena);
        window.addKeyListener(keyboard);

        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                animator.stop();
                System.exit(0);
            }
        });

        window.setVisible(true);
    }
  
    public static void start() {
    
        Cena cena = new Cena();
        KeyBoard keyboard = new KeyBoard(cena);
        keyboard.setCenaReference(cena);
        Renderer.init();
        window.addKeyListener(keyboard);
    }
}
