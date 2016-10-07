package br.com.issei.estatistica.constantes;

public enum CoeficienteCorrelacao {
	
	POSITIVA_PERFEITA("Positiva Perfeita"), POSITIVA_FORTE("Positiva Forte"), POSITIVA_MEDIA("Positiva Média"), POSITIVA_FRACA("Positiva Fraca"), LINEAR_INEXSTENTE("Linear Inexistente"), NEGATIVA_FRACA("Negativa Fraca"), NEGATIVA_MEDIA("Negativa Media"), NEGATIVA_FORTE("Negativa Forte"), NEGATIVA_PERFEITA("Negativa Perfeita");

	private String desricao;
	
	private CoeficienteCorrelacao(String desc)
	{
		this.desricao = desc;
	}
	
	public String getDescricao()
	{
		return this.desricao;
	}
	
	
}
