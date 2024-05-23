package savesplanner;

import java.lang.Math;
// import org.apache.commons.math3.analysis.solvers.BisectionSolver;
// import org.apache.commons.math3.analysis.UnivariateFunction;
// import org.apache.commons.math3.util.CombinatoricsUtils;


// class NewtonBinomial implements UnivariateFunction{
//     /*
//      * TODO:
//      * Habría que abstraer un poco los precios de la inflación.
//      * Sólo debe calcular la inflación por periodo
//      * 
//      * Estoy bastante seguro que esto se puede simplificar bastante.
//      * (1+x)^p = pe
//      * x = pe^(1/p)-1
//      */
//     private int periods;
//     // private double startPrice;
//     private double finalInterest;
//     public NewtonBinomial(double finalInterest, int periodos){
//         this.periods = periodos;
//         // this.startPrice = startPrice;
//         this.finalInterest = finalInterest;
//     }
//     public long[] coefficients(){
//         int n = this.periods;
//         long[] res = new long[n+1];
//         for (int i = 0; i <= n; i++ ){
//             res[i] = CombinatoricsUtils.binomialCoefficient(n, i);
//         }
//         return res;
//     }
//     public double value(double x){
//         // double startValue = 1;
//         double res = 0;
//         long[] coeffs = this.coefficients();
//         for (int i = 0; i < coeffs.length; i++){
//             res += coeffs[i] * Math.pow(x, i);
//         }

//         return res - finalInterest;
//     }
// }

public class Calculator {
    public static double futurePrice(double currentPrice, double interestRate, int periods){
        double price = currentPrice;

        for (int i = 1; i <= periods; i++){
            price += price * interestRate;    
        }

        return price;
    }
    public static Porcentage interestForPeriod(Porcentage finalInterest, int periods){

        // Probemos luego con varios "solvers"
        // BisectionSolver solver = new BisectionSolver();
        // double res = solver.solve(1000, function, 0,1000);

        double value = Math.pow(
            finalInterest.increaseMultiplier(), 
            1/((double) periods)
        );

        double doubleRes = value - 1;

        Porcentage res = new Porcentage(doubleRes*100);
        
        return res;
    }
    public static double[] savingsPlanner(double goal, Porcentage interest, int periods) throws Exception{
        if (goal <= 0){
            throw new Exception("the savings goal must be greather than 0");
        }
        if (periods <= 0){
            throw new Exception("periods must be a positive integer");
        }
        if (interest.increaseMultiplier() < 1){
            throw new Exception("estimated interest must be double number greather than 1");
        }

        double pagado = 0;
        double[] res = new double[periods];
        double finalGoal = goal;
        Porcentage ipp = Calculator.interestForPeriod(interest, periods);

        System.out.println("ipp: "+ipp);
        for (int i = 0; i < periods; i++){
            finalGoal += finalGoal * ipp.increaseMultiplier();
            double cuota = (finalGoal-pagado)/(periods-i);
            pagado += cuota;
            res[i] = cuota;
        }

        // double[] res = {0.0,0.1};
        return res;
    }
    public static double updateInflationPerPeriod(double[] pastPeriodsInflation, double prediction, int periods) throws Exception{
        int calulatedPeriods = pastPeriodsInflation.length;
        if (calulatedPeriods >= periods){
            throw new Exception("all periods are calculated");
        }

        double accumulated = 1;
        for (double inflation : pastPeriodsInflation){
            accumulated *= (1+inflation);
        }

        double pow = 1/(((double) periods)-((double) calulatedPeriods));
        double res = Math.pow((prediction/accumulated), pow);
        return res;
    }

    public static void main(String[] args){
        System.out.println("Test 'savingsPlanner'");

        int periods = 2;
        Porcentage interesTotal = new Porcentage(50);
        // double goal = Math.pow(10, 6);

        Porcentage ipp = Calculator.interestForPeriod(interesTotal, periods);

        System.out.println("ipp:   "+ipp.value());
        System.out.println("total: "+Math.pow(ipp.increaseMultiplier(), periods));

        // System.out.println("Test 'updateInflationPerPeriod'");

        // double[] ppi = {0.10,0.12,0.08};
        // double ipp = 0.0;
        // try {
        //     ipp = updateInflationPerPeriod(ppi, 2, 5);
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // System.out.println("ipp: "+ipp);
        
        // System.out.println(Math.pow(2/1.33, 1.0/2.0));
    }
}
