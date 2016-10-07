package br.com.issei.util.string;

import java.util.Random;


/**
 * Classe que gera CPFs aleatórios.
 *
 * @author Fernando R Galha.
 */
public class CPFGerador extends CPFCNPJUtil {
	private CPFGerador() {
	}
	
	/**
	 * Gera cpfs aleatórios a partir de um objeto Random passado.
	 * @param random
	 * @return
	 */
	public static Long generate(Random random) {
		int[] digits = NumeroGerador.generateArray(random, 11, 9);
		generateDigitsCPF(digits);
		
		return CPFGeradorUtil.arrayToLong(digits);
	}

	/**
	 * @return 11 digits ############
	 */
	public static Long generateRandom() {
		return generate(new Random());
	}

	/**
	 * @return 11 digits ############
	 */
	public static String generateRandomAsString() {
		return StringUtils.preencherZeroAEsquerda(11, generateRandom());
	}

	/**
	 * @param seed
	 *            an indice
	 * @return 11 digits ############
	 */
	public static Long generate(long seed) {
		return generate(new Random(seed));
	}

	/**
	 * Poe zeros no comeco do cpf se tem menos de 11 digitos.
	 *
	 * @return "###.###.###-##"
	 */
	public static String format(Long cpf) {
		return formatarCPF(cpf);
	}

	/**
	 * @return "###.###.###-##"
	 */
	public static String generateRandomFormatted() {
		return format(generateRandom());
	}
}