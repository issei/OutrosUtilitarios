package br.com.issei.estatistica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;

import br.com.issei.estatistica.constantes.CurvaPolida;

public class EstatisticaDescritiva {

	private double array[];

	public static void main(String[] args) {

		// seta valores do array
		double array[] = { 5, 8, 10, 12, 15,16,16,16,16,16,100 };
		
		DescriptiveStatistics teste = new SynchronizedDescriptiveStatistics();
		SummaryStatistics su = new SummaryStatistics();
		for (double d : array) {
			teste.addValue(d);
			su.addValue(d);
		}
		System.out.println(teste);
		System.out.println(su);

		// para teste com 1000000 mil valores,

		// array = new double[1000000];
		// for (int i = 0; i < array.length; i++) {
		// array[i] = (int)(Math.random()*(array.length/100));
		// }

		EstatisticaDescritiva e = new EstatisticaDescritiva(array);

		// Marca o início do tempo

		double t1 = System.currentTimeMillis();

		// e.imprimeArray(); ////Cuidado com esta linha, pois se o array tiver
		// milhoes de valores seu console vai encher

		// System.out.print("\n"+e.buscaPor(9));

		System.out.print("\n mediana: " + e.getMediana());

		System.out.print("\n Soma Elementos: " + e.getSomaDosElementos());

		System.out.print("\n Media Aritmética: " + e.getMediaAritmetica());

		System.out.print("\n SomaDosElementosAoQuadrado: "
				+ e.getSomaDosElementosAoQuadrado());

		System.out.print("\n Variância Amostral: " + e.getVariancia());

		System.out.print("\n Desvio Padrão: " + e.getDesvioPadrao());

		System.out.print("\n Coeficiente Variação de Pearson: "
				+ e.getPearson());
		System.out.print("\n Coeficiente Variação: "
				+ e.getCoeficienteVariacao());

		System.out.print("\n Moda: " + e.getModa());

		System.out.print("\n Coeficiente de Assimetria: "
				+ e.getCoefAssimetria());

		System.out.println("\n Curva = " + e.getCurvaPolida().name());

		// marca o fim do processamento

		double t2 = System.currentTimeMillis();

		// exibe o tempo em segundos

		System.out.print("\n Tempo Total: " + (t2 - t1) / Double.valueOf(1000)
				+ " segundos");

	}

	// Coeficiente de Variação de Pearson

	/**
	 * @param valores
	 */
	public EstatisticaDescritiva(double[] valores) {
		super();
		this.array = valores;
	}

	/**
	 * O coeficiente de variação fornece a variação dos dados obtidos em relação
	 * à média. Quanto menor for o seu valor, mais homogêneos serão os dados. O
	 * coeficiente de variação é considerado baixo (apontando um conjunto de
	 * dados bem homogêneos) quando for menor ou igual a 25%. O fato de o
	 * coeficiente de variação ser dado em valor relativo nos permite comparar
	 * séries de valores que apresentam unidades de medida distintas.
	 * 
	 * @return
	 */
	public double getPearson() {

		return (getDesvioPadrao() / getMediaAritmetica()) * 100;

	}

	public CurvaPolida getCurvaPolida() {
		if (getMediaAritmetica() < getMediana() && getMediana() < getModa()
				&& getMediaAritmetica() < getModa()) {
			return CurvaPolida.ASSIMETRICA_NEGATIVA;
		} else if (getModa() < getMediana()
				&& getMediana() < getMediaAritmetica()
				&& getModa() < getMediaAritmetica()) {
			return CurvaPolida.ASSIMETRICA_POSITIVA;
		} else if (getModa() == getMediana()
				&& getMediana() == getMediaAritmetica()
				&& getModa() == getMediaAritmetica()) {
			return CurvaPolida.SIMETRICA;
		} else if (getMediana() < getMediaAritmetica()
				&& getMediaAritmetica() < getModa() && getMediana() < getModa()) {
			return CurvaPolida.ASSIMETRICA_NEGATIVA;
		} else if (getMediaAritmetica() < getMediana()
				&& getModa() < getMediaAritmetica() && getModa() < getMediana()) {
			return CurvaPolida.ASSIMETRICA_POSITIVA;
		}else if (getModa() == 0
				&& getMediana() == getMediaAritmetica()) {
			return CurvaPolida.SIMETRICA;
		} else {
			throw new RuntimeException("moda = " + getModa() + " Mediana="
					+ getMediana() + " Media=" + getMediaAritmetica());
		}
	}

