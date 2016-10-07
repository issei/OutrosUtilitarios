package br.com.issei.util.string;

public class Extenso {

	public static final int MAX_LENGTH = 1000;

	/**
	 * 
	 */
	public enum ExtensoSexo {
		MASCULINO, FEMININO
	}

	private ExtensoSexo sexo = ExtensoSexo.MASCULINO;

	/**
	 * 
	 */
	public enum ExtensoUnidadeMedida {
		REAL("real", "reais"), CENTAVO("centavo", "centavos"), DIA("dia", "dias");

		private String singular;
		private String plural;

		ExtensoUnidadeMedida(String singular, String plural) {
			this.singular = singular;
			this.plural = plural;
		}

		public String getPlural() {
			return plural;
		}

		public String getSingular() {
			return singular;
		}
	}

	/**
	 * 
	 */
	public enum FormatoExtenso {
		NUMERO_PARENTESES_EXTENSO, NUMERO_EXTENSO_SEM_UNIDADE, NUMERO_EXTENSO_COM_UNIDADE;
	}

	private ExtensoUnidadeMedida unidadeMedidaParteInteira, unidadeMedidaParteFracionaria;

	private FormatoExtenso formato = FormatoExtenso.NUMERO_EXTENSO_SEM_UNIDADE;

	/** Description of the Field. */
	private double num; // The number that is going to be converted
	/** Description of the Field. */
	private String s; // The String that is going to be returned
	/** Description of the Field. */
	private int maxlen; // our result string's wrap limit..

	/**
	 * 
	 * @param sexo
	 */
	public Extenso(ExtensoSexo sexo) {
		super();
		this.sexo = sexo;
	}

	// Constructors
	/** Construtor para o objeto Extenso. */
	public Extenso() {
	}

	public Extenso(double num, int maxlen) {
		setNumber(num, maxlen);
	}

	public Extenso(ExtensoUnidadeMedida unidade, FormatoExtenso formato) {
		this.setUnidadeMedidaParteInteira(unidade);
		if (unidade == ExtensoUnidadeMedida.REAL) {
			this.setUnidadeMedidaParteFracionaria(ExtensoUnidadeMedida.CENTAVO);
		}
		this.setFormato(formato);
		extenso();
	}

	public Extenso(FormatoExtenso formato) {
		this.setFormato(formato);
		extenso();
	}

	public void setNumber(double num, int maxlen) {
		this.num = num;
		s = new String();
		this.maxlen = maxlen;
		extenso();
	}

	public void setNumber(double num) {
		setNumber(num, MAX_LENGTH);
	}

	/** The function that makes the convertion. */
	private void extenso() {
		init();
	}

	private void init() {
		String[] nome = {"um bi-lhão", " bi-lhões", "um mi-lhão", " mi-lhões"};
		long n = (long) num;
		long milMilhoes;
		long milhoes;
		long milhares;
		long unidades;
		long centavos;
		double frac = num - n;
		int nl;
		int rp;
		int last;
		int p;
		int len;
		if (num == 0) {
			s += "zero";
			return;
		}
		milMilhoes = (n - n % 1000000000) / 1000000000;
		n -= milMilhoes * 1000000000;
		milhoes = (n - n % 1000000) / 1000000;
		n -= milhoes * 1000000;
		milhares = (n - n % 1000) / 1000;
		n -= milhares * 1000;
		unidades = n;
		centavos = (long) (frac * 100);
		if ((long) (frac * 1000 % 10) > 5) {
			centavos++;
		}
		// s = "\0";
		// s[0] = '\0' ; //??
		if (milMilhoes > 0) {
			if (milMilhoes == 1) {
				s += nome[0];
			} else {
				s += numero(milMilhoes);
				s += nome[1];
			}
			if ((unidades == 0) && ((milhares == 0) && (milhoes > 0))) {
				s += " e ";
			} else if ((unidades != 0) || ((milhares != 0) || (milhoes != 0))) {
				s += ", ";
			}
		}
		if (milhoes > 0) {
			if (milhoes == 1) {
				s += nome[2];
			} else {
				s += numero(milhoes);
				s += nome[3];
			}
			if ((unidades == 0) && (milhares > 0)) {
				s += " e ";
			} else if ((unidades != 0) || (milhares != 0)) {
				s += ", ";
			}
		}
		if (milhares > 0) {
			if (milhares != 1) {
				s += numero(milhares);
			}
			s += " mil";
			if (unidades > 0) {
				if ((milhares > 100) && (unidades > 100)) {
					s += ", ";
				} else if (((unidades % 100) != 0) || ((unidades % 100 == 0) && (milhares < 10))) {
					s += " e ";
				} else {
					s += " ";
				}
			}
		}
		s += numero(unidades);
		if (num > 0) {
			if (formato.equals(FormatoExtenso.NUMERO_PARENTESES_EXTENSO)) {
				s = (long) num + " (" + s + ") ";
				s += ((long) num == 1L) ? "" + unidadeMedidaParteInteira.getSingular() : ""
						+ unidadeMedidaParteInteira.getPlural();
			}
			if (formato.equals(FormatoExtenso.NUMERO_EXTENSO_SEM_UNIDADE)) {
				s += "";
			}
			if (formato.equals(FormatoExtenso.NUMERO_EXTENSO_COM_UNIDADE)) {
				s += " ";
				s += ((long) num == 1L) ? unidadeMedidaParteInteira.getSingular()
						: unidadeMedidaParteInteira.getPlural();
			}
		}
		if (centavos != 0) {
			if (formato.equals(FormatoExtenso.NUMERO_EXTENSO_COM_UNIDADE)) {
				s += " e ";
				s += numero(centavos);
				s += " ";
				s += (centavos == 1) ? unidadeMedidaParteFracionaria.getSingular()
						: unidadeMedidaParteFracionaria.getPlural();
			} else {
				s += " e ";
				s += numero(centavos);
				s += (centavos == 1) ? "" + unidadeMedidaParteFracionaria.getSingular() : ""
						+ unidadeMedidaParteFracionaria.getPlural();
			}
		}

		len = s.length();
		StringBuffer sar = new StringBuffer(s);
		StringBuffer l = new StringBuffer();
		last = 0;
		rp = 0;
		nl = 1;

		for (p = 0; p < len; ++p) {
			if (sar.charAt(p) != '-') {
				rp++;
			}
			if (rp > maxlen) {
				if (sar.charAt(last) == ' ') {
					sar.replace(last, last + 1, "\n");
				} else {
					sar.insert(last + 1, '\n');
				}
				rp -= maxlen;
				nl++;
			}
			if ((sar.charAt(p) == ' ') || (sar.charAt(p) == '-')) {
				last = p;
			}
		} // for
		rp = 0;
		len = sar.length();

		for (p = 0; p < len; ++p) {
			if (!((sar.charAt(p) == '-') && (sar.charAt(p + 1) != '\n'))) {
				l.insert(rp++, sar.charAt(p));
			}
		} // for

		s = l.toString();
	}

