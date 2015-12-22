package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {
	private final List<Candle> candles;
	
	public SerieTemporal(List<Candle> candles) {
		if (candles.size()==0)
			throw new IllegalArgumentException("a lista enviada como argumento"
					+ " para o construtor n�o pode ser vazia");
		
		if (candles.equals(null))
			throw new IllegalArgumentException("a lista enviada como argumento"
					+ " para o construtor n�o pode ser nula");
		
		this.candles = candles;
	}
	
	public Candle getCandle(int i) {
		return this.candles.get(i);
	}
	
	public  int getUltimaPosicao() {
		return this.candles.size() - 1;
	}
}
