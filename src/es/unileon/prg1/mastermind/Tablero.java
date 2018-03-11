package es.unileon.prg1.mastermind;

public class Tablero {

private int siguiente;
private Combinacion[] matrizCombinAciertos;
private Combinacion[] matrizIntentos;

	public Tablero (int length, int nIntentos) throws MastermindException{
		this.matrizCombinAciertos = new Combinacion[nIntentos];
		this.matrizIntentos = new Combinacion[nIntentos];
		iniciaTablero(length);
		siguiente=nIntentos-1;
	}

	public void iniciaTablero(int length) throws MastermindException{
		StringBuffer guiones=new StringBuffer();
		for(int i=0;i<this.matrizCombinAciertos.length;i++){
			for (int j=0; j<length; j++){
				guiones.append("-");	
			}
			matrizCombinAciertos[i]=new Combinacion(guiones.toString(),length);
			matrizIntentos[i]=new Combinacion(guiones.toString(),length);
			guiones.delete(0,guiones.length());
		}
	}

	
	
	public void incluyeLinea(Combinacion combinacionIntroducida, Combinacion combinacionAciertos){
		this.matrizCombinAciertos[siguiente]=combinacionAciertos;
		this.matrizIntentos[siguiente--]=combinacionIntroducida;
	}
	public int getNumeroIntentos(){
	
		return this.matrizCombinAciertos.length;
	}
	public Combinacion getUltimoAcierto(){
		return this.matrizCombinAciertos[siguiente+1];
	}
	
	public String toString(){
		String stringImprimir = "";
		for(int i = 0; i < matrizCombinAciertos.length; i++){
			stringImprimir += matrizIntentos[i].toString();
			stringImprimir += "\t";
			stringImprimir += matrizCombinAciertos[i].toString();
			stringImprimir += "\n";
		}
		return stringImprimir;
		
	}

}

