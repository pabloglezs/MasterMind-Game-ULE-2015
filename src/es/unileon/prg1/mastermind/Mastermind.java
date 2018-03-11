package es.unileon.prg1.mastermind;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Mastermind{
	private static final Logger logger = LogManager.getLogger(Mastermind.class);
	

	private Tablero tablero;
	private Combinacion combinacionSecreta;

	final int NUM_COLORES = 6;
	
	public Mastermind(int length, int numeroIntentos, boolean repeticion)throws MastermindException{
		StringBuffer msg = new StringBuffer();
			if(length<=0){
				msg.append("Error, longitud incorrecta:"+length+". La longitud debe ser un numero mayor que cero\n");
			}
			if(numeroIntentos<=0){
				msg.append("\nError, numero de intentos incorrecto:"+numeroIntentos+". El numero de intentos debe ser mayor que cero\n");
			}
			if((length>NUM_COLORES)&&(repeticion==false)){
				msg.append("Error, si no hay colores repetidos no puede haber mas posiciones que colores\n");
			}
			
		if(msg.length()!=0){
			throw new MastermindException(msg.toString());
		}else{
				this.tablero = new Tablero(length,numeroIntentos);
				this.combinacionSecreta = new Combinacion(repeticion,length);
				logger.info("Combinacion aleatoria generada: "+this.combinacionSecreta.toString());
				
			}
		}
		
	public Mastermind() throws MastermindException{
		this.tablero = new Tablero(4,4);
		this.combinacionSecreta = new Combinacion("RGBY",4);
	}
//		public void introducirCombinacion(String linea) throws MastermindException{
//			StringBuffer error=new StringBuffer();
//			if(linea.length()>this.combinacionSecreta.getLongitud()||linea.length()<this.combinacionSecreta.getLongitud()){
//				error.append("Longitud erronea: "+linea.length()+". Debe introducir una combinacion de "+this.combinacionSecreta.getLongitud()+" caracteres");
//			}
//			if(error.length()!=0){
//				throw new MastermindException(error.toString());
//			}else{
//				this.combinacionIntroducida=new Combinacion(linea);
//				logger.info("Combinacion Introducida: "+this.combinacionIntroducida.toString());
//			}
//		
//		}
	public int getLongitudCombSecreta(){
		return this.combinacionSecreta.getLongitud();
	}
	/**
	 * @param La cadena sobre la cual queremos comparar los aciertos
	 * 
	 * Mï¿½todo que nos compara dos combinaciones y nos devuelve un array con los aciertos.
	 * @throws MastermindException 
	 */
	public void compararCombinaciones(Combinacion combinacionIntroducida) throws MastermindException{
		
		Combinacion combinacionCopia=new Combinacion(this.combinacionSecreta);
		Combinacion combinacionAciertos=combinacionCopia.compararAciertos(combinacionIntroducida);
		logger.info("Resultado de comparar las combinaciones: "+ combinacionAciertos.toString()); 
		this.tablero.incluyeLinea(combinacionIntroducida,combinacionAciertos);
	}
	

	
	
	/**
	 * @param Intentos totales
	 * @param La combinación introducida por el usuario
	 * 
	 * Mï¿½todo que nos dice si el juego ha terminado. Se debe utilizar dentro del bucle doWhile del juego. El juego pide combinaciones mientras hasTerminado() es false.
	 * @throws MastermindException 
	 */
	 public boolean hasTerminado(int intentosActuales) throws MastermindException{
		boolean terminado = false;
		
		
		if((intentosActuales > this.tablero.getNumeroIntentos()) || this.hasGanado(intentosActuales)=="¡Has ganado!"){
			logger.info("Se ha acabado la partida");
			terminado = true;

			
		}
		
		return terminado;
	 }
	 
	 
	 /**
		 * @param La combinación introducida por el usuario
		 * 
		 * Mï¿½todo que nos dice si hemos ganado la partida
	 * @throws MastermindException 
		 */
	 public String hasGanado(int intentos) throws MastermindException{
		Ficha acierto=new Ficha('O');
		 boolean ganado=true;
		 String mensajeFinal;
		for(int i=0;i<this.combinacionSecreta.getLongitud();i++){
			if(!tablero.getUltimoAcierto().getFicha(i).esIgual(acierto)){
				ganado =false;
			}
		}
		 if(!ganado){
			 mensajeFinal="Has perdido...\n La combinacion secreta era"+this.combinacionSecreta.toString();
		}else{
			mensajeFinal="¡Has ganado!";
		}
		return mensajeFinal; 
	 }
	 /**
	  * Podría pasar que cambiase el numero de colores, así que este método sirve para pasarselo a la interfaz.
	  */
	
	 
	 /**
	  * Método que devuelve los colores de las fichas que debes utilizar para generar una combinación.
	  * @return colores
	 * @throws MastermindException 
	  */
	 
	/* public String getColoresCabecera() throws MastermindException {
		 Ficha[] fichasCabecera = new Ficha[combinacionSecreta.NUM_COLORES];
		 String colores="";
		 
		 for(int i=0; i<combinacionSecreta.NUM_COLORES; i++){
			 fichasCabecera[i].numeroAColor(i);
			 colores+= fichasCabecera[i]+"/";
		 }
		 
		 return colores;
		 
	 }*/
	 
	 /**
	 * Mï¿½todo que nos devuelve un String con toda la informaciï¿½n referente al Mastermind.
	 */
	public String toString(){
			StringBuffer msg = new StringBuffer();
			//msg.append(this.combinacionSecreta+"\n");
				//Descomentar la linea superior para ver la combinacion secreta por pantalla.
			msg.append(tablero);
			msg.append("Introducir una combinación de "+this.combinacionSecreta.getLongitud()+" colores");
			msg.append("\nR/G/Y/B/P/C/salir");
			return msg.toString();
	}
}


