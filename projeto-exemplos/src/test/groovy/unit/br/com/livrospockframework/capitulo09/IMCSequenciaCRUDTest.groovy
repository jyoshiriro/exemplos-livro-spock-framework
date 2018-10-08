package unit.br.com.livrospockframework.capitulo09;

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class IMCSequenciaCRUDTest extends Specification {
	
	@Shared def idRegistroCriado;
	
	def 'calcular e registrar IMC'() {
		setup:
		idRegistroCriado = 11
		
		expect:
		true
	}
	
	def 'recuperar o IMC recém criado'() {
		setup:
		idRegistroCriado = 22
		
		expect:
		true
	}
	
	def 'excluir registro de IMC recém criado'() {
		setup:
		def x = idRegistroCriado
		
		expect:
		true
	}
	
	def 'tentar recuperar o IMC recém excluído e não encontrá-lo'() {
		expect:
			true
	}

}
