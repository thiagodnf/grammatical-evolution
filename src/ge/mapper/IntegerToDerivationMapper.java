package ge.mapper;

import java.util.List;
import ge.representation.Grammar;
import util.GrammarUtils;

public class IntegerToDerivationMapper extends Mapper {
	
	public String map(Grammar grammar, int[] codons) {

		String rule = grammar.getStartSymbol();
		
		String currentNonTerminal = GrammarUtils.getNextNonTerminalSymbol(rule);
		
		int position = 0;
		
		while (currentNonTerminal != null) {
			
			List<String> productions = grammar.getRule(currentNonTerminal).getProductions();
			
			int numberOfAlternatives = productions.size();
			
			int alternative = codons[position] % numberOfAlternatives;
			
			String nextRule = productions.get(alternative);
			
			rule = rule.replaceFirst(currentNonTerminal, nextRule);
			
			if (position + 1 == codons.length) {
				position = 0;
			} else {
				position++;
			}
			
			if(rule.length() > 200) {
				break;
			}
			
			currentNonTerminal = GrammarUtils.getNextNonTerminalSymbol(rule);
		}
		
		return rule;
	}
}
