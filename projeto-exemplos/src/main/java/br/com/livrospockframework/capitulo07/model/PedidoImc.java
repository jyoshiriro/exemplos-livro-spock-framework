package br.com.livrospockframework.capitulo07.model;

import br.com.livrospockframework.capitulo05.enums.Sexo;

public interface PedidoImc {

	String getNome();

	double getPeso();

	double getAltura();

	Sexo getSexo();

	int[] getLista();
}
