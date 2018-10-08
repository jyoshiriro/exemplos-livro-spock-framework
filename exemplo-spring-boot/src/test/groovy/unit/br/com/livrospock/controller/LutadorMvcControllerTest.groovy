package unit.br.com.livrospock.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import br.com.livrospock.LutadoresApp
import br.com.livrospock.controller.LutadorMvcController
import spock.lang.Specification

@WebMvcTest(LutadorMvcController) // classe com @Controller alvo dos testes, necessário esse atributo caso deseje testar um controller
@ContextConfiguration(classes=LutadoresApp) //  classe anotada com @SpringBootApplication
class LutadorMvcControllerTest extends Specification {
			
	@Autowired
	MockMvc mvc
	
	@Autowired
	LutadorMvcController controller
	
	@Value('${spring.mvc.view.prefix}')
	String viewPrefix
	
	def 'deveria retornar status 200 e redirecionar p/ JSP correto'() {
		given:
		def destino = "home"
		 
		when:
		def resposta = mvc.perform(MockMvcRequestBuilders.get("/home").param("usuario", "Lady Gaga"))
		
		then:
		resposta
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.forwardedUrl("${viewPrefix}${destino}.jsp"))
	}
	
	def 'deveria retornar status 400 quando parâmetro o "usuario" não for enviado'() {			
		when:
		def resposta = mvc.perform(MockMvcRequestBuilders.get("/home"))
				
		then:
		resposta.andExpect(MockMvcResultMatchers.status().is(400))
	}
	
}