public class SLR extends LeastSquaresRegressor {

    public SLR(double[] x, double[] y) {
        double[][] d = new double[x.length][2];
        for (int i = 0; i < x.length; i++) { d[i][0] = 1; d[i][1] = x[i]; }
        fitDesign(d, y);
    }

    public double predict(double x) { return predictRow(new double[]{1, x}); }

    @Override
    public String equation() {
        return "y = " + String.format("%.4f", beta[0]) + term(beta[1], "x");
    }
}
