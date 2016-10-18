package util;

import jmetal.core.Solution;
import jmetal.encodings.variable.Binary;
import jmetal.encodings.variable.Permutation;

public class ConvertUtils {
	/**
	 * Convert from string array to double array
	 * 
	 * @param array
	 *            Array will be converted
	 * 
	 * @return A double array
	 */
	public static double[] toDoubleArray(String[] array) {
		if (array == null) {
			throw new IllegalArgumentException("array shouldn't be null");
		}

		double[] result = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			result[i] = Double.valueOf(array[i].trim());
		}

		return result;
	}

	/**
	 * Convert from string array to int array
	 * 
	 * @param array
	 *            Array will be converted
	 * @return A int array
	 */
	public static int[] toIntArray(String[] array) {
		if (array == null) {
			throw new IllegalArgumentException("array shouldn't be null");
		}

		int[] result = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			result[i] = Integer.valueOf(array[i].trim());
		}

		return result;
	}
	
	public static String[] trim(String[] array) {

		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}

		return array;
	}
	
	public static String[] removeSpaces(String[] array){
		
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].replaceAll(" ","");
		}

		return array;
	}
	
	public static int[] fromSolutionToIntArray(Solution solution) {
		Binary variable = ((Binary) solution.getDecisionVariables()[0]);

		int[] array = new int[variable.getNumberOfBits()];

		for (int i = 0; i < variable.getNumberOfBits(); i++) {
			array[i] = variable.bits_.get(i) ? 1 : 0;
		}

		return array;
	}
	
	public static int[] fromIntegerSolutionToIntArray(Solution solution) {
		Permutation variable = ((Permutation) solution.getDecisionVariables()[0]);

		return variable.vector_;
	}
	
	public static String fromSolutionToString(Solution solution) {
		Binary variable = ((Binary) solution.getDecisionVariables()[0]);

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < variable.getNumberOfBits(); i++) {
			builder.append(variable.bits_.get(i) ? 1 : 0);
		}

		return builder.toString();
	}
}
