package br.com.issei.estatistica;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.inference.TestUtils;

public class Anova {

	/**
	 * http://commons.apache.org/proper/commons-math/userguide/stat.html
	 * @param args
	 */
	public static void main(String[] args) {
		double[] classA =
			   {93.0, 103.0, 95.0, 101.0, 91.0, 105.0, 96.0, 94.0, 101.0 };
			double[] classB =
			   {99.0, 92.0, 102.0, 100.0, 102.0, 89.0 };
			double[] classC =
			   {110.0, 115.0, 111.0, 117.0, 128.0, 117.0 };
			List<double[]> classes = new ArrayList<>();
			classes.add(classA);
			classes.add(classB);
			classes.add(classC);
			
			double fStatistic = TestUtils.oneWayAnovaFValue(classes); // F-value
			double pValue = TestUtils.oneWayAnovaPValue(classes);     // P-value
			
			System.out.println("fStatistic="+fStatistic);
			System.out.println("pValue="+pValue);
			
			System.out.println("oneWayAnovaTest 0.01="+TestUtils.oneWayAnovaTest(classes, 0.01)); // returns a boolean
            // true means reject null hypothesis

	}

}
