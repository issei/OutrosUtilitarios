package br.com.issei.estatistica;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Regressao {
	
	private List<BigDecimal> x;
	private List<BigDecimal> y;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double x[] = { 11, 12, 14, 16, 17, 18, 19 };
		double y[] = { 3.3, 3.9, 4.0, 4.8, 5.2, 5.5, 5.7 };
//		double x[] = { 5, 8, 10, 12, 15, 300, 10 };
//		double y[] = { 10, 45, 50, 75, 10, 200, 10 };
		
		SimpleRegression regression = new SimpleRegression(true);
		for (int i = 0; i < x.length; i++) {
			regression.addData(x[i], y[i]);
		}
		System.out.println("getIntercept:"+regression.getIntercept());
		System.out.println("getInterceptStdErr:"+regression.getInterceptStdErr());
		System.out.println("getMeanSquareError:"+regression.getMeanSquareError());
		System.out.println("N:"+regression.getN());
		System.out.println("R:"+regression.getR());
		System.out.println("getRegressionSumSquares:"+regression.getRegressionSumSquares());
		System.out.println("getRSquare:"+regression.getRSquare());
		System.out.println("getSignificance:"+regression.getSignificance());
		System.out.println("getSlope:"+regression.getSlope());
		System.out.println("getSlopeConfidenceInterval:"+regression.getSlopeConfidenceInterval());
		System.out.println("getSlopeStdErr:"+regression.getSlopeStdErr());
		System.out.println("getSumOfCrossProducts:"+regression.getSumOfCrossProducts());
		System.out.println("getTotalSumSquares:"+regression.getTotalSumSquares());
		System.out.println("getXSumSquares:"+regression.getXSumSquares());

	}

}
