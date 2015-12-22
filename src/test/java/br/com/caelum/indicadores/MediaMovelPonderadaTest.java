package br.com.caelum.indicadores;


import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderadaTest {
	
	@Test
	public void sequenciaSimplesDeCandles() {
		SerieTemporal serie =
		GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		MediaMovelPonderada mmp = new MediaMovelPonderada();
		//ex: calcula(2): 1*1 + 2*2 +3*3 = 14. Divide por 6, da 14/6
		Assert.assertEquals(14.0/6, mmp.calcula(2, serie), 0.00001);
		Assert.assertEquals(20.0/6, mmp.calcula(3, serie), 0.00001);
		Assert.assertEquals(26.0/6, mmp.calcula(4, serie), 0.00001);
		Assert.assertEquals(32.0/6, mmp.calcula(5, serie), 0.00001);
	}
}
