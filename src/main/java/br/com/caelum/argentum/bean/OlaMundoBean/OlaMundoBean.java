package br.com.caelum.argentum.bean.OlaMundoBean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OlaMundoBean {
	private String mensagem = "Quem é você?";
	private String nome = "";
	
	public void nomeFoiDigitado() {
		System.out.println("\nChamou o botão");
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
