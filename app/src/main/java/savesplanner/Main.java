package savesplanner;
// import savesplanner.Calculator;

public class Main {
    public static void main(String[] args) {
        /*
         * TODO:
         * Mejorar la interfaz, esto significa:
         * - Utilizar la misma notación para inflación en todos lados.
         * - Esta será la de un multiplicador.
         * -> ejemplo "30% de aumento -> 1.03"
         * 
         * FIXME:
         * El algoritmo explota cuando usamos un finalInteres<1
         */

        int periods = 12;
        double currentPrice = 100;
        double interest = Calculator.interestForPeriod(1.1, periods);
        double price = Calculator.futurePrice(currentPrice, interest, periods);
        System.out.println(interest*100);
        System.out.println(price);
    }
}

