package operator.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.operators.crossover.Crossover;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

public class SinglePointVariableLengthCrossover extends Crossover {

	private static final long serialVersionUID = -5456657555812428544L;

	protected Double crossoverProbability_ = null;

	public SinglePointVariableLengthCrossover(HashMap<String, Object> parameters) {
		super(parameters);

		if (parameters.get("probability") != null) {
			crossoverProbability_ = (Double) parameters.get("probability");
		}else{
			crossoverProbability_ = 0.9;
		}
	}
	
	public Solution[] doCrossover(double probability, Solution parent1, Solution parent2) throws JMException {
		Solution[] offSpring = new Solution[2];
		
	    offSpring[0] = new Solution(parent1);
	    offSpring[1] = new Solution(parent2);
	    
	    if (PseudoRandom.randDouble() < probability) {
	    	
	    	 // 1. Get the total number of bits
            int totalNumberOfGenesParent1 = offSpring[0].getDecisionVariables().length;
            int totalNumberOfGenesParent2 = offSpring[1].getDecisionVariables().length;

            // 2. Calculate the point to make the crossover
            int crossoverPointParent1 = PseudoRandom.randInt(0, totalNumberOfGenesParent1 - 1);
            int crossoverPointParent2 = PseudoRandom.randInt(0, totalNumberOfGenesParent2 - 1);
            
            List<Variable> varForOffspring0 = new ArrayList<Variable>();
            List<Variable> varForOffspring1 = new ArrayList<Variable>();
            
            for (int i = 0; i < crossoverPointParent1; i++) {
            	varForOffspring0.add(parent1.getDecisionVariables()[i]);
            }
            for (int i = 0; i < crossoverPointParent2; i++) {
            	varForOffspring1.add(parent2.getDecisionVariables()[i]);
            }
            for (int i = crossoverPointParent1; i < totalNumberOfGenesParent1; i++) {
            	varForOffspring1.add(parent1.getDecisionVariables()[i]);
            }
            for (int i = crossoverPointParent2; i < totalNumberOfGenesParent2; i++) {
            	varForOffspring0.add(parent2.getDecisionVariables()[i]);
            }
            
			offSpring[0].setDecisionVariables(varForOffspring0.toArray(new Variable[varForOffspring0.size()]));
			offSpring[1].setDecisionVariables(varForOffspring1.toArray(new Variable[varForOffspring1.size()]));
		}
		
	    return offSpring;
	}

	@Override
	public Object execute(Object object) throws JMException {
		Solution[] parents = (Solution[]) object;

		if (parents.length < 2) {
			throw new JMException(this.getClass().getCanonicalName()+" operator needs two parents");
		}

		Solution[] offSpring = doCrossover(crossoverProbability_, parents[0], parents[1]);
		
		//-> Update the offSpring solutions
	    for (int i = 0; i < offSpring.length; i++) {
	      offSpring[i].setCrowdingDistance(0.0);
	      offSpring[i].setRank(0);
	    }

	    return offSpring;
	}
}
