package ge.mapper;

import ge.representation.Grammar;

public abstract class Mapper {

	public abstract String map(Grammar grammar, int[] codons);
}
