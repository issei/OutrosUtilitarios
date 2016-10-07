package br.com.issei.util.string;


/**
 * Validador de CPF e CNPJ com otima performance. Sem concatenacao de strings, etc.
 *
 * @author allanjones
 *
 */
public abstract class CPFCNPJUtil {
	private static final int[][] CNPJ_MULTIPLICADORES = { { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 0, 0 },
			{ 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 0 } };

	private static final int[][] CPF_MULTIPLICADORES = { { 10, 9, 8, 7, 6, 5, 4, 3, 2, 0, 0 }, { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 0 } };
	
	private static final String CNPJ_MASK = "99.999.999/9999-99";
	private static final String CPF_MASK = "999.999.999-99";

	private static boolean check(char[] c, int[][] multiplicadores) {
		int[] digit = new int[c.length];
		int last = -1;
		int countEquals = 0;
		for (int i = 0; i < digit.length; i++) {
			digit[i] = Character.digit(c[i], 10);
			if (digit[i] < 0) {
				return false;
			}
			
			if (i == 0 || last == digit[i]) {
				countEquals++;
			}

			last = digit[i];
		}
		
		// 111.111.111-11, 222.222.222-22
		if (countEquals == digit.length) {
			return false;
		}

		return generate(digit, multiplicadores, false);
	}
	
	private static boolean generate(int[] digit, int[][] multiplicadores, final boolean generateDigits) {
		int[] verificadores = new int[2];
		for (int i = 0; i < digit.length; i++) {
			for (int j = 0; j < verificadores.length; j++) {
				verificadores[j] += digit[i] * multiplicadores[j][i];
			}
		}

		boolean changed = false;
		for (int i = 0; i < verificadores.length; i++) {
			verificadores[i] = verificadores[i] % 11;
			verificadores[i] = verificadores[i] < 2 ? 0 : 11 - verificadores[i];

			if (digit[digit.length - verificadores.length + i] != verificadores[i]) {
				if (!generateDigits) {
					return false;
				}

				digit[digit.length - verificadores.length + i] = verificadores[i];
				changed = true;
			}
		}

		if (changed) {
			// Necessario recalcular
			return generate(digit, multiplicadores, generateDigits);
		}

		return true;
	}

	protected static void generateDigitsCPF(int[] digit) {
		generate(digit, CPF_MULTIPLICADORES, true);
	}

	protected static void generateDigitsCNPJ(int[] digit) {
		generate(digit, CNPJ_MULTIPLICADORES, true);
	}

	/**
	 * 11.222.333/0001-XX
	 * 
	 */
	protected static boolean isCNPJ(String str) {
		if (str == null) {
			return false;
		}

		final String value = leftPad(str, 14);
		final int length = value.length();
		if (length == 18 && value.charAt(2) == '.' && value.charAt(6) == '.' && value.charAt(10) == '/' && value.charAt(15) == '-') {
			char[] c = new char[14];
			value.getChars(0, 2, c, 0);
			value.getChars(3, 6, c, 2);
			value.getChars(7, 10, c, 5);
			value.getChars(11, 15, c, 8);
			value.getChars(16, 18, c, 12);
			return check(c, CNPJ_MULTIPLICADORES);
		} else if (length == 16 && value.charAt(8) == '/' && value.charAt(13) == '-') {
			char[] c = new char[14];
			value.getChars(0, 8, c, 0);
			value.getChars(9, 13, c, 8);
			value.getChars(14, 16, c, 12);

			return check(c, CNPJ_MULTIPLICADORES);
		} else if (length == 14) {
			return check(value.toCharArray(), CNPJ_MULTIPLICADORES);
		}

		return false;

	}

	/**
	 * 123.456.789-09
	 * 
	 * @param value
	 * @return
	 */
	protected static boolean isCPF(String str) {
		if (str == null) {
			return false;
		}

		final String value = leftPad(str, 11);
		final int length = value.length();
		if (length == 14 && value.charAt(3) == '.' && value.charAt(7) == '.' && value.charAt(11) == '-') {
			char[] c = new char[11];
			value.getChars(0, 3, c, 0);
			value.getChars(4, 7, c, 3);
			value.getChars(8, 11, c, 6);
			value.getChars(12, 14, c, 9);

			return check(c, CPF_MULTIPLICADORES);
		} else if (length == 12 && value.charAt(9) == '-') {
			char[] c = new char[11];
			value.getChars(0, 9, c, 0);
			value.getChars(10, 12, c, 9);

			return check(c, CPF_MULTIPLICADORES);
		} else if (length == 11) {
			return check(value.toCharArray(), CPF_MULTIPLICADORES);
		}

		return false;
	}
	
	protected static String leftPad(final String value, int length) {
		return value.length() < length ? StringUtils.preencherZeroAEsquerda(length, Long.valueOf(value)) : value;
	}

	private static String formatar(Long longValue, final String mask) {

		if( mask == null ) {
			return "";
		}
		if( longValue == null ){
			longValue = new Long("0");
		}

		final StringBuilder sb = new StringBuilder(mask.length());
		sb.setLength(mask.length());

		int count = 0;
		for (int i = 0; i < mask.length(); i++) {
			final char ch = mask.charAt(i);
			if (!Character.isDigit(ch)) {
				sb.setCharAt(i, ch);
				count++;
				continue;
			}
		}
		
		final String value = longValue.toString();
		int numZeros = mask.length() - value.length() - count;
		if (numZeros < 0) {
			throw new IllegalArgumentException("Valor a ser formatado " + longValue + " eh maior que a mascara " + mask);
		}

		int longIndex = 0;
		
		for (int i = 0; i < mask.length(); i++) {
			final char ch = mask.charAt(i);
			if (!Character.isDigit(ch)) {
				continue;
			}
			
			if (numZeros > 0) {
				sb.setCharAt(i, '0');
				numZeros--;
				continue;
			}
			
			sb.setCharAt(i, value.charAt(longIndex++));
		}

		return sb.toString();
	}
	
	protected static String formatarCPF(final Long longValue) {
		return formatar(longValue, CPF_MASK);
	}

	protected static String formatarCNPJ(final Long longValue) {
		return formatar(longValue, CNPJ_MASK);
	}
}
