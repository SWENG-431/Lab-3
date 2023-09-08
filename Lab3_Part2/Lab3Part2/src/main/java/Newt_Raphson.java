import java.util.Random;

/**
 *
 * @author Nicole Vadillo, Brian Karimi, Katie Killian
 */
public class Newt_Raphson {
    // Function f'(x) = 4x^3-y-1
    public static double f(double x, double y) {
        return 4 * Math.pow(x, 3) - y - 1;
    }

    // Second derivative f''(x) = 12x^2
    public static double f2(double x) {
        return 12 * Math.pow(x, 2);
    }

    // Function g'(y) = 12y^5-x
    public static double g(double x, double y) {
        return 12 * Math.pow(y, 5) - x;
    }

    // Second derivative g''(y) = 60y^4
    public static double g2(double y) {
        return 60 * Math.pow(y, 4);
    }

    // Function to find local min/max using Newton-Raphson method
    public static double[] findMinMax(double x0, double y0) {
        double epsilon = 0.00001;
        int maxIterations = 10000;
        double x = x0;
        double y = y0;
        int iterations = 0;
        double newX;
        double newY;

        while (iterations < maxIterations)
        {
            //Newton-Raphson's method xn+1 = xn – f '(xn) / f ''(xn)
            newX = x - f(x, y) / f2(x);
            newY = y - g(newX, y) / g2(y);
            
            /*
            program finds a local minimum or a local maximum 
            when the difference between the new solution 
            and the previous one is smaller than 0.00001 
            within 10000 iterations:
            */
            if (Math.abs(newX - x) < epsilon && Math.abs(newY - y) < epsilon) {
                return new double[]{newX, newY};
            }

            x = newX;
            y = newY;
            iterations++;
        }
        // Otherwise return infinity 
        return new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
    }

    public static void main(String[] args) {
        
        Random random = new Random();
        
        // Initial guesses within the precondition: |x| ≤ 6 and |y| ≤ 5
        double x0 = (random.nextDouble() * 12) - 6;
        double y0 = (random.nextDouble() * 10) - 5;


        double[] result = findMinMax(x0, y0);

        if (result[0] == Double.POSITIVE_INFINITY) {
            System.out.println("No local minimum or maximum found within 10,000 iterations.");
        } else {
            System.out.println("Local minimum or maximum found at x = " + result[0] + ", y = " + result[1]);
        }
    }
}

