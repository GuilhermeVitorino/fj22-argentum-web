package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CandleFactory {
	
	// ctrl + 1 para adicionar o return automaticamente
	public Candle constroiCandleParaData(Calendar data,
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
		
			double preco = negociacao.getPreco();
			if (preco > maximo) {
				maximo = preco;
			}
			
			if (preco < minimo) {
				minimo = preco;
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
		return new Candle(abertura, fechamento, minimo, maximo,
														volume, data);
				
	}

	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {
		
		Collections.sort(todasNegociacoes,
				new Comparator<Negociacao>() {
					@Override
					public int compare(Negociacao n1, Negociacao n2) {
						return n1.getData().getTime().compareTo(n2.getData().getTime());
					}
		}); //Ordenar negociações!!!
		
		List<Candle> candles = new ArrayList<Candle>();
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		
		for (Negociacao negociacao : todasNegociacoes) {
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if (!negociacao.isMesmoDia(dataAtual)) {
				criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
		negociacoesDoDia.add(negociacao);
		}
		
		dataAtual = todasNegociacoes.get(0).getData();
		
		// adiciona último candle
		criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
		
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

	private void criaEGuardaCandle(List<Candle> candles,
			List<Negociacao> negociacoesDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual,
		negociacoesDoDia);
		candles.add(candleDoDia);
	}
}
