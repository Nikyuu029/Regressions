import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class PRAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("[" + getLocalName() + "] Agente iniciado.");
        addBehaviour(new PRBehaviour());
    }

    @Override
    protected void takeDown() {
        System.out.println("[" + getLocalName() + "] Agente finalizado.");
    }

    private class PRBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            // 1. Dataset hardcodeado
            double[] x = {1, 2, 3, 4, 5, 6, 7, 8};
            double[] y = {5.9, 9.6, 15.9, 21.5, 30.1, 37.6, 48.0, 57.7};
            int degree = 2;

            PR model = new PR(x, y, degree);

            System.out.println("\n PR (grado " + degree + ") ");
            System.out.println("Ecuación: " + model.equation());

            double[] b = model.getBetas();
            double[] tests = {3, 6, 10};
            for (double t : tests) {
                System.out.printf("Predicción: y = %.4f + %.4f * (%.1f) + %.4f * (%.1f)^2 = %.4f%n",
                        b[0], b[1], t, b[2], t, model.predict(t));
            }

            System.out.printf("R^2 = %.6f%n", model.rSquared());

            myAgent.doDelete();
        }
    }
}
