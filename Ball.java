import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ball implements BallInterface{
    public Float cordX;
    public Float cordXRight;
    public Float cordXLeft;
    public Float cordY;
    public Float cordYUp;
    public Float cordYDown;
    public Float acelerationX;
    public Float acelerationY;
    public Boolean isStart;
 
    
    public Ball (Float cordX, Float cordXRight, Float cordXLeft, Float cordY, Float cordYUp, Float cordYDown, float acelerationX, Float acelerationY, Boolean isStart) {
        this.cordX = cordX;
        this.cordXRight = cordXRight;
        this.cordXLeft = cordXLeft;
        this.cordY = cordY;
        this.cordYUp = cordYUp;
        this.cordYDown = cordYDown;
        this.acelerationX = acelerationX;
        this.acelerationY = acelerationY;
        this.isStart = isStart;
    }

    public Boolean changeVectorY(){
        try{
            //boolean isDown = vectorDown();
            //if (isDown == true){
                this.acelerationY = acelerationY * -1;
            //} else {}
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }  
    public Boolean changeVectorYWall(){
        try{  
            this.acelerationY = acelerationY * -1;
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean changeVectorXWall(){
        try{  
            this.acelerationX = acelerationX * -1;
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean changeVectorX(){
        try{  
            boolean isRight = vectorRight();
            if (isRight == true){
                this.acelerationX = acelerationX * -1;
            } else {}
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    public Boolean startBall(){
        try{
            if (acelerationX == 0) {

                List<Float> lista1 = new ArrayList<Float>();
                
                lista1.add(5f);
                lista1.add(-5f);

                Collections.shuffle(lista1);

                float element = lista1.get(0);

                this.acelerationX = element;
            }
            this.cordX += acelerationX;
            this.cordY += acelerationY;
            this.cordYUp += acelerationY;
            this.cordYDown -= (acelerationY * -1);
            this.cordXRight += acelerationX;
            this.cordXLeft -= (acelerationX * -1);
            System.out.println("CordYUp: " + this.cordYUp);
            System.out.println("CordYDown: " + this.cordYDown);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean vectorRight(){
        if (this.cordX > 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean vectorDown(){
        if (this.cordY < 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean isntStart(){
        this.isStart = false;
        return this.isStart;
    }
}
