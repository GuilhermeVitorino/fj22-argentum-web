package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {
	private final List<Candlestick> candles;
	
	public SerieTemporal(List<Candlestick> candles) {
		this.candles = candles;
	}
	
	public Candlestick getCandle(int i) {
		return this.candles.get(i);
	}
	
	public  int getUltimaPosicao() {
		return this.candles.size() - 1;
	}
}
