import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.singleObjective.geneticAlgorithm.gGA;
import jmetal.util.JMException;
import problem.WordMatchProblem;
import util.OperatorUtils;

public class GAExplore {

	public static void main(String[] args) throws JMException, ClassNotFoundException {
		System.out.println("Running...");

		WordMatchProblem p = new WordMatchProblem("grammar/letter.bnf", "thiagodnf", 10);

		// Generational GA
		Algorithm algorithm = new gGA(p);

		/* Algorithm parameters */
		algorithm.setInputParameter("populationSize", 100);
		algorithm.setInputParameter("maxEvaluations", 500000);

		/* Operators */
		Operator crossover = OperatorUtils.getCrossover("SinglePointVariableLengthCrossover", 0.9);
		Operator mutation = OperatorUtils.getMutation("SwapVariableLengthMutation", 0.05);
		Operator selection = OperatorUtils.getSelection("BinaryTournament");

		/* Add the operators to the algorithm */
		algorithm.addOperator("crossover", crossover);
		algorithm.addOperator("mutation", mutation);
		algorithm.addOperator("selection", selection);

		/* Execute the Algorithm */
		long initTime = System.currentTimeMillis();
		SolutionSet population = algorithm.execute();
		long estimatedTime = System.currentTimeMillis() - initTime;
		
		System.out.println("Result: " + p.convert(population.get(0)));		
		System.out.println("Done. Total execution time: " + estimatedTime);
	}
}
