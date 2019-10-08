package unit.br.com.livrospockframework.capitulo08


import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.HttpResponseException

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Specification

class ImcApiPostTest extends Specification {

	RESTClient cliente
	def urlBase = 'http://localhost:8080'
	def uriEndpoint = '/imcs/calculo'
	
	private def criarPedido() {
		[nome:'Ze Magrinho', altura:1.75, peso: 71, sexo: 'MASCULINO']
	}
	
	private def criarHeader() {
		['x-api-key' : 'chave-valida']
	}

	def setup() {
		this.cliente = new RESTClient(this.urlBase, ContentType.JSON)
	}

	def 'deve calcular e registrar IMC'() {
		given:
		System.setProperty("api-token", "a5e61b00-db1f-406b-a344-9d6b3a5bc536")
		def pedido = this.criarPedido() 
		
		when:
		HttpResponse response = this.cliente.post(path: this.uriEndpoint,
									headers: this.criarHeader(),
									body: pedido)

		then: 'Validando os headers da resposta'
		response.status == HttpStatus.SC_CREATED
		response.getEntity().contentType.value.startsWith(ContentType.JSON.toString())
		response.headers.location.toURL().path.split("/").last().toInteger()

		and: 'Validando o corpo da resposta'
		response.data.size() == 4 // número de atributos no JSON
		response.data.id instanceof Number
		response.data.nome == pedido.nome
		response.data.imc instanceof Number
		response.data.condicao instanceof String
		
	}
	
	def 'deve receber 400 ao tentar criar IMC com JSON ausente ou inválido'() {
		when: 'Requisição sem JSON no corpo'
		this.cliente.post(path: this.uriEndpoint, headers: this.criarHeader())

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_BAD_REQUEST

		when: 'Requisição com JSON inválido'
		this.cliente.post(path: this.uriEndpoint,
				headers : ['x-api-key' : 'chave-valida'],
				body: [nome:888, altura:'toma', peso: 'olha', sexo: 1])

		then:
		ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_BAD_REQUEST
	}
	
	def 'deve receber 405 ao tentar usar PUT ao invés de POST no cálculo de IMC'() {
		when:
		this.cliente.put(path: this.uriEndpoint, headers: this.criarHeader(), body: this.criarPedido())

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_METHOD_NOT_ALLOWED
	}

	def 'deve receber 401 ao tentar calcular IMC sem informar a chave da API'() {
		when:
		this.cliente.post(path: this.uriEndpoint, body: this.criarPedido())

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_UNAUTHORIZED
	}

	def 'deve receber 403 ao tentar calcular IMC informando uma chave da API inválida'() {
		when:
		this.cliente.post(path: this.uriEndpoint,
				headers : ['x-api-key' : 'chave-INVALIDA'],
				body: this.criarPedido())

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_FORBIDDEN
	}
}
