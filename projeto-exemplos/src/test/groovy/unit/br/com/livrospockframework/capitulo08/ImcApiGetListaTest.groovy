package unit.br.com.livrospockframework.capitulo08


import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.HttpResponseException
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Specification

class ImcApiGetListaTest extends Specification {

	RESTClient cliente
	def urlBase = 'http://localhost:8080'
	def uriEndpoint = '/imcs'
	
	def criarHeader() {
		['x-api-key' : 'chave-valida']
	}

	def setup() {
		this.cliente = new RESTClient(this.urlBase, ContentType.JSON)
	}

	def 'deveria recuperar lista de todos os IMCs'() { 
		
		when:
		HttpResponse response = this.cliente.get(path: this.uriEndpoint, headers: this.criarHeader())

		then: 'Validando os headers da resposta'
		response.status == HttpStatus.SC_OK
		response.getEntity().contentType.value.startsWith(ContentType.JSON.toString());

		and: 'Validando o corpo da resposta'
		response.data.size() > 0 // número de itens na lista de JSONs
		for (item in response.data) {
			item.id instanceof Number
			item.nome instanceof String
			item.imc instanceof Number
			item.condicao instanceof String
		}
		
	}
	
	def 'deveria filtrar corretamente os IMCs'() { 
		
		when: 'Somente parâmetro "apartir" impossível de existir'
		HttpResponse response = this.cliente.get(path: this.uriEndpoint, 
			headers: this.criarHeader(), 
			query: [apartir: Integer.MAX_VALUE])
		
		then: 'Validando os headers da resposta'
		response.status == HttpStatus.SC_NO_CONTENT
		
		and: 'Validando o corpo da resposta'
		response.data == null
		
		
		when: 'Somente parâmetro "ate" impossível de existir'
		response = this.cliente.get(path: this.uriEndpoint, 
				headers: this.criarHeader(), 
				query: [ate: Integer.MIN_VALUE])
		
		then: 'Validando os headers da resposta'
		response.status == HttpStatus.SC_NO_CONTENT
		
		and: 'Validando o corpo da resposta'
		response.data == null
		
		
		when: 'Parâmetros "apartir" e "ate" com valores extremos possíveis'
		response = this.cliente.get(path: this.uriEndpoint,
				headers: this.criarHeader(),
				query: [apartir: Integer.MIN_VALUE, ate: Integer.MAX_VALUE])
		
		then: 'Validando os headers da resposta'
		response.status == HttpStatus.SC_OK
		response.getEntity().contentType.value.startsWith(ContentType.JSON.toString());
		
		and: 'Validando o corpo da resposta'
		response.data.size() > 0 // número de itens na lista de JSONs
		for (item in response.data) {
			item.id instanceof Number
			item.nome instanceof String
			item.imc instanceof Number
			item.condicao instanceof String
		}
					
	}

	def 'deveria receber 401 ao tentar recuperar IMCs sem informar a chave da API'() {
		when:
		this.cliente.get(path: this.uriEndpoint,
			query: [apartir: Integer.MIN_VALUE, ate: Integer.MAX_VALUE])

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_UNAUTHORIZED
	}

	def 'deveria receber 403 ao tentar recuperar IMCs informando uma chave da API inválida'() {
		when:
		this.cliente.get(path: this.uriEndpoint, headers : ['x-api-key' : 'chave-INVALIDA'],
			query: [apartir: Integer.MIN_VALUE, ate: Integer.MAX_VALUE])

		then:
		HttpResponseException ex = thrown(HttpResponseException)
		ex.statusCode == HttpStatus.SC_FORBIDDEN
	}
}
