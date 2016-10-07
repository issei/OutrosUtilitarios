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
     * Valida CPF do usu�rio. N�o aceita CPF's padr�es como 11111111111 ou
     * 22222222222
     * 
     * @param cpf
     *            String valor com 11 d�gitos
     */
    public boolean validaCPF(String cpf) {
    	return isCPF(cpf);
    }

	/**
	 * M�todo para formatar o n�mero que representa o c�digo de um
	 * <code>CPF</code>.
	 * 
	 * @param cpfLong
	 *            N�emro do <code>CPF</code>.
	 * @return Um texto formatado do <code>CPF</code>.
	 */
	public String formatarCpf(Long cpfLong) {
		return formatarCPF(cpfLong);
	}
	
	/**
	 * M�todo para formatar o n�mero que representa o c�digo de um
	 * <code>CPF</code> em chamadas no IReport.
	 * 
	 * @param cpfLong
	 *            N�emro do <code>CPF</code>.
	 * @return Um texto formatado do <code>CPF</code>.
	 */
	public static String formatarCpfReport(Long cpfLong) {
		return formatarCPF(cpfLong);
	}
}
