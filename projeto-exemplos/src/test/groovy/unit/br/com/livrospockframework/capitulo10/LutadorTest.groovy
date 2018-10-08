package unit.br.com.livrospockframework.capitulo10;

import java.lang.reflect.Field

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

import br.com.livrospockframework.capitulo10.entity.Lutador
import spock.lang.Specification

class LutadorTest extends Specification {
	
	Class classe = Lutador.class;

	def 'classe deve estar anotada com @Entity vazia e sem @Table'() {
		expect:
		!classe.getAnnotation(Entity).name()
		!classe.getAnnotation(Table)
	}
	
	def '"id" deve estar anotado com @Id, @GeneratedValue strategy=IDENTITY e @Column name="id_lutador" '() {
		setup:
		def campo = classe.getDeclaredField('id')
		def anotacaoId = campo.getDeclaredAnnotation(Id)
		def anotacaoGeneratedValue = campo.getDeclaredAnnotation(GeneratedValue)
		def anotacaoColumn = campo.getDeclaredAnnotation(Column)
		
		expect:
		anotacaoId
		anotacaoGeneratedValue.strategy() == GenerationType.IDENTITY
		anotacaoColumn.name() == 'id_lutador'
	}
	
	def '"nome" deve estar anotado com @Column nullable = false, length=30 '() {
		setup:
		def anotacaoColumn = classe.getDeclaredField('nome').getDeclaredAnnotation(Column)
		
		expect:
		!anotacaoColumn.nullable()
		anotacaoColumn.length() == 30
	}
	
	def '"peso" deve estar anotado com @Column nullable = false, scale=2 '() {
		setup:
		def anotacaoColumn = classe.getDeclaredField('peso').getDeclaredAnnotation(Column)
		
		expect:
		anotacaoColumn.scale() == 2
	}
	
	def '"nascimento" deve estar SEM anotações '() {
		expect:
		!classe.getDeclaredField('nascimento').getDeclaredAnnotations()
	}
}
