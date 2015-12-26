package br.com.caelum.argentum.grafico;

import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorModeloGrafico {
	
	private SerieTemporal serie;
	private int comeco;
	private int fim;
	private LineChartModel modeloGrafico;

	public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.modeloGrafico = new LineChartModel();
	}
	
	public void plotaIndicador(Indicador indicador) {
				
		LineChartSeries chartSerie = new LineChartSeries(indicador.toString());
		
		for (int i = comeco; i <= fim; i++) {
			
			double valor = indicador.calcula(i, serie);
			chartSerie.set(i, valor);
		}
		
		this.modeloGrafico.addSeries(chartSerie);
		this.modeloGrafico.setLegendPosition("w");
		this.modeloGrafico.setTitle("Indicadores");
	}

	public SerieTemporal getSerie() {
		return serie;
	}

	public void setSerie(SerieTemporal serie) {
		this.serie = serie;
	}

	public int getComeco() {
		return comeco;
	}

	public void setComeco(int comeco) {
		this.comeco = comeco;
	}

	public int getFim() {
		return fim;
	}

	public void setFim(int fim) {
		this.fim = fim;
	}

	public LineChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public void setModeloGrafico(LineChartModel modeloGrafico) {
		this.modeloGrafico = modeloGrafico;
	}
	
}
