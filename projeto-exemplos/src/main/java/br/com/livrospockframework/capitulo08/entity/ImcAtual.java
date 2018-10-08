package br.com.livrospockframework.capitulo08.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.livrospockframework.capitulo05.enums.Sexo;

@Entity
@Table(name="imc_atual")
public class ImcAtual {
	
	@Id
	private int id;
	
	@Column(length=50)
	private String nome;
	
	@Column
	private double imc;
	
	@Column
	private Sexo sexo;

	public ImcAtual(int id, String nome, double imc, Sexo sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.imc = imc;
		this.sexo = sexo;
	}

	public ImcAtual() {
		
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImcAtual other = (ImcAtual) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
