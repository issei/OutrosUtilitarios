package br.com.issei.util.string;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.issei.util.string.Extenso;
import br.com.issei.util.string.Extenso.ExtensoUnidadeMedida;
import br.com.issei.util.string.Extenso.FormatoExtenso;


/**
 * Disponibiliza m�todos para transformar para extenso.
 *
 */
public final class ExtensoUtil {
	
	private ExtensoUtil() { }
	
	/**
	 * Converte um BigDecimal para Extenso.
	 * @return
	 */
	public static String converterMonetario(BigDecimal valor) {
		String resultado = new String();
		
		Extenso extenso = new Extenso(ExtensoUnidadeMedida.REAL, FormatoExtenso.NUMERO_EXTENSO_COM_UNIDADE);
		extenso.setNumber(valor.doubleValue(), Integer.MAX_VALUE);
		
		resultado = extenso.getResult();
		
		// O n�mero 3 est� escrito sem acentuo na classe Extenso.java
		resultado.replace("tres", "tr�s");
		
		return resultado;
	}
	
	/**
	 * Converte um BigDecimal para Extenso.
	 * @return
	 */
	public static String converter(BigDecimal valor) {
		String resultado = new String();
		
		Extenso extenso = new Extenso(ExtensoUnidadeMedida.DIA, FormatoExtenso.NUMERO_EXTENSO_SEM_UNIDADE);
		extenso.setNumber(valor.doubleValue(), Integer.MAX_VALUE);
		
		resultado = extenso.getResult();
				
		return resultado;
	}
	
	/**
	 * Retorna o m�s por extenso dado o n�mero do m�s.
	 * @param mes
	 * @return
	 */
	public static String retornaMesExtenso(Integer mes) {
		String mesExtenso = "";
		switch (mes) {
			case Calendar.JANUARY:
				mesExtenso = "Janeiro";
				break;
			case Calendar.FEBRUARY:
				mesExtenso = "Fevereiro";
				break;
			case Calendar.MARCH:
				mesExtenso = "Mar�o";
				break;
			case Calendar.APRIL:
				mesExtenso = "Abril";
				break;
			case Calendar.MAY:
				mesExtenso = "Maio";
				break;
			case Calendar.JUNE:
				mesExtenso = "Junho";
				break;
			case Calendar.JULY:
				mesExtenso = "Julho";
				break;
			case Calendar.AUGUST:
				mesExtenso = "Agosto";
				break;
			case Calendar.SEPTEMBER:
				mesExtenso = "Setembro";
				break;
			case Calendar.OCTOBER:
				mesExtenso = "Outubro";
				break;
			case Calendar.NOVEMBER:
				mesExtenso = "Novembro";
				break;
			case Calendar.DECEMBER:
				mesExtenso = "Dezembro";
				break;
			default:
				break;
		}
		return mesExtenso;
	}
}
