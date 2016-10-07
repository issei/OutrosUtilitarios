package br.com.issei.estatistica;

public class DivisorComum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(mdc(100, 4));
		System.out.println(mmc(100, 4));
		System.out.println(mmc(100, 20, 30, 10,5,60));

	}
	
	/**
	 * <b>MMC(a,b,c,d) = MMC(MMC(MMC(a,b),c),d))</b>
	 * @param valor
	 * @return
	 */
	public static int mmc(int... valor)
	{
		int aux = valor[0];
		if(valor.length>2)
		{
			for(int n=valor.length-1;n>0;n--)
			{
				aux = mmc(valor[n],aux);
			}
		}
		return aux;
	}

	/**
	 * Em aritmética e em teoria dos números o mínimo múltiplo comum (mmc) de
	 * dois inteiros a e b é o menor inteiro positivo que é múltiplo
	 * simultaneamente de a e de b. Se não existir tal inteiro positivo, por
	 * exemplo, se a = 0 ou b = 0, então mmc(a, b) é zero por definição.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static final int mmc(int a, int b) {
		return (a * b) / mdc(a, b);
	}

	/**
	 * O máximo divisor comum ou MDC (mdc) entre dois ou mais números inteiros é
	 * o maior número inteiro que é fator de tais números.1 Por exemplo, os
	 * divisores comuns de 12 e 18 são 1,2,3 e 6, logo mdc(12,18)=6. A definição
	 * abrange qualquer número de termos, por exemplo mdc(10,15,25,30)=5. O
	 * máximo divisor comum também pode ser representado só com parênteses. Com
	 * esta notação, dizemos que dois números inteiros x e y são primos entre si
	 * , se e somente se mdc(x, y)=1.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final int mdc(int x, int y) {
		//Algoritmo de Euclides
		if (x <= y || x % y == 0) { // condição de parada
			return y;
		} else {
			if (x < y) {
				return mdc(x, y);
			} else {
				return mdc(y, x % y);
			} // fim do else
		} // fim do 2º if
	} // fim da função

}
