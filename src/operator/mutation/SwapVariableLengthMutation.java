package operator.mutation;

import java.util.HashMap;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.operators.mutation.Mutation;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

public class SwapVariableLengthMutation extends Mutation {
	
	private static final long serialVersionUID = -4310660473206828238L;
	
	private Double mutationProbability_ = null;

	/**
	 * Constructor
	 */
	public SwapVariableLengthMutation(HashMap<String, Object> parameters) {
		super(parameters);

		if (parameters.get("probability") != null) {
			mutationProbability_ = (Double) parameters.get("probability");
		} else {
			mutationProbability_ = 0.005;
		}
	}

	public void doMutation(double probability, Solution solution) throws JMException {
		
		if (PseudoRandom.randDouble() < probability) {
			
			int size = solution.getDecisionVariables().length;
			
			int pos1 = PseudoRandom.randInt(0, size - 1);
			int pos2 = PseudoRandom.randInt(0, size - 1);
			
		
		/*	while (pos1 == pos2) {
				if (pos1 == (size - 1))
					pos2 = PseudoRandom.randInt(0, size - 2);
				else
					pos2 = PseudoRandom.randInt(pos1, size - 1);
			}*/
			
			Variable aux = solution.getDecisionVariables()[pos1].deepCopy();
			solution.getDecisionVariables()[pos1] = solution.getDecisionVariables()[pos2];
			solution.getDecisionVariables()[pos2] = aux;
		} 
	}

	@Override
	public Object execute(Object object) throws JMException {
		Solution solution = (Solution) object;

		this.doMutation(mutationProbability_, solution);

		return solution;
	}

}
