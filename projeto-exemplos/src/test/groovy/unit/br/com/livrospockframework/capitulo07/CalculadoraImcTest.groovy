package unit.br.com.livrospockframework.capitulo07;

import br.com.livrospockframework.capitulo05.enums.FaixaImc
import br.com.livrospockframework.capitulo05.enums.Sexo
import br.com.livrospockframework.capitulo07.CalculadoraImc
import br.com.livrospockframework.capitulo07.model.PedidoImc
import spock.lang.Specification

class CalculadoraImcTest extends Specification {

	
	def 'deve ser um homem acima do peso'() {
		given:
		def pedido = Mock(PedidoImc)
		
		pedido.getNome() >> "Zé Fofinho"
		pedido.getSexo() >> Sexo.MASCULINO
		pedido.getPeso() >> 72
		pedido.getAltura() >> 1.50
		
		def x = Mock(PedidoImc).getLista()
		
		when:
		def resultado = new CalculadoraImc().verificarCondicaoImc(pedido)
		
		then:
		resultado.imc == 32
		resultado.condicao == FaixaImc.ACIMA.descricao
	}
	
	def 'não deveria calcular quando peso <= 0'() {
		given:
		def pedido = Mock(PedidoImc)
		
		pedido.getNome() >> "João Sem Peso"
		pedido.getPeso() >> 0
		pedido.getAltura() >> 1.75
		
		when:
		def resultado = new CalculadoraImc().verificarCondicaoImc(pedido)
		
		then:
		!resultado.imc
	}
	
	def 'deve calcular somente quando possível'() {
		given:
		def pedido1 = Mock(PedidoImc)
		def pedido2 = Mock(PedidoImc)
		def pedido3 = Mock(PedidoImc)
				
		pedido1.getNome() >> "Carlos Sem Peso"
		pedido1.getPeso() >> 0
		pedido1.getAltura() >> 1.80
		
		pedido2.getNome() >> "Maria sem Altura"
		pedido2.getPeso() >> 60
		pedido2.getAltura() >> -1.70
		
		pedido2.getNome() >> "Felipe Completo"
		pedido3.getPeso() >> 80
		pedido3.getAltura() >> 1.40
				
		def pedidos = [pedido1, pedido2, pedido3]
		
		when:
		new CalculadoraImc().verificarCondicaoImc(pedidos)

		then:
		0 * pedido1.getSexo()
		0 * pedido2.getSexo()
		1 * pedido3.getSexo()
		
	}

}
