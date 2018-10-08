package unit.br.com.livrospockframework.capitulo02;

import br.com.livrospockframework.capitulo02.model.Lutador
import br.com.livrospockframework.exceptions.ForcaInvalidaException
import spock.lang.Specification

class LutadorForcaInvalidaTest extends Specification {

	def 'deveria ocorrer exceção para força inválida'() {
		when:
		new Lutador(0)

		then:
		thrown(ForcaInvalidaException)
		
		
		when:
		new Lutador(-1)

		then:
		thrown(ForcaInvalidaException)
	}
	
	
	def 'deveria ocorrer exceção com mensagem específica para força inválida'() {
		given:
		def vidaZero = 0.0
		
		when:
		new Lutador(vidaZero)

		then:
		def ex = thrown(ForcaInvalidaException)
		ex.message == "A força deve ser maior que zero. Recebido: ${vidaZero}"
	}

}
