public class Player implements Movement{
    public Float cordX;
    public Float cordXRight;
    public Float cordXLeft;
    public Float cordY;
    public Float cordYUp;
    public Float cordYDown;
    public Float velocity;
    public int upgrades;
    public int currency;

    public Player(Float cordXRight, Float cordXLeft, Float cordYUp, Float cordYDown, Float velocity, Float cordX, Float cordY, int upgrades, int currency) {
        this.cordX = cordX;
        this.cordXRight = cordXRight;
        this.cordXLeft = cordXLeft;
        this.cordY = cordY;
        this.cordYUp = cordYUp;
        this.cordYDown = cordYDown;
        this.velocity = velocity;
        this.upgrades = upgrades;
        this.currency = currency;
    }

    public static int Upgrades(int upgrades) {
        
        return 0;

    }

    public int Currency() {

        this.currency += currency;
        
        return currency;
    }


    @Override
    public Boolean moveLeft(){
        try{
            
            if (cordXLeft != -250) {
                this.cordX -= velocity;
                this.cordXLeft -= velocity;
                this.cordXRight += (velocity * -1);
            } else {}
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean moveRight(){
        try{
            if (cordXRight != 250) {
                this.cordX += velocity;
                this.cordXRight += velocity;
                this.cordXLeft -= (velocity * -1);
            } else {}
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
