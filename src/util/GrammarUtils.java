package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarUtils {
	
	public static String getNextNonTerminalSymbol(String string){
		String regex = "\\<\\w*\\>";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);

		if(matcher.find()){
			return matcher.group(0);
		}

		return null;
	}

	public static boolean isValid(String derivation) {
		return getNextNonTerminalSymbol(derivation) == null;
	}
}
