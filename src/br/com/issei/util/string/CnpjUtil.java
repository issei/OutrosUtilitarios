package br.com.issei.util.string;


/**
 * CNPJ Util.
 */
public class CnpjUtil extends CPFCNPJUtil {
    /**
     * Valida CNPJ do usu�rio.
     * 
     * @param cnpj
     *            String valor com quatorze d�gitos
     */
    public boolean validaCNPJ(String cnpj) {
    	return isCNPJ(cnpj);
    }

    /**
     * formata com a mascara de cnpj o numero recebido.
     * @param cnpjLong
     * @return
     */
	public String formatarCnpj(Long cnpjLong) {
		return formatarCNPJ(cnpjLong);
	}
}
