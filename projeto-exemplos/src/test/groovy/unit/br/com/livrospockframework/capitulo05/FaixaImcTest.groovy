package unit.br.com.livrospockframework.capitulo05

import spock.lang.*
import br.com.livrospockframework.capitulo05.enums.FaixaImc
import br.com.livrospockframework.capitulo05.enums.Sexo

class FaixaImcTest extends Specification {

	def "IMC deve estar na faixa correta V1"() {
		expect:
		FaixaImc.getFaixa(18, Sexo.FEMININO) == FaixaImc.ABAIXO
		FaixaImc.getFaixa(21, Sexo.FEMININO) == FaixaImc.NORMAL
		FaixaImc.getFaixa(27, Sexo.FEMININO) == FaixaImc.ACIMA
		FaixaImc.getFaixa(20, Sexo.MASCULINO) == FaixaImc.ABAIXO
		FaixaImc.getFaixa(23, Sexo.MASCULINO) == FaixaImc.NORMAL
		FaixaImc.getFaixa(28, Sexo.MASCULINO) == FaixaImc.ACIMA
	}
	
	def "IMC deve estar na faixa correta V2"() {
		setup:
		def cenarios = [
			[imc:18, sexo: Sexo.FEMININO, condicao: FaixaImc.ABAIXO],
			[imc:21, sexo: Sexo.FEMININO, condicao: FaixaImc.NORMAL],
			[imc:27, sexo: Sexo.FEMININO, condicao: FaixaImc.ACIMA],
			[imc:20, sexo: Sexo.MASCULINO, condicao: FaixaImc.ABAIXO],
			[imc:23, sexo: Sexo.MASCULINO, condicao: FaixaImc.NORMAL],
			[imc:28, sexo: Sexo.MASCULINO, condicao: FaixaImc.ACIMA]
		]
		
		expect:
		cenarios.each{
			assert FaixaImc.getFaixa(it.imc, it.sexo) == it.condicao
		}
	}
	
	@See(["oio","kkkk"])
	def "hehehe IMC deve estar na faixa correta"() {
		expect:
		FaixaImc.getFaixa(imc, sexo) == resultado

		where:
		imc | sexo      | resultado
		18  | Sexo.FEMININO  | FaixaImc.ABAIXO
		21  | Sexo.FEMININO  | FaixaImc.NORMAL
		27  | Sexo.FEMININO  | FaixaImc.ACIMA
		20  | Sexo.MASCULINO | FaixaImc.ABAIXO
		23  | Sexo.MASCULINO | FaixaImc.NORMAL
		28  | Sexo.MASCULINO | FaixaImc.ACIMA
	}

	@Unroll
	def "IMC #imc deve estar na faixa #resultado para o sexo #sexo"() {
		expect:
		FaixaImc.getFaixa(imc, sexo) == resultado

		where:
		imc    | sexo       | resultado
		18.99  | Sexo.FEMININO   | FaixaImc.ABAIXO
		19     | Sexo.FEMININO   | FaixaImc.NORMAL
		25.99  | Sexo.FEMININO   | FaixaImc.NORMAL
		26     | Sexo.FEMININO   | FaixaImc.ACIMA
		20.99  | Sexo.MASCULINO  | FaixaImc.ABAIXO
		21     | Sexo.MASCULINO  | FaixaImc.NORMAL
		26.99  | Sexo.MASCULINO  | FaixaImc.NORMAL
		27     | Sexo.MASCULINO  | FaixaImc.ACIMA
	}
	
	def 'IMC deve estar na faixa correta GWT'() {
		when:
		def calculo = new Object() {
			def getFaixa(imc, sexo) {
				FaixaImc.getFaixa(imc, sexo)
			}
		}
	
		then:
		calculo.getFaixa(imc, sexo) == resultado
	
		where:
		imc    | sexo           || resultado
		180     | Sexo.FEMININO  || FaixaImc.NORMAL
		21     | Sexo.FEMININO  || FaixaImc.NORMAL
		27     | Sexo.FEMININO  || FaixaImc.ACIMA
		// demais cenários
	}
}
