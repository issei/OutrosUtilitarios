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
	 * Em aritm�tica e em teoria dos n�meros o m�nimo m�ltiplo comum (mmc) de
	 * dois inteiros a e b � o menor inteiro positivo que � m�ltiplo
	 * simultaneamente de a e de b. Se n�o existir tal inteiro positivo, por
	 * exemplo, se a = 0 ou b = 0, ent�o mmc(a, b) � zero por defini��o.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static final int mmc(int a, int b) {
		return (a * b) / mdc(a, b);
	}

	/**
	 * O m�ximo divisor comum ou MDC (mdc) entre dois ou mais n�meros inteiros �
	 * o maior n�mero inteiro que � fator de tais n�meros.1 Por exemplo, os
	 * divisores comuns de 12 e 18 s�o 1,2,3 e 6, logo mdc(12,18)=6. A defini��o
	 * abrange qualquer n�mero de termos, por exemplo mdc(10,15,25,30)=5. O
	 * m�ximo divisor comum tamb�m pode ser representado s� com par�nteses. Com
	 * esta nota��o, dizemos que dois n�meros inteiros x e y s�o primos entre si
	 * , se e somente se mdc(x, y)=1.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final int mdc(int x, int y) {
		//Algoritmo de Euclides
		if (x <= y || x % y == 0) { // condi��o de parada
			return y;
		} else {
			if (x < y) {
				return mdc(x, y);
			} else {
				return mdc(y, x % y);
			} // fim do else
		} // fim do 2� if
	} // fim da fun��o

}
