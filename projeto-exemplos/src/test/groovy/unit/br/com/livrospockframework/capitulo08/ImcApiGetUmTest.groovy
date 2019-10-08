package unit.br.com.livrospockframework.capitulo08



import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.HttpResponseException

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Specification

class ImcApiGetUmTest extends Specification {

	RESTClient cliente
	def urlBase = 'http://localhost:8080'
	def uriEndpoint = '/imcs/1'
	
	def criarHeader() {
		['x-api-key' : 'chave-valida']
	}

	def setup() {
		this.cliente = new RESTClient(this.urlBase, ContentType.JSON)
	}

	def 'deve recuperar um IMC'() { 
		
		when:
		HttpResponse response = this.cliente.get(path: this.uriEndpoint, headers: this.criarHeader())

		then: 'Validando os headers da resposta'
		response.status == HttpStatus.SC_OK
		response.getEntity().contentType.value.startsWith(ContentType.JSON.toString());

		and: 'Validando o corpo da resposta'
		response.data.size() == 4 // número de atributos no JSON
		response.data.id == this.uriEndpoint.split('/').last().toInteger()
		response.data.nome instanceof String
		response.data.imc instanceof Number
		response.data.condicao instanceof String
		
	}
	
	def 'deve receber 404 ao tentar recuperar um IMC informando um identificador inexistente'() {
		when:
		this.cliente.get(path: '/imcs/-19', headers: this.criarHeader())

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_NOT_FOUND
	}
	
	def 'deve receber 401 ao tentar recuperar um IMC sem informar a chave da API'() {
		when:
		this.cliente.get(path: this.uriEndpoint)

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_UNAUTHORIZED
	}

	def 'deve receber 403 ao tentar recuperar um IMC informando uma chave da API inválida'() {
		when:
		this.cliente.get(path: this.uriEndpoint, headers : ['x-api-key' : 'chave-INVALIDA'])

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_FORBIDDEN
	}
}
