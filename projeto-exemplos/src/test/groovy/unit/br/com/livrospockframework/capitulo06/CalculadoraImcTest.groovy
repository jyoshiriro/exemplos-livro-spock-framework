package unit.br.com.livrospockframework.capitulo06;

import br.com.livrospockframework.capitulo06.CalculadoraImc
import spock.lang.Specification

class CalculadoraImcTest extends Specification {

	def 'lancar exceção com peso invalido V1'() {
		when:
		boolean houveExcecao
		try {
			new CalculadoraImc().calcularImc(0, 1.70)
			houveExcecao = false
		} catch (IllegalArgumentException e) {
			houveExcecao = true
		}

		then:
		houveExcecao
	}

	def 'lancar exceção com peso invalido V2'() {

		when:
		new CalculadoraImc().calcularImc(0, 1.70)

		then:
		thrown(IllegalArgumentException)
	}

	def 'lancar exceção com peso invalido V3'() {
		when:
		new CalculadoraImc().calcularImc(0, 1.70)

		then:
		thrown(IllegalArgumentException)

		when:
		new CalculadoraImc().calcularImc(-1, 1.70)

		then:
		thrown(IllegalArgumentException)
	}

	def 'lancar exceção c/ mensagem correta p/ peso inválido'() {
		when:
		def peso = -1
		new CalculadoraImc().calcularImc(peso, 1.70)

		then:
		def ex = thrown(IllegalArgumentException)
//		ex.message == "O peso $peso é inválido"
		ex.message == "Peso inválido: ${peso.toDouble()}"
	}

	def 'lancar exceção c/ mensagem correta p/ peso inválido V2'() {
		when:
		def peso = 0
		new CalculadoraImc().calcularImc(peso, 1.70)

		then:
		def ex = thrown(IllegalArgumentException)
		ex.message == "O peso $peso é inválido"
		

		when:
		peso = -2
		new CalculadoraImc().calcularImc(peso, 1.70)

		then:
		ex = thrown(IllegalArgumentException)
		ex.message == "O peso $peso é inválido"
	}
}
