package reader;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import ge.representation.Grammar;
import ge.representation.Rule;
import util.ConvertUtils;

public class GrammarReader extends Reader {

	public GrammarReader(String filename) {
		super(filename);
	}

	public Rule getRule(String s) throws IOException {

		checkNotNull(s, "The argument was null but expected to be non-nul");
		checkArgument(!s.isEmpty(), "The argument was empty but expected to be non-empty");
		
		String[] array = s.split("::=");
		
		array = ConvertUtils.removeSpaces(array);
		
		Rule rule = new Rule(array[0]);
		
		String[] productions = array[1].split("\\|");
		
		productions = ConvertUtils.removeSpaces(productions);
		
		for (int i = 0; i < productions.length; i++) {
			rule.addProductions(productions[i]);
		}
		
		return rule;
	}
	
	public Grammar getGrammar(){
		
		Grammar grammar = new Grammar();
		
		try {
			// Before read the file, it is necessary to open it
			open();
			
			// Read the first line of the file
			String line = readLine();
			
			// Read all lines of the file
			while (line != null) {
				// Convert the string into a rule object and append it into the grammar
				grammar.appendRule(getRule(line));
				
				//Read the next line of the file
				line = readLine();
			}

			//After read all lines, close the file
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return grammar;
	}

}
