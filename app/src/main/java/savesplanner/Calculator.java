package savesplanner;

import java.lang.Math;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.UnivariateFunction;

class NewtonBinomial implements UnivariateFunction{
    public double value(double x){
        /* 
         * TODO:
         * - [ ] Crear el polinomio a partir de un número de periodos
         */
        double c = 100.0; // coefficient
        return -c + 3*c*x + 3*c*x*Math.pow(x, 2) + c*Math.pow(x, 3);
    }
}

public class Calculator {
    public static double futurePrice(double currentPrice, double interestRate, int periods){
        /*
         * FIXME:
         * 
         * Comportamiento extraño
         * currentPrice: 100
         * interesRate: 0.29803579673171043
         * Salida: 218.7056528420159
         * Salida esperada: 200
         * 
         * ¿Qué pasó?
         */
        double price = currentPrice;

        for (int i = 1; i <= periods; i++){
            price += price * interestRate;    
        }

        return price;
    }
    public static double interestForPeriod(double finalInterest){
        NewtonBinomial function = new NewtonBinomial();

        // Probemos luego con varios "solvers"
        BisectionSolver solver = new BisectionSolver();
        double res = solver.solve(1000, function, 0,1000);
        
        return res;
    }
}
