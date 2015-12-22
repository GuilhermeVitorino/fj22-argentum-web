package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada {
	
	public double calcula(int posicao, SerieTemporal serie) {
		
		double soma = 0.0;
		int peso = 3;
		
		for (int i = posicao; i > posicao - 3; i--) {
			
			Candle c = serie.getCandle(i);
			soma += c.getFechamento() * peso;
			peso--;
		}
		
		return soma / 6;
	}
}
