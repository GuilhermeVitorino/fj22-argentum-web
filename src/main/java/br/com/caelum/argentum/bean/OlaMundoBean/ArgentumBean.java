package br.com.caelum.argentum.bean.OlaMundoBean;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;


@ManagedBean
@ViewScoped
public class ArgentumBean {
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;
	
	public ArgentumBean() {
		System.out.println("Obtendo negociacoes do WebService...");
		this.negociacoes = new ClienteWebService().getNegociacoes();
		geraGrafico();
	}

	public void geraGrafico() {
		
		System.out.println("PLOTANDO:" + nomeMedia + "de" + nomeIndicadorBase);
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorModeloGrafico geradorGrafico =
				new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());
		//defineIndicador(geradorGrafico);
		geradorGrafico.plotaIndicador(defineIndicador(geradorGrafico));
		this.setModeloGrafico(geradorGrafico.getModeloGrafico());
	}

	private Indicador defineIndicador(GeradorModeloGrafico geradorGrafico) {
		
		if (nomeIndicadorBase == null || nomeMedia == null)
			return new MediaMovelSimples(new IndicadorFechamento());
		
		try {
			
			String pacote = "br.com.caelum.argentum.indicadores.";
			
			Class<?> classeIndicadorBase = Class.forName(pacote + nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();
			
			Class<?> classeMedia = Class.forName(pacote + nomeMedia);
			Constructor<?> construtorMedia = classeMedia.getConstructor(Indicador.class);
			
			Indicador indicador = (Indicador) construtorMedia.newInstance(indicadorBase);
			return indicador;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public void setModeloGrafico(ChartModel modeloGrafico) {
		this.modeloGrafico = modeloGrafico;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

}
