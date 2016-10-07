package br.com.issei.util.string;


/**
 * Classe Singleton respons&aacute;vel pelo algoritmo Módulo 11.
 * 
 * @author usuario
 * 
 */
public class Modulo11 implements AlgorithmStrategy {
	
	private static Modulo11 instance = new Modulo11();
	
	public static Modulo11 getInstance() {
		return instance;
	}

	/**
	 * Calcula o d&iacute;gito verificador.
	 * 
	 * @param digStr
	 * @return
	 */
	public String calcularDigito(String digStr) {
		int len = digStr.length();
		int sum = 0, rem = 0;

		for (int k = 1; k <= len; k++) {
			int numericValue = Character.getNumericValue(digStr.charAt(k - 1));
			sum += (11 - k) * numericValue;
		}
		rem = sum % 11;
		if (rem == 0) {
			return "0";
		} else if (rem == 1) {
			return "X";
		}
		return (new Integer(11 - rem)).toString();
	}

	/**
	 * Recupera o d&iacute;gito.
	 * 
	 * @param strSemDigito
	 * @param multiplicadorArray
	 * @return
	 */
	public String obterDigito(String strSemDigito, int[] multiplicadorArray) {
		strSemDigito = StringUtils.preencherZeroAEsquerda(multiplicadorArray.length, Long.valueOf(strSemDigito));
		int digitoVerificador = -1;
		int sum = 0;
		for (int i = 0; i < strSemDigito.length(); i++) {
			char charAt = strSemDigito.charAt(i);
			int charAtAsNumber = Character.getNumericValue(charAt);
			int multiplicador = multiplicadorArray[i];
			int multiplicacao = charAtAsNumber * multiplicador;
			sum += multiplicacao;
		}
		int mod = sum % 11;
		if (mod < 2) {
			digitoVerificador = 0;
		} else {
			digitoVerificador = 11 - mod;
		}

		return String.valueOf(digitoVerificador);
	}

	/**
	 * Recupera o d&iacute;gito.
	 * 
	 * @param strSemDigito
	 * @param multiplicadorArray
	 * @return
	 */
	public String obterDigitoRegistroPostal(String strSemDigito, int[] multiplicadorArray) {
		strSemDigito = StringUtils.preencherZeroAEsquerda( multiplicadorArray.length,Long.valueOf(strSemDigito));
		int digitoVerificador = -1;
		int sum = 0;
		for (int i = 0; i < strSemDigito.length(); i++) {
			char charAt = strSemDigito.charAt(i);
			int charAtAsNumber = Character.getNumericValue(charAt);
			int multiplicador = multiplicadorArray[i];
			int multiplicacao = charAtAsNumber * multiplicador;
			sum += multiplicacao;
		}
		int mod = sum % 11;
		if (mod > 1) {
			digitoVerificador = 11 - mod;
		} else if (mod == 1) {
			digitoVerificador = 0;
		} else if (mod == 0) {
			digitoVerificador = 5;
		} else {
			digitoVerificador = 0;
		}

		return String.valueOf(digitoVerificador);
	}
	
	public static void main(String[] args) {
		System.out.println(Modulo11.getInstance().obterDigito("70000188", new int[]{9,8,7,6,5,4,3,2}));
		System.out.println(Modulo11.getInstance().obterDigito("70000661", new int[]{9,8,7,6,5,4,3,2}));
		System.out.println(Modulo11.getInstance().obterDigito("70000489", new int[]{9,8,7,6,5,4,3,2}));
		System.out.println(Modulo11.getInstance().obterDigito("70000418", new int[]{9,8,7,6,5,4,3,2}));
		System.out.println(Modulo11.getInstance().obterDigito("2941117288", new int[]{11,10,9,8,7,6,5,4,3,2}));
		
	}
	
}
