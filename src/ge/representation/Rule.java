package ge.representation;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	protected String nonTerminal;

	protected List<String> productions;

	public Rule(String nonTerminal, List<String> productions) {
		this.nonTerminal = nonTerminal;
		this.productions = productions;
	}

	public Rule(String nonTerminal) {
		this(nonTerminal, new ArrayList<String>());
	}

	public void addProductions(String production) {
		this.productions.add(production);
	}
	
	public String getNonTerminal() {
		return nonTerminal;
	}

	public void setNonTerminal(String nonTerminal) {
		this.nonTerminal = nonTerminal;
	}

	public List<String> getProductions() {
		return productions;
	}

	public void setProductions(List<String> productions) {
		this.productions = productions;
	}

	@Override
	public String toString() {
		return nonTerminal + " ::= " + productions;
	}
}