	public String getResult() {
		String temp;
		if (s == null) {
			return "Number is not set!";
		}
		temp = s;
		s = null;

		return temp;
	}


	public String numero(long n) {
		String[] u = getArrayUnitario();
		String[] d = {"", "", "vin-te", "trin-ta", "qua-ren-ta", "cin-quen-ta", "ses-sen-ta", "se-ten-ta",
				"oi-ten-ta", "no-ven-ta" };
		String[] c = {"", "cen-to", "du-zen-tos", "tre-zen-tos", "qua-tro-cen-tos", "qui-nhen-tos", "seis-cen-tos",
				"se-te-cen-tos", "oi-to-cen-tos", "no-ve-cen-tos" };
		String extensoDoNumero = new String();
		// extenso_do_numero = "\0" ;
		if ((n < 1000) && (n != 0)) {
			if (n == 100) {
				extensoDoNumero = "cem";
			} else {
				if (n > 99) {
					extensoDoNumero += c[(int) (n / 100)];
					if (n % 100 > 0) {
						extensoDoNumero += " e ";
					}
				}
				if (n % 100 < 20) {
					extensoDoNumero += u[(int) n % 100];
				} else {
					extensoDoNumero += d[((int) n % 100) / 10];
					if ((n % 10 > 0) && (n > 10)) {
						extensoDoNumero += " e ";
						extensoDoNumero += u[(int) n % 10];
					}
				}
			}
		} else if (n > 999) {
			extensoDoNumero = "<<ERRO: NUMERO > 999>>";
		}
		return extensoDoNumero;
	}

	private String[] getArrayUnitario() {
		if (sexo.equals(ExtensoSexo.MASCULINO)) {
			return new String[]{"", "um", "dois", "tres", "qua-tro", "cin-co", "seis", "se-te", "oi-to", "no-ve",
					"dez", "on-ze", "do-ze", "tre-ze", "ca-tor-ze", "quin-ze", "de-zes-seis", "de-zes-sete",
					"de-zoi-to", "de-ze-no-ve" };
		} else {
			return new String[]{"", "uma", "duas", "tres", "qua-tro", "cin-co", "seis", "se-te", "oi-to", "no-ve",
					"dez", "on-ze", "do-ze", "tre-ze", "ca-tor-ze", "quin-ze", "de-zes-seis", "de-zes-sete",
					"de-zoi-to", "de-ze-no-ve" };
		}

	}

	public ExtensoUnidadeMedida getUnidadeMedidaParteInteira() {
		return unidadeMedidaParteInteira;
	}

	public void setUnidadeMedidaParteInteira(ExtensoUnidadeMedida unidadeMedidaParteInteira) {
		this.unidadeMedidaParteInteira = unidadeMedidaParteInteira;
	}

	public ExtensoUnidadeMedida getUnidadeMedidaParteFracionaria() {
		return unidadeMedidaParteFracionaria;
	}

	public void setUnidadeMedidaParteFracionaria(ExtensoUnidadeMedida unidadeMedidaParteFracionaria) {
		this.unidadeMedidaParteFracionaria = unidadeMedidaParteFracionaria;
	}

	public FormatoExtenso getFormato() {
		return formato;
	}

	public void setFormato(FormatoExtenso formato) {
		this.formato = formato;
	}
}
