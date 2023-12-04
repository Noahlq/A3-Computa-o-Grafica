public class Backend {
    
    static Player player1 = new Player(70f, -70f, -230f, -240f, 5f, 0f, 0f, 0, 0);
    static Player player2 = new Player(null, null, null, null, 5f, 0f, 0f, 0, 0);

    public static void movePlayer1Right(){
        player1.moveRight();
        System.out.println("CordXRight: " + player1.cordXRight);
        System.out.println("CordXLeft: " + player1.cordXLeft);
        System.out.println("CordYUp: " + player1.cordYUp);
        System.out.println("CordYDown: " + player1.cordYDown);
        System.out.println("Velocity: " + player1.velocity);
    }

    public static void movePlayer1Left(){
        player1.moveLeft();
        System.out.println("CordXRight: " + player1.cordXRight);
        System.out.println("CordXLeft: " + player1.cordXLeft);
        System.out.println("CordYUp: " + player1.cordYUp);
        System.out.println("CordYDown: " + player1.cordYDown);
        System.out.println("Velocity: " + player1.velocity);
    }

    public static void movePlayer2Right(){
        player2.moveRight();
    }

    public static void movePlayer2Left(){
        player2.moveLeft();
    }

    public static Player sumCurrencyPlayer1(){
        player1.sumCurrency();
        return player1;
    }

    public static Player getPlayer1(){
        return player1;
    }

    public static Player getPlayer2(){
        return player2;
    }

}
