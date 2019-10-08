package unit.br.com.livrospockframework.capitulo09

import spock.lang.*
import br.com.livrospockframework.capitulo05.enums.FaixaImc
import br.com.livrospockframework.capitulo05.enums.Sexo

@Title("Testes de cenários de faixas de IMC")
class FaixaImcTest extends Specification {

	@Issue('https://github.com/perfil/repositorio/issues/12')
	@See(['http://bvsms.saude.gov.br/bvs/dicas/215_obesidade.html', 'http://intranet.sesporte.ce.gov.br/imc/'])
	def "IMC deve estar na faixa correta"() {
		expect:
		FaixaImc.getFaixa(18, Sexo.FEMININO) != FaixaImc.ABAIXO
		FaixaImc.getFaixa(21, Sexo.FEMININO) == FaixaImc.NORMAL
		FaixaImc.getFaixa(27, Sexo.FEMININO) == FaixaImc.ACIMA
		FaixaImc.getFaixa(20, Sexo.MASCULINO) == FaixaImc.ABAIXO
		FaixaImc.getFaixa(23, Sexo.MASCULINO) == FaixaImc.NORMAL
		FaixaImc.getFaixa(28, Sexo.MASCULINO) == FaixaImc.ACIMA
	}
	
	
}
