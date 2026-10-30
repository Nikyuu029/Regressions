public class MLR extends LeastSquaresRegressor {

    public MLR(double[][] x, double[] y) {
        double[][] d = new double[x.length][];
        for (int i = 0; i < x.length; i++) d[i] = row(x[i]);
        fitDesign(d, y);
    }

    private static double[] row(double[] x) {
        double[] r = new double[x.length + 1];
        r[0] = 1;
        System.arraycopy(x, 0, r, 1, x.length);
        return r;
    }

    public double predict(double[] x) { return predictRow(row(x)); }

    @Override
    public String equation() {
        StringBuilder sb = new StringBuilder("y = " + String.format("%.4f", beta[0]));
        for (int j = 1; j < beta.length; j++) sb.append(term(beta[j], "x" + j));
        return sb.toString();
    }
}
