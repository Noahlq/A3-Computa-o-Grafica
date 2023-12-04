public class Ball implements BallInterface{
    public Float cordX;
    public Float cordXRight;
    public Float cordXLeft;
    public Float cordY;
    public Float cordYUp;
    public Float cordYDown;
    public Float acelerationX;
    public Float acelerationY;
    
    public Ball (Float cordX, Float cordXRight, Float cordXLeft, Float cordY, Float cordYUp, Float cordYDown, float acelerationX, Float acelerationY) {
        this.cordX = cordX;
        this.cordXRight = cordXRight;
        this.cordXLeft = cordXLeft;
        this.cordY = cordY;
        this.cordYUp = cordYUp;
        this.cordYDown = cordYDown;
        this.acelerationX = acelerationX;
        this.acelerationY = acelerationY;
    }

    public Boolean moveBall(){
        try{
            this.cordX += acelerationX;
            this.cordY += acelerationY;
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
