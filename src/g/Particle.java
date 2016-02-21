package g;

public class Particle {
    
    Particle(int size) {
        this.xPosition = Math.random() * size - (size / 2);
        this.yPosition = Math.random() * size - (size / 2);
        this.xVelocity = 0.0;
        this.yVelocity = 0.0;
    }
    
    private Double xPosition;
    private Double yPosition;
    private Double xVelocity;
    private Double yVelocity;
    
    public Double getXPosition() {
        return this.xPosition;
    }
    public Double getYPosition() {
        return this.yPosition;
    }
    public Double getXVelocity() {
        return this.xVelocity;
    }
    public Double getYVelocity() {
        return this.yVelocity;
    }
    
    
    public void changeVelocity(Double x, Double y) {
        if (x < 1.0 && x < 1.0) {
            this.xVelocity = this.xVelocity + x;
        }
        if (y < 1.0 && y < 1.0) {
            this.yVelocity = this.yVelocity + y;
        }
    }
    
    public void updatePosition() {
        this.xPosition = this.xPosition + this.xVelocity;
        this.yPosition = this.yPosition + this.yVelocity;
    }
}