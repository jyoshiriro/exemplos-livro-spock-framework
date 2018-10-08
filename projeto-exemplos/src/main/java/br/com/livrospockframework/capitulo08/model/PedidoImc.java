package br.com.livrospockframework.capitulo08.model;

import br.com.livrospockframework.capitulo05.enums.Sexo;

public class PedidoImc implements br.com.livrospockframework.capitulo07.model.PedidoImc {
	
	private String nome;
	
	private Double altura;
	
	private Double peso;
	
	private Sexo sexo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public int[] getLista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
