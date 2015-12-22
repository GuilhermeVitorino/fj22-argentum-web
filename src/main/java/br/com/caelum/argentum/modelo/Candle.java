package br.com.caelum.argentum.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Candle {
		private final double abertura;
		private final double fechamento;
		private final double minimo;
		private final double maximo;
		private final double volume;
		private final Calendar data;
		
		public Candle(double abertura, double fechamento, double minimo, double maximo, double volume,
				Calendar data) {
			
			if (minimo > maximo) {
				throw new IllegalArgumentException("maximo nao pode ser menor que minimo");
			}
			
			if (abertura < 0) {
				throw new IllegalArgumentException("valor da abetura não pode ser negativo");
			}
			
			if (fechamento < 0) {
				throw new IllegalArgumentException("valor do fechamento não pode ser negativo");
			}
			if (minimo < 0) {
				throw new IllegalArgumentException("valor minimo não pode ser negativo");
			}
			if (maximo < 0) {
				throw new IllegalArgumentException("valor maximo não pode ser negativo");
			}
						
			if (abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0) {
				throw new IllegalArgumentException("");
			}
			
			this.abertura = abertura;
			this.fechamento = fechamento;
			this.minimo = minimo;
			this.maximo = maximo;
			this.volume = volume;
			this.data = data;
		}
		
		public String toString() {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			return "Abertura "+abertura+", Fechamento "+fechamento+
						", Mínima "+minimo+", Máxima "+maximo+", Volume "+
						volume+", Data "+dateFormat.format(data.getTime());
							
		}
		
		public boolean isAlta() {
			return this.abertura < this.fechamento;
		}
		
		public boolean isBaixa() {
			return this.abertura > this.fechamento;
		}

		public double getAbertura() {
			return abertura;
		}

		public double getFechamento() {
			return fechamento;
		}

		public double getMinimo() {
			return minimo;
		}

		public double getMaximo() {
			return maximo;
		}

		public double getVolume() {
			return volume;
		}

		public Calendar getData() {
			return data;
		}
		
}
