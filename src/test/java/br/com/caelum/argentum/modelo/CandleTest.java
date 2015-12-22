package br.com.caelum.argentum.modelo;

import java.util.Calendar;

import org.junit.Test;

public class CandleTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		new Candle (40.5, 45.8,  43.2, 42.2, 100,Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Candle (40.5, 45.8,  43.2, 42.2, 100, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComAberturaNegativa() {
		new Candle (-40.5, 45.8,  43.2, 42.2, 100,Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComMinimoNegativo() {
		new Candle (40.5, -45.8,  43.2, 42.2, 100,Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComMaximoNegativo() {
		new Candle (40.5, 45.8,  -43.2, 42.2, 100,Calendar.getInstance());
	}

}
