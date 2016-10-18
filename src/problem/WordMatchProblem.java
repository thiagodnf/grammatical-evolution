package problem;

import ge.mapper.IntegerToDerivationMapper;
import ge.representation.Grammar;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.PermutationSolutionType;
import jmetal.util.JMException;
import reader.GrammarReader;
import util.ConvertUtils;
import util.GrammarUtils;

public class WordMatchProblem extends Problem{

	private static final long serialVersionUID = -7340773541993499401L;
	
	protected Grammar grammar;
	
	protected String expectedName;
	
	public WordMatchProblem(String grammar, String expectedName) {
		this(grammar, expectedName, 150);
	}

	public WordMatchProblem(String grammar, String expectedName, int maxInteger) {
		// JMetal
		numberOfVariables_ = 1;
		numberOfObjectives_ = 1;
		numberOfConstraints_ = 0;
		problemName_ = this.getClass().getCanonicalName();
		solutionType_ = new PermutationSolutionType(this);
		length_ = new int[numberOfVariables_];
		length_[0] = maxInteger;

		// Problem
		this.expectedName = expectedName;
		this.grammar = new GrammarReader(grammar).getGrammar();
	}

	@Override
	public void evaluate(Solution solution) throws JMException {

		String derivation = convert(solution);

		if (GrammarUtils.isValid(derivation)) {
			solution.setObjective(0, value(expectedName, derivation));
		} else {
			solution.setObjective(0, Integer.MAX_VALUE);
		}
	}
	
	public String convert(Solution solution){
		int[] codons = ConvertUtils.fromIntegerSolutionToIntArray(solution);
		
		return new IntegerToDerivationMapper().map(grammar, codons);
	}
	
	public double value(String name, String result) {

		int minLength = Math.min(result.length(), name.length());
		double tmpFit = Math.max(result.length(), name.length());

		for (int i = 0; i < minLength; i++) {
			if (result.charAt(i) == name.charAt(i)) {
				tmpFit--;
			}
		}
		return tmpFit;
	}
}
