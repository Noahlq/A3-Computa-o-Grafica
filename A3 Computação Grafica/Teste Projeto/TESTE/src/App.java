import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        // Create a frame to hold the canvas.
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a canvas to draw on.
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(capabilities);

        // Create the pong game.
        PongGame game = new PongGame();

        // Add a key listener for paddle movement.
        canvas.addKeyListener(game);

        // Add a listener for the game to the canvas.
        canvas.addGLEventListener(game);

        // Add the canvas to the frame.
        frame.getContentPane().add(canvas);

        // Set the size of the frame and make it visible.
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}

class PongGame implements GLEventListener, java.awt.event.KeyListener {
    private int x = 0, y = 0;
    private int dx = 2, dy = 2;
    private int paddle1Y = 0, paddle2Y = 0;
    private boolean leftPaddle = false, rightPaddle = false;

    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glRectf(x, y, x + 50, y + 20);

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glRectf(x, paddle1Y, x + 20, paddle1Y + 100);

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glRectf(x + 600, paddle2Y, x + 620, paddle2Y + 100);

        x += dx;
        y += dy;

        // Bounce the ball off the walls.
        if (x <= 0 || x >= 750) {
            dx = -dx;
        }

        // Bounce the ball off the paddles.
        if (y <= paddle1Y + 100 && x >= 0 && x <= 20) {
            dy = -dy;
        }
        if (y >= paddle2Y - 100 && x >= 600 && x <= 620) {
            dy = -dy;
        }

        // Check for losing.
        if (y <= 0 || y >= 550) {
            if (x <= 20) {
                System.out.println("Player 2 wins!");
            } else {
                System.out.println("Player 1 wins!");
            }
            System.exit(0);
        }

        drawable.swapBuffers();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void keyPressed(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_W:
                paddle1Y -= 10;
                break;
            case java.awt.event.KeyEvent.VK_S:
                paddle1Y += 10;
                break;
            case java.awt.event.KeyEvent.VK_UP:
                paddle2Y -= 10;
                break;
            case java.awt.event.KeyEvent.VK_DOWN:
                paddle2Y += 10;
                break;
        }
    }

    public void keyReleased(java.awt.event.KeyEvent e) {
    }

    public void keyTyped(java.awt.event.KeyEvent e) {
    }
}