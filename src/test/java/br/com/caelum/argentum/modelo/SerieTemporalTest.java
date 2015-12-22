package br.com.caelum.argentum.modelo;

import java.util.ArrayList;

import org.junit.Test;

public class SerieTemporalTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void criaSerieComListaVazia(){
		ArrayList<Candle> listaVazia = new ArrayList<Candle>();
		SerieTemporal serie = new SerieTemporal(listaVazia);
	}
	
}
