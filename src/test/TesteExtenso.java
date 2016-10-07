package test;
import java.math.BigDecimal;

import br.com.issei.util.string.ExtensoUtil;


public class TesteExtenso {

	public static void main(String[] args) {
		
		for(int i=0;i<300;i++)
		System.out.println(i +" = "+ ExtensoUtil.converter(new BigDecimal(i)));

	}

}
