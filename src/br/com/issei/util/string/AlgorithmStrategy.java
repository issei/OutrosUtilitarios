package br.com.issei.util.string;


/**
 *  Classe abstrata para os tipos de algoritmos.
 * 
 * @author Adenilton Soares
 *
 */
public interface AlgorithmStrategy {

	/**
	 * Obtém o dígito de um valor string.
	 * 
	 * @param valor
	 * @param multiplicadorArray
	 * @return
	 */
	String obterDigito(String valor, int[] multiplicadorArray);
	
	/**
	 * Calculao dígito.
	 * 
	 * @param digito
	 * @return
	 */
	String calcularDigito(String digito);
	
}
