package br.com.issei.util.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaria para o gerador de CPF.
 * @author Fernando R Galha.
 *
 */
public class CPFGeradorUtil {
	
	private CPFGeradorUtil() {
	}
	
	/**
	 * eg: number = 1234, returns { 1, 2, 3, 4 }.
	 *
	 * @param number
	 *            a long, see Long.MAX_VALUE and Long.MIN_VALUE
	 * @return one array of digits
	 */
	public static int[] longToArray(final long number) {
		long n = number;
		List<Long> list = new ArrayList<Long>(10);
		do {
			list.add(n % 10);
			n /= 10;
		} while (n != 0);
		int length = list.size();
		int[] digits = new int[length];
		for (int i = length - 1, j = 0; i >= 0; i--, j++) {
			digits[j] = list.get(i).intValue();
		}
		
		return digits;
	}

	/**
	 * eg: number = { 1, 2, 3, 4 }, returns a long = 1234 if the number
	 * correspondent of digits is bigger than Long.MAX_VALUE, so Long.MAX_VALUE
	 * is returned.
	 *
	 * @param digits
	 *            an array of digits [0-9]
	 * @return a long correspondent to the digits
	 */
	public static long arrayToLong(final int[] digits) {
		long result = 0;
		long pot = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			long partial = digits[i] * pot;
			if (Long.MAX_VALUE - result - partial > 0) {
				result += partial;
			} else {
				return Long.MAX_VALUE;				
			}
			pot *= 10;
		}
		return result;
	}

}