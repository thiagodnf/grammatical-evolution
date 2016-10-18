package ge.representation;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
	
	protected List<Rule> rules;
	
	public Grammar() {
		this(new ArrayList<Rule>());
	}

	public Grammar(List<Rule> rules) {
		this.rules = rules;
	}

	public void appendRule(Rule rule) {
		this.rules.add(rule);		
	}
	
	public String getStartSymbol() {
		return this.rules.get(0).getNonTerminal();
	}	
	
	public Rule getRule(String nonTerminal) {
		for (Rule rule : this.rules) {
			if (rule.getNonTerminal().equalsIgnoreCase(nonTerminal)) {
				return rule;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Start Symbol: ").append(getStartSymbol()).append("\n");

		for (Rule rule : this.rules) {
			builder.append(rule).append("\n");
		}

		return builder.toString();
	}

	
}
