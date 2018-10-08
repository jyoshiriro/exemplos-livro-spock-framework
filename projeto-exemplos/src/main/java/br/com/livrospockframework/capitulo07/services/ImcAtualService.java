package br.com.livrospockframework.capitulo07.services;

import java.util.List;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.livrospockframework.capitulo07.entity.ImcAtual;
import br.com.livrospockframework.capitulo07.model.ResultadoImc;

public class ImcAtualService {

	private static final String CONSULTA_IMC = 
			"SELECT i FROM ImcAtual i WHERE nome = ?0";

	private EntityManager entityManager;

	public int registrarImc(List<ResultadoImc> resultados) throws SQLException {

		int inseridos = 0;
		for (ResultadoImc resultado : resultados) {
			ImcAtual imcExistente = this.imcExistente(resultado.getNome());
			if (imcExistente != null) {
				imcExistente.setImc(resultado.getImc());
				continue;
			}
			ImcAtual imcAtual = new ImcAtual(resultado.getNome(), resultado.getImc());
			this.entityManager.persist(imcAtual);
			inseridos++;
		}

		return inseridos;
	}

	private ImcAtual imcExistente(String nome) {
		
		try {
			return this.entityManager
					.createQuery(CONSULTA_IMC, ImcAtual.class)
					.setParameter(0, nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
}
