package savesplanner;
// import savesplanner.Calculator;

public class Main {
    public static void main(String[] args) {
        // double precio = Calculator.futurePrice(100, 1.845/12, 12);
        // System.out.println("Hello world!");
        // System.out.println(precio);
        double currentPrice = 100;
        double interest = Calculator.interestForPeriod(200);
        double price = Calculator.futurePrice(currentPrice, interest, 3);
        System.out.println(interest);
        System.out.println(price);
    }
}

