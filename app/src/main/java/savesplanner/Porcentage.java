package savesplanner;

public class Porcentage {
    private double value = 0;
    public Porcentage(double value){
        this.value = value/100;
    }
    @Override
    public String toString(){
        return ""+this.value();
    }
    public double value(){
        return this.value * 100;
    }
    public double increaseMultiplier(){
        return 1 + this.value;
    }
    public double increase(double valueToIncrease){
        return valueToIncrease * (1+this.value());
    }
    public Porcentage multiply(Porcentage other){
        return new Porcentage((1+this.value())*(1+other.value())-1);
    }

}
