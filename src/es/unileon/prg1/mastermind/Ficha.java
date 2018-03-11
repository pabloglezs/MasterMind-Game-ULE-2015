package es.unileon.prg1.mastermind;
/**
 * 
 * @author Alejandro Moya García, Pablo González de la Iglesia, Juan Carlos Gutiérrez Vicente, Ignacio Rodríguez Basante
 * @version 1.0
 */
public class Ficha {
	
	private char color;
	/**
	 * Constructor de la clase Ficha.
	 * @param color
	 */
	public Ficha(char color)throws MastermindException{
		StringBuffer error=new StringBuffer();
		if(!fichaPermitida(color)){
			error.append("\nError. "+ color+" no es un color permitido");
		}
		if(error.length()!=0){
			throw new MastermindException(error.toString());
		}else{
			this.color = color;
		}
		
	}	
	/**
	 * 
	 * @param otraFicha
	 * @return
	 */
	public boolean esIgual(Ficha otraFicha){
		return this.getColor()==otraFicha.getColor();
	}
	
 /**
  * Método para obtener el color de una ficha
  * @return caracter correspondiente con el color de la ficha
  */
	public char getColor() {
		return this.color;
	}
/**
 * Método para cambiar el color de una ficha.
 * @param color
 */
	public void setColor(char color)throws MastermindException {
		
		StringBuffer error=new StringBuffer();
		if(!fichaPermitida(color)){
			error.append("\nError."+ color+" no es un color permitido");
		}
		if(error.length()!=0){
			throw new MastermindException(error.toString());
		}else{
			this.color = color;
		}
	}
	
/**
 * Método que elimina el color de una ficha.
 * @throws MastermindException 
 */
	public void fichaSinColor() throws MastermindException{
		this.setColor('-');
	}

/**
 * Método que asigna un color aleatorio a la ficha.
 * @param aleatorio
 * @throws MastermindException 
 */
	public void numeroAColor(int aleatorio) throws MastermindException{
		switch(aleatorio){
		case 0:
			this.setColor('R');
			break;
		case 1:
			this.setColor('G');
			break;
		case 2:
			this.setColor('B');
			break;
		case 3:
			this.setColor('Y');
			break;
		case 4:
			this.setColor('P');
			break;
		case 5:
			this.setColor('C');
			break;
		}
		
	}
	public boolean fichaPermitida(char color){
		char[] coloresDisponibles = {'R', 'G', 'B', 'Y', 'P', 'C' ,'-','O','X'};
		boolean permitida=false;
		for(int i=0;i<coloresDisponibles.length;i++){
			if(color==coloresDisponibles[i]){
				permitida=true;
			}
		}
		return permitida;
	}
	public String toString(){
		String salida;
		if(this.color=='-'){
			salida=" - ";
		}else{
		salida= "["+this.color+"]";
		}
	return salida;
	}
	
}
