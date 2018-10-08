package unit.br.com.livrospockframework.capitulo11


import org.apache.http.HttpStatus
//import com.google.gson.Gson

import br.com.livrospockframework.capitulo10.model.User
import groovy.json.JsonBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class GetUsersTest extends Specification {

	@Shared properties = new Properties()
	@Shared def client
	@Shared	def gson = null //new Gson()
	
	def setupSpec() {
		properties.load(this.class.getResourceAsStream("/api_tests.properties"))
		
		client = new RESTClient("${properties.url_base}")
	}

	def 'GET todos os usuários'() {
		when:
		HttpResponseDecorator resp = client.get(path:'/users')

		then:
		resp.status == HttpStatus.SC_OK // o status HTTP é 200?
		resp.contentType == ContentType.JSON.toString()  // o content-type é 'application/json'?
		resp.headers['Set-Cookie'] // existe o cabeçalho 'Set-Cookie'?

		resp.getData() instanceof List // a resposta foi entendida como uma 'lista' JSON ou XML?
		!resp.getData().empty // a 'lista' da resposta contém pelo menos 1 elemento?

		def jsonBuilder = new JsonBuilder(resp.getData()[0])
		gson.fromJson(jsonBuilder.toPrettyString(), User.class)
		// Se o JSON não possuir 'entidade aninhadas', podemos fazer apenas:
		// def user = new User(resp.getData()[0])
	}

	def 'GET usuário com id específico'() {
		given:
		def idUsuario = properties.id_usuario_existente

		when:
		HttpResponseDecorator resp = client.get(path:"/users/${idUsuario}")

		then:
		def jsonBuilder = new JsonBuilder(resp.getData())
		gson.fromJson(jsonBuilder.toPrettyString(), User.class)
	}

	def 'Get usuário com id inexistente'() {
		given:
		def idUsuario = properties.id_usuario_inexistente

		when:
		HttpResponseDecorator resp = client.get(path:"/users/${idUsuario}")

		then:
		def ex = thrown(HttpResponseException)
		ex.response.status == HttpStatus.SC_NOT_FOUND // tomamos um 404? 
	}
}