	/**
	 * A média aritmética é uma das formas de obter um valor intermediário entre
	 * vários valores.
	 * 
	 * @return
	 */
	public double getMediaAritmetica() {

		double total = 0;

		for (int counter = 0; counter < array.length; counter++)

			total += array[counter];

		return total / array.length;

	}

	public double getSomaDosElementos() {

		double total = 0;

		for (int counter = 0; counter < array.length; counter++)

			total += array[counter];

		return total;

	}

	public double getSomaDosElementosAoQuadrado() {

		double total = 0;

		for (int counter = 0; counter < array.length; counter++)

			total += Math.pow(array[counter], 2);

		return total;

	}

	public double getMediaAritmetica(double array[]) {

		double total = 0;

		for (int counter = 0; counter < array.length; counter++)

			total += array[counter];

		return total / array.length;

	}

	public double getSomaDosElementos(double array[]) {

		double total = 0;

		for (int counter = 0; counter < array.length; counter++)

			total += array[counter];

		return total;

	}

	public void ordenar() {

		Arrays.sort(array);

	}

	public void imprimeArray() {

		System.out.print("\nElementos do Array: ");

		for (int count = 0; count < array.length; count++)

			System.out.print(array[count] + " ");

	}

	// Array não pode conter valores duplicados

	public int buscaPor(int value) {

		return Arrays.binarySearch(array, value);

	}

	// Variância Amostral

	public double getVariancia() {

		double p1 = 1 / Double.valueOf(array.length - 1);

		double p2 = getSomaDosElementosAoQuadrado()

		- (Math.pow(getSomaDosElementos(), 2) / Double

		.valueOf(array.length));

		return p1 * p2;

	}

	// Desvio Padrão Amostral

	public double getDesvioPadrao() {

		return Math.sqrt(getVariancia());

	}

	/**
	 * A mediana é uma medida de tendência central, um número que caracteriza as
	 * observações de uma determinada variável de tal forma que este número (a
	 * mediana) de um grupo de dados ordenados separa a metade inferior da
	 * amostra, população ou distribuição de probabilidade, da metade superior.
	 * Mais concretamente, 1/2 da população terá valores inferiores ou iguais à
	 * mediana e 1/2 da população terá valores superiores ou iguais à mediana.
	 * 
	 * @return
	 */
	public double getMediana() {

		this.ordenar();

		int tipo = array.length % 2;

		if (tipo == 1) {

			return array[((array.length + 1) / 2) - 1];

		} else {

			int m = array.length / 2;

			return (array[m - 1] + array[m]) / 2;

		}

	}

	/**
	 * a moda é o valor que detém o maior número de observações, ou seja, o
	 * valor ou valores mais frequentes, ou ainda
	 * "o valor que ocorre com maior freqüência num conjunto de dados, isto é, o valor mais comum"
	 * .
	 * 
	 * @return
	 */
	public double getModa() {

		HashMap map = new HashMap();

		Integer i;

		Double moda = 0.0;

		Integer numAtual, numMaior = 0;

		for (int count = 0; count < array.length; count++) {

			i = (Integer) map.get(new Double(array[count]));

			if (i == null) {

				map.put(new Double(array[count]), new Integer(1));

			} else {

				map.put(new Double(array[count]), new Integer(i.intValue() + 1));

				numAtual = i.intValue() + 1;

				if (numAtual > numMaior) {

					numMaior = numAtual;

					moda = new Double(array[count]);

				}

			}

		}

		// System.out.print("\n Eis o mapa: "+map.toString());

		return moda;

	}

	public double getCoefAssimetria() {

		return (getMediaAritmetica() - getModa()) / getDesvioPadrao();

	}

	public double getCoeficienteVariacao() {
		return getVariancia() / getMediaAritmetica();
	}

	public double[] getArray() {

		return array;

	}

	public void setArray(double[] array) {

		this.array = array;

	}

}
