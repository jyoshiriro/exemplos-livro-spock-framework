package br.com.livrospock.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livrospock.domain.Lutador;

@Service
public class LutadorService {

	@Autowired
	private EntityManager em;
	
	public Lutador findMelhorLutador() {
		return (Lutador) this.em.createQuery(
				"from Lutador "
				+ "order by vitorias desc, nocontest asc, derrotas asc")
				.setMaxResults(1)
				.getSingleResult();
	}
	
}
