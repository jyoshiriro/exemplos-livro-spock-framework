package br.com.livrospockframework.capitulo08

import org.apache.http.HttpResponse
import org.apache.http.conn.BasicManagedEntity
import org.apache.http.entity.HttpEntityWrapper

import br.com.livrospockframework.capitulo05.enums.Sexo
import br.com.livrospockframework.capitulo08.model.PedidoImc
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

class ImcApiPostTest {
	
	public static void main(String[] args) {
		def urlBase = 'http://localhost:8080'
		RESTClient client = new RESTClient(urlBase, ContentType.JSON)
		PedidoImc pedido = new PedidoImc(nome:'Ze Magrinho', altura:1.75, peso: 71, sexo: Sexo.MASCULINO)
		try {
			HttpResponse response = client.post(
					path: "/imcs/calculo",
					headers : [
						'Content-Type' : 'application/json',
						'x-api-key' : 'chave-valida'],
					body: pedido)
			HttpEntityWrapper entity = response.entity
			println response.data
		} catch (Exception e) {
			e.printStackTrace()
			throw new IllegalStateException("deu ruim", e)
		}

	}

}
