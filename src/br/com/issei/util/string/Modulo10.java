package br.com.issei.util.string;


/**
 * Classe Singleton respons&aacute;vel pelo algoritmo Módulo 10.
 * 
 * @author Adenilton Soares
 * 
 */
public class Modulo10 implements AlgorithmStrategy {
	
	private static Modulo10 instance = new Modulo10();
		
	public static Modulo10 getInstance() {
		return instance;
	}
	
	/**
	 * Calcula o d&iacute;gito verificador.
	 * 
	 * @param digStr
	 * @return
	 */
	public String calcularDigito(String digStr) {
		return null;
	}

	/**
	 * Recupera o d&iacute;gito.
	 * 
	 * @param strSemDigito
	 * @param multiplicadorArray
	 * @return
	 */
	public String obterDigito(String strSemDigito, int[] multiplicadorArray) {
		return null;
	}
	
}
