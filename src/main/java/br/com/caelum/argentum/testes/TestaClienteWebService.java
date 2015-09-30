package br.com.caelum.argentum.testes;

import java.util.List;

import br.com.caelum.argentum.ws.ClienteWebService;
import br.com.caelum.argentum.modelo.*;

public class TestaClienteWebService {
	
	public static void main(String[] args) {
		ClienteWebService cli = new ClienteWebService();
		List<Negociacao> lista = cli.getNegociacoes();
		for (Negociacao neg : lista)
			System.out.println(neg.getPreco());
	}
}
