public abstract class LeastSquaresRegressor {

    protected double[] beta;
    protected double[][] design;
    protected double[] y;

    protected void fitDesign(double[][] design, double[] y) {
        this.design = design;
        this.y = y;
        int n = design.length, p = design[0].length;

        double[][] xtx = new double[p][p];
        double[] xty = new double[p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                xty[j] += design[i][j] * y[i];
                for (int k = 0; k < p; k++) xtx[j][k] += design[i][j] * design[i][k];
            }
        }
        beta = solve(xtx, xty);
    }

    private static double[] solve(double[][] a, double[] b) {
        int n = b.length;
        for (int col = 0; col < n; col++) {
            int piv = col;
            for (int r = col + 1; r < n; r++)
                if (Math.abs(a[r][col]) > Math.abs(a[piv][col])) piv = r;
            double[] tmpRow = a[col]; a[col] = a[piv]; a[piv] = tmpRow;
            double tmp = b[col]; b[col] = b[piv]; b[piv] = tmp;
            if (Math.abs(a[col][col]) < 1e-12)
                throw new IllegalStateException("no se pueden calcular las betas.");
            for (int r = col + 1; r < n; r++) {
                double f = a[r][col] / a[col][col];
                for (int c = col; c < n; c++) a[r][c] -= f * a[col][c];
                b[r] -= f * b[col];
            }
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double s = b[i];
            for (int j = i + 1; j < n; j++) s -= a[i][j] * x[j];
            x[i] = s / a[i][i];
        }
        return x;
    }

    protected double predictRow(double[] row) {
        double s = 0;
        for (int j = 0; j < beta.length; j++) s += beta[j] * row[j];
        return s;
    }

    public double rSquared() {
        double mean = 0;
        for (double v : y) mean += v;
        mean /= y.length;
        double sse = 0, sst = 0;
        for (int i = 0; i < y.length; i++) {
            double e = y[i] - predictRow(design[i]);
            sse += e * e;
            sst += (y[i] - mean) * (y[i] - mean);
        }
        return 1.0 - sse / sst;
    }

    public double[] getBetas() { return beta; }

    protected static String term(double coef, String name) {
        String sign = coef < 0 ? " - " : " + ";
        return sign + String.format("%.4f", Math.abs(coef)) + name;
    }

    public abstract String equation();
}
