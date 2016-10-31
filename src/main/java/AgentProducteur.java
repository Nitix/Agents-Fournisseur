import sun.management.Agent;

/**
 * Created by Arthur on 31/10/2016.
 */
import jade.core.Agent;

public class AgentProducteur extends Agent{

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis un producteur !");
    }
}
