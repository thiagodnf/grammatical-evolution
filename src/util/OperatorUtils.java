package util;

import java.util.HashMap;
import jmetal.core.Operator;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.util.JMException;

@SuppressWarnings({"rawtypes","unchecked"})
public class OperatorUtils {
	
	public static Operator getCrossover(String name, double probability) throws JMException{
		HashMap parameters = new HashMap();
		
		parameters.put("probability", probability) ;
		
		return CrossoverFactory.getCrossoverOperator(name, parameters);
	}
	
	public static Operator getMutation(String name, double probability) throws JMException{
		HashMap parameters = new HashMap();
		
		parameters.put("probability", probability) ;
		
		return MutationFactory.getMutationOperator(name, parameters);
	}
	
	public static Operator getSelection(String name) throws JMException{
		return SelectionFactory.getSelectionOperator(name, null);
	}

}
