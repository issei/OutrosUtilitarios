package br.com.issei.estatistica;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import br.com.issei.estatistica.constantes.CoeficienteCorrelacao;

public class Correlacao {

	private MathContext mathContext = MathContext.DECIMAL128;
	private List<BigDecimal> x;
	private List<BigDecimal> y;
	
	/**
	 *
	 * @param x
	 * @param y
	 */
	public Correlacao(List<BigDecimal> x, List<BigDecimal> y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public Correlacao(double[] x, double[] y) {
		super();
		ParserUtil util = new ParserUtil();
		this.x = util.parseToBigDecimal(x);
		this.y = util.parseToBigDecimal(y);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double array[] = { 5, 8, 10, 12, 15, 300, 10 };
		double array2[] = { 10, 45, 50, 75, 10, 200, 10 };
		Correlacao c = new Correlacao(array, array2);
		System.out.println(c.correlacaoAlternativa());
		System.out.println(c.correlacao());
		System.out.println(c.getCoeficienteCorrelacao().name());
		//Apache Commons Math
		System.out.println(new PearsonsCorrelation().correlation(array, array2));
	}
	
	public final CoeficienteCorrelacao getCoeficienteCorrelacao()
	{
		return getCoeficienteCorrelacao(correlacao().doubleValue());
	}
	
	public final CoeficienteCorrelacao getCoeficienteCorrelacao(double valor) {

		if (valor < -1 || valor > 1)
			return null;

		if (valor >= 1)
			return CoeficienteCorrelacao.POSITIVA_PERFEITA;
		if (valor == 0)
			return CoeficienteCorrelacao.LINEAR_INEXSTENTE;
		if (valor <= -1)
			return CoeficienteCorrelacao.NEGATIVA_PERFEITA;
		if (valor < 1 && valor >= 0.75)
			return CoeficienteCorrelacao.POSITIVA_FORTE;
		if (valor < 0.75 && valor >= 0.5)
			return CoeficienteCorrelacao.POSITIVA_MEDIA;
		if (valor < 0.5 && valor > 0.0)
			return CoeficienteCorrelacao.POSITIVA_FRACA;
		if (valor < 0.0 && valor > -0.5)
			return CoeficienteCorrelacao.NEGATIVA_FRACA;
		if (valor <= -0.5 && valor > -0.75)
			return CoeficienteCorrelacao.NEGATIVA_MEDIA;
		if (valor <= -0.75 && valor > -1)
			return CoeficienteCorrelacao.NEGATIVA_FRACA;

		// -1 <= valor <= 1
		return null;
	}

	public final BigDecimal correlacaoAlternativa() {
		BigDecimal resultado = new BigDecimal(0);
		BigDecimal somaX = new BigDecimal(0);
		BigDecimal somaY = new BigDecimal(0);
		BigDecimal somaXY = new BigDecimal(0);
		BigDecimal media_x = x.get(0);
		BigDecimal media_y = y.get(0);
		for (int i = 2; i < x.size() + 1; i += 1) {
			BigDecimal sweep = new BigDecimal(Double.valueOf(i - 1)).divide(
					new BigDecimal(i), mathContext);
			BigDecimal delta_x = x.get(i - 1).subtract(media_x);
			BigDecimal delta_y = y.get(i - 1).subtract(media_y);
			somaX = somaX.add(delta_x.multiply(delta_x).multiply(sweep));
			somaY = somaY.add(delta_y.multiply(delta_y).multiply(sweep));
			somaXY = somaXY.add(delta_x.multiply(delta_y).multiply(sweep));
			media_x = media_x
					.add(delta_x.divide(new BigDecimal(i), mathContext));
			media_y = media_y
					.add(delta_y.divide(new BigDecimal(i), mathContext));
		}
		BigDecimal pop_sd_x = BigDecimal.valueOf(Math.sqrt(somaX.divide(
				new BigDecimal(x.size()), mathContext).doubleValue()));
		BigDecimal pop_sd_y = BigDecimal.valueOf(Math.sqrt(somaY.divide(
				new BigDecimal(y.size()), mathContext).doubleValue()));
		BigDecimal cov_x_y = somaXY
				.divide(new BigDecimal(x.size()), mathContext);
		resultado = cov_x_y.divide(pop_sd_x.multiply(pop_sd_y), mathContext);
		return resultado;
	}

	public final BigDecimal correlacao() {
		int n = x.size();
		BigDecimal somaX = new BigDecimal(0);
		BigDecimal somaY = new BigDecimal(0);
		BigDecimal somaXY = new BigDecimal(0);
		BigDecimal somaXquadrado = new BigDecimal(0);
		BigDecimal somaYquadrado = new BigDecimal(0);

		for (int i = 0; i < n; i++) {
			somaX = somaX.add(x.get(i));
			somaY = somaY.add(y.get(i));
			somaXY = somaXY.add(x.get(i).multiply(y.get(i)));
			somaXquadrado = somaXquadrado.add(BigDecimal.valueOf(Math.pow(x
					.get(i).doubleValue(), 2)));
			somaYquadrado = somaYquadrado.add(BigDecimal.valueOf(Math.pow(y
					.get(i).doubleValue(), 2)));
		}
		return ((somaXY.multiply(new BigDecimal(n))).subtract(somaX
				.multiply(somaY))).divide(BigDecimal.valueOf(Math
				.sqrt((somaXquadrado.multiply(new BigDecimal(n))
						.subtract(BigDecimal.valueOf(Math.pow(
								somaX.doubleValue(), 2)))).multiply(
						somaYquadrado.multiply(new BigDecimal(n)).subtract(
								BigDecimal.valueOf(Math.pow(
										somaY.doubleValue(), 2))))
						.doubleValue())), mathContext);

	}

	/**
	 * @return the mathContext
	 */
	public MathContext getMathContext() {
		return mathContext;
	}

	/**
	 *  Default context MathContext.DECIMAL128
	 * @param mathContext the mathContext to set
	 */
	public void setMathContext(MathContext p) {
		if(p!=null)
		this.mathContext = p;
	}

	

}
