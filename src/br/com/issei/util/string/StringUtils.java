package br.com.issei.util.string;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.logging.Level.WARNING;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public final class StringUtils {
	/**
	 * Default charset to use for encoding/decoding strings.
	 */
	public static final String ENCODING_CHARSET = "UTF-8";

	/**
	 * Logger.
	 */
	private static final Logger logger = Logger.getLogger(StringUtils.class
			.getName());

	/**
	 * Prevents instantiation.
	 */
	private StringUtils() {
	}

	/**
	 * Is {@code string} blank (null or only whitespace)?
	 * 
	 * @param string
	 *            The string to check.
	 * @return {@code true} if {@code string} is blank, {@code false} otherwise.
	 */
	public static boolean isBlank(String string) {
		return string == null || "".equals(string.trim());
	}

	/**
	 * Returns a trimmed version of {@code string}, or {@code null} if
	 * {@code string} is {@code null} or the trimmed version is a blank string.
	 * 
	 * @param string
	 *            The string to trim.
	 * @return A trimmed version of {@code string}, or {@code null} if
	 *         {@code string} is {@code null} or the trimmed version is a blank
	 *         string.
	 */
	public static String trimToNull(String string) {
		if (isBlank(string))
			return null;
		return string.trim();
	}

	/**
	 * Returns a trimmed version of {@code string}, or an empty string if
	 * {@code string} is {@code null} or the trimmed version is a blank string.
	 * 
	 * @param string
	 *            The string to trim.
	 * @return A trimmed version of {@code string}, or an empty string if
	 *         {@code string} is {@code null} or the trimmed version is a blank
	 *         string.
	 */
	public static String trimToEmpty(String string) {
		if (isBlank(string))
			return "";
		return string.trim();
	}

	/**
	 * Converts {@code string} to a byte array.
	 * <p>
	 * Assumes {@code string} is in {@value #ENCODING_CHARSET} format.
	 * 
	 * @param string
	 *            The string to convert to a byte array.
	 * @return A byte array representation of {@code string}.
	 * @throws NullPointerException
	 *             If {@code string} is {@code null}.
	 * @throws IllegalStateException
	 *             If unable to convert because the JVM doesn't support
	 *             {@value #ENCODING_CHARSET}.
	 */
	public static byte[] toBytes(String string) {
		if (string == null)
			throw new NullPointerException("Parameter 'string' cannot be null.");

		try {
			return string.getBytes(ENCODING_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("Platform doesn't support "
					+ ENCODING_CHARSET, e);
		}
	}

	/**
	 * Converts {@code data} to a string in {@value #ENCODING_CHARSET} format.
	 * 
	 * @param data
	 *            The data to convert to a string.
	 * @return A string representation of {@code data}.
	 * 
	 * @throws NullPointerException
	 *             If {@code data} is {@code null}.
	 * @throws IllegalStateException
	 *             If unable to convert because the JVM doesn't support
	 *             {@value #ENCODING_CHARSET}.
	 * @since 1.6.13
	 */
	public static String toString(byte[] data) {
		if (data == null)
			throw new NullPointerException("Parameter 'data' cannot be null.");

		try {
			return new String(data, ENCODING_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("Platform doesn't support "
					+ ENCODING_CHARSET, e);
		}
	}

	/**
	 * Builds and returns a string representation of the given
	 * {@code inputStream} .
	 * 
	 * @param inputStream
	 *            The stream from which a string representation is built.
	 * 
	 * @return A string representation of the given {@code inputStream}.
	 * @throws IOException
	 *             If an error occurs while processing the {@code inputStream}.
	 */
	public static String fromInputStream(InputStream inputStream)
			throws IOException {
		if (inputStream == null)
			return null;

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(inputStream,
					ENCODING_CHARSET));
			StringBuilder response = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null)
				response.append(line);

			return response.toString();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (Throwable t) {
					// Really nothing we can do but log the error
					if (logger.isLoggable(WARNING))
						logger.warning("Unable to close stream, continuing on: "
								+ t);
				}
		}
	}

	/**
	 * Joins the given {@code array} into a comma-separated string.
	 * 
	 * @param array
	 *            The array to join.
	 * @return A comma-separated string representation of the given
	 *         {@code array}.
	 */
	public static String join(String[] array) {
		return array == null ? null : join(asList(array));
	}

	/**
	 * Joins the given {@code list} into a comma-separated string.
	 * 
	 * @param list
	 *            The list to join.
	 * @return A comma-separated string representation of the given {@code list}
	 *         .
	 */
	public static String join(List<String> list) {
		if (list == null)
			return null;

		StringBuilder joined = new StringBuilder();
		boolean first = true;

		for (String element : list) {
			if (first)
				first = false;
			else
				joined.append(",");
			joined.append(element);
		}

		return joined.toString();
	}

	/**
	 * Returns an {@code Integer} representation of the given {@code string}, or
	 * {@code null} if it's not a valid {@code Integer}.
	 * 
	 * @param string
	 *            The string to process.
	 * @return The {@code Integer} representation of {@code string}, or
	 *         {@code null} if {@code string} is {@code null} or not a valid
	 *         {@code Integer}.
	 */
	public static Integer toInteger(String string) {
		if (string == null)
			return null;

		try {
			return parseInt(string);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * M&eacute;todo que remove acentos de uma String.
	 * 
	 * @param source
	 * @return
	 */
	public static String removeAccents(String source) {
		if (StringUtils.isEmpty(source)) {
			return source;
		} else {
			source = java.text.Normalizer.normalize(source, java.text.Normalizer.Form.NFD);
			source = source.replaceAll("[^\\p{ASCII}]", "");

			return String.valueOf(source);
		}
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String nullToEmpty(String s) {
		return s == null ? "" : s;
	}

	/**
	 * Converte um objeto em sua representação como {@link String}, se ele for
	 * nulo, retornará uma {@link String} vazia.
	 * 
	 * @param o
	 *            Objeto para ser convertido.
	 * @return Retorna o texto que representa o objeto informado.
	 */
	public static String nullToEmpty(Object o) {
		if (o != null) {
			return nullToEmpty(String.valueOf(o));
		}

		return "";
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return nullToEmpty(s).trim().equals("");
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}
	
	public static String[] split(String s, String separador){
		separador = separador.replace("|", "\\|");
		
		return s.split(separador);
	}
	
	/**
	 * Returns the characters starting at position zero to position informed.
	 * 
	 * @param source
	 *            text to be changed
	 * @param numberCharacters
	 *            Number of characters to be read in the text
	 * @return the amount of text characters informed
	 * @author fssantana
	 * @since 04/03/2012
	 */
	public static String readTheFirstFewCharacters(String source,
			int numberCharacters) {
		return source.length() >= numberCharacters ? source.substring(0,
				numberCharacters) : source;
	}

	/**
	   * Parses a list according to the specified delimiter into an
	   * array of Strings.
	   * @see java.util.StringTokenizer
	   * @param list a string of token seperated values
	   * @param delim the delimiter character(s).  Each character in the string is a
	   * single delimeter.
	   * @return an array of strings
	   */
	  public static List<String> parseList(String list, String delim) {
	      List<String> result = new ArrayList<String>();
	      StringTokenizer tokenizer = new StringTokenizer(list, delim);
	      while (tokenizer.hasMoreTokens()) {
	          result.add(tokenizer.nextToken());
	      }
	      return result;
	  }
	  
	  /**
	   * Parses a comma-separated list into an array of Strings
	   * Values can contain whitespace, but whitespace at the beginning and
	   * end of each value is trimmed.
	   * @return array of Strings
	   * @param csvList a string of comma seperated values
	   */
	  public static List<String> parseCommaDelimitedList(String csvList) {
		  List<String> listToken = parseList(csvList, ",");
		  List<String> result = new ArrayList<String>();
	      for (String token : listToken) {
	          result.add(token.trim());
	      }
	      return result;
	  }
	  
	  /**
	   * Preenche value com a quantidade de ZERO à esquerda informada. 
	   * 
	   * @param quantidadeZeroEsquerda
	   * @param value
	   * @return
	   */
	  public static String preencherZeroAEsquerda(int quantidadeZeroEsquerda, Number value) {
		  return String.format("%0" + quantidadeZeroEsquerda + "d", value);
	  }
}