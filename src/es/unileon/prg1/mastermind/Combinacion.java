package es.unileon.prg1.mastermind;

import java.util.Random;

/**
 * 
 * @author Alejandro Moya García, Pablo González de la Iglesia, Juan Carlos
 *         Gutiérrez Vicente, Ignacio Rodríguez Basante
 *
 */
public class Combinacion {

//	private int length;
	private Ficha[] fichas;
//	private boolean repeticion;
	final int NUM_COLORES = 6;

	/**
	 * Constructor de la clase Combinacion
	 * 
	 * @param repeticion
	 * @param longitud
	 *            de la combinacion
	 * @throws FichaException 
	 * 
	 * 
	 * 
	 *             Método que genera una combinación aleatoria con o sin
	 *             repetición de colores. Asigna a cada ficha un color ,que se
	 *             corresponde con un número de un array de los colores
	 *             disponibles(0=R/1=G/2=B/3=Y/4=P/5=C). Si es sin repetición,
	 *             una vez se usa ese color, se intercambia con el último de los
	 *             colores disponibles, y se reduce el rango de elección de
	 *             colores en 1.
	 * 
	 */

	public Combinacion(boolean repeticion, int length) throws MastermindException {
		int[] auxiliar = { 0, 1, 2, 3, 4, 5 };
		int random = 0, numeroColores = NUM_COLORES;
		Random numeroaleatorio = new Random();
		numeroaleatorio.setSeed(System.nanoTime());

		
		
		this.fichas = new Ficha[length];

		if (!repeticion) {
			
			for (int i = 0; i < length; i++) {
				random = auxiliar[numeroaleatorio.nextInt(numeroColores)];
				fichas[i] = new Ficha('-');
				fichas[i].numeroAColor(auxiliar[random]);
				auxiliar[random] = auxiliar[numeroColores - 1];
				numeroColores--;
			}

		} else {

			for (int i = 0; i < length; i++) {
				random = numeroaleatorio.nextInt(NUM_COLORES);
				fichas[i] = new Ficha('-');
				fichas[i].numeroAColor(random);
			}
		}
	}

	/**
	 * Constructor copia de la clase Combinacion. Crea una combinacion igual que
	 * la que recibe como parámetro
	 * 
	 * @param una
	 *            combinacion
	 * @throws FichaException 
	 * 
	 * 
	 */
	public Combinacion(Combinacion otraCombinacion) throws MastermindException {
		this.fichas = new Ficha[otraCombinacion.getLongitud()];
		for (int i = 0; i < otraCombinacion.getLongitud(); i++) {
			fichas[i] = new Ficha('-');
			fichas[i].setColor(otraCombinacion.getColorAt(i));
		}
	}

	/**
	 * Transforma a combinación un String introducido por el usuario.
	 * 
	 * @param combinacion
	 *            por el usuario
	 * @throws FichaException 
	 * 
	 */
	public Combinacion(String combinacion, int longitud) throws  MastermindException{
		StringBuffer error=new StringBuffer();
		if(combinacion.length()!=longitud){
			error.append("Longitud erronea: "+combinacion.length()+" , debe introducir una combinacion de "+longitud+" caracteres.");
		}
			this.fichas = new Ficha[longitud];
			for (int i = 0; i < combinacion.length(); i++) {
				
				
				try{
				this.fichas[i] = new Ficha(combinacion.charAt(i));
				}catch(MastermindException exception){
					error.append(exception.getMessage());
				}catch(ArrayIndexOutOfBoundsException exception2){
					
				}

			}
			if(error.length()!=0){
				throw new MastermindException(error.toString());
			}
		
	}

	/**
	 * Método que devuelve la longitud (número de fichas) de la combinación
	 * introducida
	 * 
	 * @return longitud de la combinación introducida
	 */
	public int getLongitud() {
		return this.fichas.length;
	}



	/**
	 * Obtiene el color de una de las fichas de la combinacion
	 * 
	 * @param posicion
	 *            de la ficha
	 * @return color de la ficha
	 */
	public char getColorAt(int posicion) {
		return this.fichas[posicion].getColor();
	}

	/**
	 * Cambia el color una ficha de la combinacion
	 * 
	 * @param posicion
	 *            de la Ficha
	 * @param nuevo
	 *            color de la ficha
	 * @throws FichaException 
	 */
	public Ficha getFicha(int posicion){
		return this.fichas[posicion];
	}

	/**
	 * Compara la combinacion introducida por el usuario con la combinacion
	 * aleatoria generada por el juego. Compara ficha a ficha, si coinciden
	 * color y posicion escribe una 'O' en la combinacion de aciertos. Si es
	 * solo posicion una 'X'. Después de comparar cada ficha se cambia el color
	 * de la ficha comparada a SinColor ('_').
	 * 
	 * @param array
	 *            de chars introducido por el usuario
	 * @return un array de chars con los aciertos de posición y color como 'O' y
	 *         los de color pero no posicion como 'X'
	 * @throws MastermindException 
	 * @throws FichaException 
	 * 
	 */

	public Combinacion compararAciertos(Combinacion combinacionIntroducida) throws MastermindException   {
		int siguiente = 0, i = 0, j = 0;
		Combinacion combinacionAciertos = new Combinacion(false, combinacionIntroducida.getLongitud());
		while (i < this.getLongitud() && siguiente < this.getLongitud()) {
			while (j < this.getLongitud() && siguiente < this.getLongitud()) {
				if (combinacionIntroducida.getFicha(i).esIgual(this.fichas[j]) && i == j) {
					combinacionAciertos.getFicha(siguiente++).setColor('O');
					this.fichas[j].fichaSinColor();
					j=this.getLongitud();//para salir del bucle "j"
				}
				j++;
			}
			i++;
			j = 0;
		}
		i = 0;
		j = 0;
		while (i < this.getLongitud() && siguiente < this.getLongitud()) {
			while (j < this.getLongitud() && siguiente < this.getLongitud()) {
				if (combinacionIntroducida.getFicha(i).esIgual(this.fichas[j])) {
					combinacionAciertos.getFicha(siguiente++).setColor('X');
					this.fichas[j].fichaSinColor();
					j=this.getLongitud();//para salir del bucle "j"
				}
				j++;
			}
			i++;
			j = 0;
		}
		while (siguiente < this.getLongitud()) {
			combinacionAciertos.getFicha(siguiente++).setColor('-');

		}

		return combinacionAciertos;
	}

	/**
	 * Pasa una combinacion a string
	 */
	public String toString(){
		StringBuffer salida= new StringBuffer();
		for(int i=0;i<this.getLongitud();i++){
			salida.append(this.fichas[i].toString());
		}
		return salida.toString();
	}

}
