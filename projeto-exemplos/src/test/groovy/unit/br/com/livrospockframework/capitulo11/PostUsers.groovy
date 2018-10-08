package unit.br.com.livrospockframework.capitulo11


import org.apache.http.HttpStatus

//import com.google.gson.Gson

import br.com.livrospockframework.capitulo10.model.User
import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class PostUsers extends Specification {

	@Shared properties = new Properties()
	@Shared RESTClient client
	@Shared	def gson = null //new Gson()

	def setupSpec() {
		properties.load(this.class.getResourceAsStream("/api_tests.properties"))

		client = new RESTClient("${properties.url_base}")
	}

	def 'POST usuário válido'() {
		when:
		def newUser = new User(id:999, name:'Zé Ruela', username:'zeruela', email:'zeruela@xmail.br')
		HttpResponseDecorator resp = client.post(
				path: '/users',
				body: newUser, // corpo da requisição
				requestContentType: ContentType.JSON.toString() // definindo o 'context-type' da requisição
				)

		then:
		resp.status == HttpStatus.SC_CREATED // o status HTTP é 201?
	}

	def 'POST usuário inválido'() {
		when:
		HttpResponseDecorator resp = client.post(
				path: '/users',
				body: properties.json_invalido,
				requestContentType: ContentType.JSON.toString())

		then:
		def ex = thrown(HttpResponseException)
		ex.response.status == HttpStatus.SC_BAD_REQUEST // tomamos um 400?
	}
}
