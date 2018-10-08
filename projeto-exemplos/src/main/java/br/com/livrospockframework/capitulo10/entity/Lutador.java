package br.com.livrospockframework.capitulo10.entity;


import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lutador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lutador")
	private Long id;
	
	@Column(nullable = false, length=30)
	private String nome;
	
	@Column(scale=2)
	private double peso;
	
	private Calendar nascimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}

	
	
}
