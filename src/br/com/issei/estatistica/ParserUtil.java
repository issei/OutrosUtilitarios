package br.com.issei.estatistica;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ParserUtil {

	public ParserUtil() {
		super();
	}

	protected final ArrayList<BigDecimal> parseToBigDecimal(double[] scores1) {
		ArrayList<BigDecimal> a = new ArrayList<BigDecimal>();
		for (double a1 : scores1)
			a.add(new BigDecimal(a1));
		return a;
	}

}