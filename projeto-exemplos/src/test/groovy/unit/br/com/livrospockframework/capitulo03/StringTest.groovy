package unit.br.com.livrospockframework.capitulo03;

import spock.lang.Specification

class StringTest extends Specification {
	
	def "toUpperCase() deveria por tudo em caixa alta"() {
		expect:
		"bom dia".toUpperCase() == "Bom Dia"
	}

	def "substring() deveria extrair a parte desejada do texto"() {
		expect:
		"sejam bem vindos!".substring(0,4) == "sejam"
	}
}
