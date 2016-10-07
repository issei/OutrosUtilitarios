package br.com.issei.estatistica;

import br.com.issei.estatistica.exception.CalculoException;

/**
 * Qualquer n�mero inteiro positivo, maior do que um, pode ser escrito
 * univocamente como o produto de v�rios n�meros primos (chamados fatores
 * primos). Ao processo que recebe como argumento um n�mero e devolve os seus
 * fatores primos chama-se decomposi��o em fatores primos.
 * 
 * <ul>Exemplos:<br\>
 * <li>O fator primo de 6 � 2 e 3 (6 = 2 � 3).</li> <li>5 tem apenas um fator
 * primo: ele mesmo (5 � n�mero primo).</li> <li>100 tem dois fatores primos: 2
 * e 5 (100 = 2^2 * 5^2).</li> <li>2, 4, 8, 16, etc. Cada um deles tem apenas
 * �nico fator primo: 2. (2 � primo, 4 = 2^2, 8 = 2^3, etc.)</li> <li>1 n�o tem
 * fator primo.</li>
 * </ul>
 * 
 * @author myokoyama
 * 
 */
public class FatorPrimo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(acharPrimos(100));
		} catch (CalculoException e) {
			e.printStackTrace();
		}

	}

	// M�todos da classe
	public static String acharPrimos(long n) throws CalculoException {
		String s = "";
		long temp;
		temp = n;
		long x = n;
		long resto;
		long div = 2;
		long exp = 0;
		if (x < 2)
			throw new CalculoException("Valor deve ser maior que 2");
		while (x > 1) {
			resto = x % div;
			if (resto == 0) {
				while (resto == 0) {
					x = (long) (x / div);
					exp += 1;
					resto = x % div;
				} // endwhile
				s = s + div;

				if (exp > 1)
					s = s + "^" + exp;
				if (x > 1)
					s = s + "  *  ";
			} else {
				exp = 0; // reinicia expoente
				div += 1; // pr�ximo divisor
			} // endif
		} // endwhile

		return s;

	}

}
