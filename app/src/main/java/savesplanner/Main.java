package savesplanner;
// import savesplanner.Calculator;

import java.text.NumberFormat;
import java.util.Locale;

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

        double interest = (1+0.33);
        int periods = 2;
        double[] payments = new double[periods];
        try {
            payments = Calculator.savingsPlanner(1, interest,periods);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Locale current_Locale = Locale.getDefault();
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(current_Locale);

        double sum = 0;
        for (double p: payments){
            sum += p;
            System.out.println(numberFormatter.format(p));
        }
        System.out.format("total: %s", numberFormatter.format(sum));

    }
}

