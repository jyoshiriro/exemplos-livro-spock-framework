package br.com.livrospockframework.capitulo07.services;

import javax.persistence.EntityManager;

public interface ConexaoBancoService {

	public EntityManager criarConexao();
	
}
