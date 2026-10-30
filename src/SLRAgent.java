import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class SLRAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("[" + getLocalName() + "] Agente iniciado.");
        addBehaviour(new SLRBehaviour());
    }

    @Override
    protected void takeDown() {
        System.out.println("[" + getLocalName() + "] Agente finalizado.");
    }

    private class SLRBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            // 1. Dataset hardcodeado
            double[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            double[] y = {191, 214, 238, 259, 283, 305, 330, 352, 374, 399};

            SLR model = new SLR(x, y);

            System.out.println("Ecuación: " + model.equation());

            double[] tests = {5, 10, 17};
            for (double t : tests) {
                System.out.printf("Predicción: y = %.4f + %.4f * (%.1f) = %.4f%n",
                        model.getBetas()[0], model.getBetas()[1], t, model.predict(t));
            }


            System.out.printf("R^2 = %.6f%n", model.rSquared());

            myAgent.doDelete();
        }
    }
}
