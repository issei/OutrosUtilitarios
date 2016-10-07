package br.com.issei.estatistica;

import java.io.IOException;
import java.math.BigInteger;

import br.com.issei.estatistica.exception.CalculoException;

public class Calculo {

	private static final BigInteger UM = new BigInteger("1");

	/**
	 * @param args
	 * @throws CalculoException
	 * @throws IOException
	 */
	public static void main(String[] args) throws CalculoException {
		String m = "60";
		String p = "6";
		System.out.println(permutacao(m));
		System.out.println(arranjoSimples(m, p));
		System.out.println(combinacaoSemRepeticao(m, p));
		System.out.println(combinacaoComRepeticao(m, p));
		

	}

	/**
	 * Arranjo simples de 'M' elementos tomados 'P' a 'P' , onde 'M' >= 1 e 'P'
	 * é um número natural, é qualquer ordenação de 'P'elementos dentre os 'M'
	 * elementos, em que cada maneira de tomar os elementos se diferenciam pela
	 * ordem e natureza dos elementos. Em arranjos, a ordem dos objetos é
	 * importante.
	 * 
	 * @param m
	 *            é o total de elementos
	 * @param p
	 *            o número de elementos escolhidos.
	 * @return BigInteger
	 * @throws CalculoException
	 */
	public static BigInteger arranjoSimples(String m, String p)
			throws CalculoException {
		final BigInteger M;
		final BigInteger P;
		try {
			M = new BigInteger(m);
			P = new BigInteger(p);
		} catch (NumberFormatException e) {
			throw new CalculoException(e);
		}
		BigInteger resp = fatorial(M).divide(fatorial(M.subtract(P)));
		return resp;
	}
	
	public static BigInteger combinacaoSemRepeticao(String m, String p)
			throws CalculoException {
		final BigInteger M;
		final BigInteger P;
		try {
			M = new BigInteger(m);
			P = new BigInteger(p);
		} catch (NumberFormatException e) {
			throw new CalculoException(e);
		}
		BigInteger resp = fatorial(M).divide(fatorial(P).multiply(fatorial(M.subtract(P))));
		return resp;
	}


	public static BigInteger combinacaoComRepeticao(String n, String r)
			throws CalculoException {
		final BigInteger N;
		final BigInteger R;
		try {
			N = new BigInteger(n);
			R = new BigInteger(r);
		} catch (NumberFormatException e) {
			throw new CalculoException(e);
		}
		BigInteger resp = fatorial(N.add(R.subtract(UM))).divide(fatorial(R).multiply(fatorial(N.subtract(UM))));
		return resp;
	}

	public static BigInteger permutacao(String n) throws CalculoException {
		return fatorial(n);
	}

	public static BigInteger fatorial(String numero) throws CalculoException {
		BigInteger n;
		try {
			n = new BigInteger(numero);
		} catch (NumberFormatException e) {
			throw new CalculoException(e);
		}
		return fatorial(n);
	}

	public static BigInteger fatorial(BigInteger n) throws CalculoException {
		if (n.longValue() < 1)
			throw new CalculoException("Valor não pode ser zero ou negativo.");
		if (n.longValue() == 1)
			return UM;
		else
			return n.multiply(fatorial(n.subtract(UM)));
	}

}
