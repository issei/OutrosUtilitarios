package br.com.issei.util.string;


/**
 * CNPJ Util.
 */
public class CnpjUtil extends CPFCNPJUtil {
    /**
     * Valida CNPJ do usuário.
     * 
     * @param cnpj
     *            String valor com quatorze dígitos
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
