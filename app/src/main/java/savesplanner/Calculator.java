package savesplanner;

import java.lang.Math;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.UnivariateFunction;

class NewtonBinomial implements UnivariateFunction{
    public double value(double x){
        double c = 100.0; // coefficient
        return -c + 3*c*x + 3*c*x*Math.pow(x, 2) + c*Math.pow(x, 3);
    }
}

public class Calculator {
    public static double futurePrice(double currentPrice, double interestRate, int periods){
        double price = currentPrice;

        for (int i = 1; i <= periods; i++){
            price += price * interestRate;    
        }

        return price;
    }
    public static double interestForPeriod(double finalInterest){
        NewtonBinomial function = new NewtonBinomial();
        BisectionSolver solver = new BisectionSolver();
        double res = solver.solve(1000, function, 0,1000);
        
        return res;
    }
}
