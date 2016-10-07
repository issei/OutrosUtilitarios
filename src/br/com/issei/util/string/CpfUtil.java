package br.com.issei.util.string;


/**
 * Classe Utilitaria para CPF.
 * 
 * @author
 */
public class CpfUtil extends CPFCNPJUtil {
	
	public static void main(String[] args) {
		System.out.println("valido?"+new CpfUtil().validaCPF("12345678909"));
	}
    /**
     * Valida CPF do usuário. Não aceita CPF's padrões como 11111111111 ou
     * 22222222222
     * 
     * @param cpf
     *            String valor com 11 dígitos
     */
    public boolean validaCPF(String cpf) {
    	return isCPF(cpf);
    }

	/**
	 * Método para formatar o número que representa o código de um
	 * <code>CPF</code>.
	 * 
	 * @param cpfLong
	 *            Núemro do <code>CPF</code>.
	 * @return Um texto formatado do <code>CPF</code>.
	 */
	public String formatarCpf(Long cpfLong) {
		return formatarCPF(cpfLong);
	}
	
	/**
	 * Método para formatar o número que representa o código de um
	 * <code>CPF</code> em chamadas no IReport.
	 * 
	 * @param cpfLong
	 *            Núemro do <code>CPF</code>.
	 * @return Um texto formatado do <code>CPF</code>.
	 */
	public static String formatarCpfReport(Long cpfLong) {
		return formatarCPF(cpfLong);
	}
}
