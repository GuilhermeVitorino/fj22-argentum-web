package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CandlestickFactory {
	
	// ctrl + 1 para adicionar o return automaticamente
	public Candlestick constroiCandleParaData(Calendar data,
										List<Negociacao> negociacoes) {
		//double maximo = negociacoes.get(0).getPreco();
		//double minimo = negociacoes.get(0).getPreco();
		double maximo = 0;
		double minimo = Double.MAX_VALUE;
		
		double volume = 0;
		
		// digite foreach e dê um ctrl + espaço para ajudar a
		// criar o bloco abaixo!
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
		
			if (negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();
			}
			
			if (negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}
		}
		
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 :
							negociacoes.get(negociacoes.size() - 1).getPreco();
		if(negociacoes.isEmpty())
			minimo=0;
		//double abertura = negociacoes.get(0).getPreco();
		//double fechamento = negociacoes.get(negociacoes.size()-1).getPreco();
		System.out.println(minimo);
		return new Candlestick(abertura, fechamento, minimo, maximo,
														volume, data);
				
	}

	public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacoes) {
		
		Collections.sort(todasNegociacoes,
				new Comparator<Negociacao>() {
					@Override
					public int compare(Negociacao n1, Negociacao n2) {
						return n1.getData().getTime().compareTo(n2.getData().getTime());
					}
		}); //Ordenar negociações!!!
		
		List<Candlestick> candles = new ArrayList<Candlestick>();
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		
		for (Negociacao negociacao : todasNegociacoes) {
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if (!negociacao.isMesmoDia(dataAtual)) {
				Candlestick candleDoDia = constroiCandleParaData(dataAtual,
				negociacoesDoDia);
				candles.add(candleDoDia);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
		negociacoesDoDia.add(negociacao);
		}
		
		dataAtual = todasNegociacoes.get(0).getData();
		
		// adiciona último candle
		Candlestick candleDoDia = constroiCandleParaData(dataAtual,
		negociacoesDoDia);
		
		candles.add(candleDoDia);
		
		for (Negociacao negociacao : todasNegociacoes) {
			System.err.println(negociacao.getData().getTime());
		}
		
		for (Negociacao negociacao : todasNegociacoes) {
			
			System.err.println("Data negociação: "+negociacao.getData().getTime());
			System.err.println("Data atual: "+dataAtual.getTime());
			if (negociacao.getData().before(dataAtual)) {
				throw new IllegalStateException("negociações em ordem errada");
			}
			// se não for mesmo dia, fecha candle e reinicia variáveis
		}
		
		return candles;
	}
}
