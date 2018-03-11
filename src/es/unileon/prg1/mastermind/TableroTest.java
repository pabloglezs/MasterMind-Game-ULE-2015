package es.unileon.prg1.mastermind;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class TableroTest {
Tablero tablero;
Combinacion introducida;
Combinacion secreta;

	@Before
	public void setUp() throws Exception{
		tablero = new Tablero(4,5);
		introducida = new Combinacion("GGGG",4);
		secreta = new Combinacion("GYGG",4);
	}

	@Test
	public void testIniciaTablero(){
		assertEquals(tablero.toString()," -  -  -  - \t -  -  -  - \n -  -  -  - \t -  -  -  - \n -  -  -  - \t -  -  -  - \n -  -  -  - \t -  -  -  - \n -  -  -  - \t -  -  -  - \n");
	}
	
	@Test
	public void testIncluyeLinea() throws MastermindException{
		
		tablero.incluyeLinea(introducida,secreta.compararAciertos(introducida));
		assertEquals(tablero.toString()," -  -  -  - \t -  -  -  - \n -  -  -  - \t -  -  -  - \n -  -  -  - \t -  -  -  - \n -  -  -  - \t -  -  -  - \n[G][G][G][G]\t[O][O][O] - \n");
	}
	
	@Test
	public void testGetNumeroIntentos(){
		assertEquals(tablero.getNumeroIntentos(),5);
	}
	
	@Test
	public void testGetUltimoAcierto() throws MastermindException{
		Combinacion copia=new Combinacion(secreta);
		tablero.incluyeLinea(introducida, copia.compararAciertos(introducida));
		assertEquals(tablero.getUltimoAcierto().toString(),secreta.compararAciertos(introducida).toString());
	}
}
