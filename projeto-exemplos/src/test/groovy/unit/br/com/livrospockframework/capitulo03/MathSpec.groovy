package unit.br.com.livrospockframework.capitulo03

import groovy.json.StringEscapeUtils
import spock.lang.Specification

class MathSpec extends Specification {

	def '2 ao quadrado deve ser 4'() {
		expect:
		Math.pow(2, 2) == 4
	}
	
	def '2 ao quadrado deve ser 4 b'() {
		expect:
			Math.pow(2, 2) == 4
	}

	def 'sqrt deve calcular a raiz quadrada'() {
given:
def x = 0
				expect:
		Math.sqrt(4) == 2
		Math.sqrt(25) == 5
		
		and:
		Math.sqrt(144) == 12
	}


	def 'pow deve calcular potência 1'() {
		given:
		def file = new File("oi.txt")
		file.createNewFile()

		and:
		def base = 2
		def potencia = 3
		
		and:
		def esperado = 8
		

		expect:
		Math.pow(base, potencia) == esperado
		

		cleanup:
		file.delete()

		and:
		println('oooo')
	}


	def 'pow deve calcular potência 2'() {
		
		given:
		def file = new File("oi.txt")
		file.createNewFile()		
		
		and:
		def base = 2
		def potencia = 3
		
		and:
		def esperado = 8
		
		when:
		def resultado = Math.pow(base, potencia)
		
		then:
		resultado == esperado
		file.delete()
	}
}
