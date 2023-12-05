public class Backend {
    
    static Player player1 = new Player(30f, -30f, -230f, -240f, 5f, 0f, 0f, 0, 0);
    static Player player2 = new Player(30f, -30f, 230f, 240f, 5f, 0f, 0f, 0, 0);
    static Ball ball = new Ball(0f, 5f, -5f, -220f, -215f, -225f, 0f, 5f, true);

    public static void movePlayer1Right(){
        player1.moveRight();
    }

    public static void movePlayer1Left(){
        player1.moveLeft();
    }

    public static void movePlayer2Right(){
        player2.moveRight();
    }

    public static void movePlayer2Left(){
        player2.moveLeft();
    }

    public static Player getCurrencyPlayer1(){
        player1.Currency();
        return player1;
    }

    public static Player getPlayer1(){
        return player1;
    }

    public static Player getPlayer2(){
        return player2;
    }

    public static Ball getBall(){
        return ball;
    }

    public static Boolean checkCollisionPlayer(){
        if (ball.cordXRight <= player2.cordXRight & ball.cordXLeft >= player2.cordXLeft & ball.cordYUp >= (player2.cordYUp - 11f) & ball.cordYDown <= (player2.cordYDown - 11f)) {
            ball.changeVectorY();
            ball.changeVectorX();
            return true;
        } else if (ball.cordXRight <= player1.cordXRight & ball.cordXLeft >= player1.cordXLeft & ball.cordYUp >= (player1.cordYUp + 10f) & ball.cordYDown <= (player1.cordYDown + 10f)) {
            ball.changeVectorY();
            ball.changeVectorX();
            return true;
        } else {
            return false;
        }
    }
    public static Boolean checkCollisionWallUp() {
        if(ball.cordYUp == 250f & ball.cordXRight <= 69f & ball.cordXLeft >= -69f){
            System.out.println("Gol");
            return true;
        } else if(ball.cordYDown == -250f & ball.cordXRight >= 69f & ball.cordXLeft <= -69f){
            System.out.println("Colidiu");
            ball.changeVectorYWall();
            return true;
        } else {
            return false;
        }
    }
    public static Boolean checkCollisionWallDown() {
        if(ball.cordYDown == -250f & ball.cordXRight <= 69f & ball.cordXLeft >= -69f){
            System.out.println("Gol");
            return true;
        } else if(ball.cordYDown == -250f & ball.cordXRight >= 69f & ball.cordXLeft <= -69f){
            System.out.println("COLIDIU");
            ball.changeVectorYWall();
            return true;
        } else {
            return false;
        }
    }

    public static Boolean checkCollisionWallRight() {
        if(ball.cordXRight == 250f){
            ball.changeVectorXWall();
            return true;
        } else {
            return false;
        }
    }

    public static Boolean checkCollisionWallLeft() {
        if(ball.cordXLeft == -250f){
            ball.changeVectorXWall();
            return true;
        } else {
            return false;
        }
    }
}
