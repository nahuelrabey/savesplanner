package savesplanner;

import java.lang.Math;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.util.CombinatoricsUtils;


class NewtonBinomial implements UnivariateFunction{
    /*
     * TODO:
     * Habría que abstraer un poco los precios de la inflación.
     * Sólo debe calcular la inflación por periodo
     */
    private int periods;
    // private double startPrice;
    private double finalInterest;
    public NewtonBinomial(double finalInterest, int periodos){
        this.periods = periodos;
        // this.startPrice = startPrice;
        this.finalInterest = finalInterest;
    }
    public long[] coefficients(){
        int n = this.periods;
        long[] res = new long[n+1];
        for (int i = 0; i <= n; i++ ){
            res[i] = CombinatoricsUtils.binomialCoefficient(n, i);
        }
        return res;
    }
    public double value(double x){
        // double startValue = 1;
        double res = 0;
        long[] coeffs = this.coefficients();
        for (int i = 0; i < coeffs.length; i++){
            res += coeffs[i] * Math.pow(x, i);
        }

        return res - finalInterest;
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
    public static double interestForPeriod(double finalInterest, int periods){
        NewtonBinomial function = new NewtonBinomial(finalInterest, periods);

        // Probemos luego con varios "solvers"
        BisectionSolver solver = new BisectionSolver();
        double res = solver.solve(1000, function, 0,1000);
        
        return res;
    }
}
