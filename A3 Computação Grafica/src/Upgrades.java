import com.jogamp.newt.event.KeyEvent;


public class Upgrades{

    public Boolean SpeedUp(){
        try{
            Player p1 = Backend.getPlayer1();
            p1.velocity += 5;
            return true;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    public Boolean DoubleLength(){
        Player p1 = Backend.getPlayer1();

        p1.cordXLeft += 70;
        p1.cordXRight += 70;

        return true;

    }

    public Boolean TripleBalls(){

        return true;

    }

    public Boolean CarraraTaxi(KeyEvent e){

        String primeiroinput = "", segundoinput = "", terceiroinput = "", quartoinput = ""; 

        if (e.getKeyChar() == KeyEvent.VK_LEFT){

            primeiroinput = "Esquerda";

        }

        if (e.getKeyChar() == KeyEvent.VK_RIGHT){

            segundoinput = "Direita";

        }

        if (e.getKeyChar() == KeyEvent.VK_UP){

            terceiroinput = "Cima";


        }

        if (e.getKeyChar() == KeyEvent.VK_DOWN){

            quartoinput = "Baixo";

        }


        if (primeiroinput == "Esquerda" && segundoinput == "Direita" && terceiroinput == "Cima" && quartoinput == "Baixo"){

            System.out.println("CarraraTaxi");

        }

        return true;

    }


    public Boolean FireBalls(){


        return true;

    }
}
