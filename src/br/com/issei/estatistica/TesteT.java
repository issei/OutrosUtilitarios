package br.com.issei.estatistica;

import org.apache.commons.math3.stat.inference.TestUtils;

public class TesteT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double alpha = 0.5;
		double[] observed = {1d, 2d, 3d};
		double mu = 2.5d;
		System.out.println(TestUtils.t(mu, observed));
		System.out.println(TestUtils.tTest(mu, observed,alpha));
		
		
		long[] observed2 = {10, 9, 11};
		double[] expected = {10.1, 9.8, 10.3};
		
		System.out.println(TestUtils.chiSquare(expected, observed2));
		System.out.println(TestUtils.chiSquareTest(expected, observed2,alpha));
		
		long[] observed3 = new long[]{70, 79, 3, 4};
		expected = new double[]{0.54d, 0.40d, 0.05d, 0.01d};
		System.out.println(TestUtils.g(expected, observed3));
		
		

	}

}
