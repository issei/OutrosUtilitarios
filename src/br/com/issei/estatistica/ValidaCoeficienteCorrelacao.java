package br.com.issei.estatistica;

import br.com.issei.estatistica.constantes.CoeficienteCorrelacao;

public class ValidaCoeficienteCorrelacao {

	public CoeficienteCorrelacao getCoeficienteCorrelacao(double valor) {

		if (valor < -1 || valor > 1)
			return null;

		if (valor == 1)
			return CoeficienteCorrelacao.POSITIVA_PERFEITA;
		if (valor == 0)
			return CoeficienteCorrelacao.LINEAR_INEXSTENTE;
		if (valor == -1)
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

}
