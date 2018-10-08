package br.com.livrospockframework.capitulo08.model;

public class ResultadoImc extends br.com.livrospockframework.capitulo07.model.ResultadoImc {
	
	private int id;
	
	public ResultadoImc(int novoId, br.com.livrospockframework.capitulo07.model.ResultadoImc resultadoImc) {
		this.id = novoId;
		this.setNome(resultadoImc.getNome());
		this.setImc(resultadoImc.getImc());
		this.setCondicao(resultadoImc.getCondicao());
	}
	
	
	public ResultadoImc(int id, String nome, double imc, String condicao) {
		super(nome, imc, condicao);
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
