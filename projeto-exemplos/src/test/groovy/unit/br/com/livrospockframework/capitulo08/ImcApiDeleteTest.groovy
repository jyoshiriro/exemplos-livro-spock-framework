package unit.br.com.livrospockframework.capitulo08


import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.HttpResponseException

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Specification

class ImcApiDeleteTest extends Specification {

	RESTClient cliente
	def urlBase = 'http://localhost:8080'
	def uriEndpoint = '/imcs/5'
	
	def criarHeader() {
		['x-api-key' : 'chave-valida']
	}

	def setup() {
		this.cliente = new RESTClient(this.urlBase, ContentType.JSON)
	}

	def 'deveria excluir um registro de IMC'() { 
		
		when:
		HttpResponse response = this.cliente.delete(path: this.uriEndpoint, headers: this.criarHeader())

		then: 'Validando os headers da resposta'
		response.status == HttpStatus.SC_OK
		
	}
	
	def 'deveria receber 404 ao tentar excluir IMC com identificador inexistente'() {
		when:
		this.cliente.delete(path: '/imcs/-19', headers: this.criarHeader())
		
		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_NOT_FOUND
	}

	def 'deveria receber 401 ao tentar excluir IMC sem informar uma chave da API'() {
		when:
		this.cliente.delete(path: this.uriEndpoint)

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_UNAUTHORIZED
	}

	def 'deveria receber 403 ao tentar excluir IMC informando uma chave da API inválida'() {
		when:
		this.cliente.delete(path: this.uriEndpoint, headers : ['x-api-key' : 'chave-INVALIDA'])

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_FORBIDDEN
	}
}
