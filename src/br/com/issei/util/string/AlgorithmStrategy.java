package br.com.issei.util.string;


/**
 *  Classe abstrata para os tipos de algoritmos.
 * 
 * @author Adenilton Soares
 *
 */
public interface AlgorithmStrategy {

	/**
	 * Obt�m o d�gito de um valor string.
	 * 
	 * @param valor
	 * @param multiplicadorArray
	 * @return
	 */
	String obterDigito(String valor, int[] multiplicadorArray);
	
	/**
	 * Calculao d�gito.
	 * 
	 * @param digito
	 * @return
	 */
	String calcularDigito(String digito);
	
}
