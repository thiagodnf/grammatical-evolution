package ge.representation;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
	
	private String startSymbol;
	
	protected List<Rule> rules;
	
	public Grammar(String startSymbol, List<Rule> rules) {
		this.setStartSymbol(startSymbol);
		this.rules = rules;
	}

	public Grammar(String startSymbol) {
		this(startSymbol, new ArrayList<Rule>());
	}

	public Grammar() {
		this("<string>");
	}

	public void appendRule(Rule rule) {
		this.rules.add(rule);		
	}
	
	public String getStartSymbol() {
		return startSymbol;
	}

	public void setStartSymbol(String startSymbol) {
		this.startSymbol = startSymbol;
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
