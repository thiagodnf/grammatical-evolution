package operator.mutation;

import java.util.HashMap;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.operators.mutation.Mutation;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

public class IncreaseVariableLengthMutation extends Mutation {
	
	private static final long serialVersionUID = -4310660473206828238L;
	
	private Double mutationProbability_ = null;

	/**
	 * Constructor
	 */
	public IncreaseVariableLengthMutation(HashMap<String, Object> parameters) {
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
			
			int pos = PseudoRandom.randInt(0, size - 1);
			
			Variable aux = solution.getDecisionVariables()[pos].deepCopy();
			
			solution.getDecisionVariables()[pos].setValue(aux.getValue()+1);			
		} 
	}

	@Override
	public Object execute(Object object) throws JMException {
		Solution solution = (Solution) object;

		this.doMutation(mutationProbability_, solution);

		return solution;
	}

}
