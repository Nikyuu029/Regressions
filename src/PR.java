public class PR extends LeastSquaresRegressor {

    private final int degree;

    public PR(double[] x, double[] y, int degree) {
        this.degree = degree;
        double[][] d = new double[x.length][];
        for (int i = 0; i < x.length; i++) d[i] = row(x[i]);
        fitDesign(d, y);
    }

    private double[] row(double x) {
        double[] r = new double[degree + 1];
        for (int j = 0; j <= degree; j++) r[j] = Math.pow(x, j);
        return r;
    }

    public double predict(double x) { return predictRow(row(x)); }

    @Override
    public String equation() {
        StringBuilder sb = new StringBuilder("y = " + String.format("%.4f", beta[0]));
        for (int j = 1; j <= degree; j++)
            sb.append(term(beta[j], j == 1 ? "x" : "x^" + j));
        return sb.toString();
    }
}
