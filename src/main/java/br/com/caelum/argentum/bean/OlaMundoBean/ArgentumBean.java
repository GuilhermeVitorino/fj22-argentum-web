package br.com.caelum.argentum.bean.OlaMundoBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.argentum.modelo.*;
import br.com.caelum.argentum.ws.ClienteWebService;


@ManagedBean
@ViewScoped
public class ArgentumBean {
	private List<Negociacao> negociacoes;
	
	public ArgentumBean() {
		negociacoes = new ClienteWebService().getNegociacoes();
		System.out.println("Obtendo negociacoes do WebService...");
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

}
