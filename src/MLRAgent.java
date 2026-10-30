import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MLRAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("[" + getLocalName() + "] Agente iniciado.");
        addBehaviour(new MLRBehaviour());
    }

    @Override
    protected void takeDown() {
        System.out.println("[" + getLocalName() + "] Agente finalizado.");
    }

    private class MLRBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            double[][] x = {
                {1, 2}, {2, 1}, {3, 4}, {4, 3},
                {5, 6}, {6, 5}, {7, 8}, {8, 7}
            };
            double[] y = {13.2, 12.1, 22.9, 22.2, 32.8, 31.9, 43.1, 42.0};

            MLR model = new MLR(x, y);

            System.out.println("Ecuación: " + model.equation());

            double[] b = model.getBetas();
            double[][] tests = {{3, 4}, {10, 9}, {2.5, 7}};
            for (double[] t : tests) {
                System.out.printf("Predicción: y = %.4f + %.4f * (%.1f) + %.4f * (%.1f) = %.4f%n",
                        b[0], b[1], t[0], b[2], t[1], model.predict(t));
            }

            System.out.printf("R^2 = %.6f%n", model.rSquared());

            myAgent.doDelete();
        }
    }
}
