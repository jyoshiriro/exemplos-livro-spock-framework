package unit.br.com.livrospock.controller

import br.com.livrospock.LutadoresApp
import br.com.livrospock.controller.LutadorController
import br.com.livrospock.domain.Lutador
import br.com.livrospock.repository.LutadorRepository
import br.com.livrospock.service.LutadorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

import spock.lang.Specification
import spock.lang.Stepwise

//@ActiveProfiles("test")
@SpringBootTest(classes = LutadoresApp) //  classe anotada com @SpringBootApplication
@Stepwise 
class LutadorControllerTest extends Specification {	
	
	@Autowired
	LutadorController controller
	
	@Autowired
	LutadorRepository repository

	@Autowired
	LutadorService service
	
	@Value('${msg.erro.cadastro}')
	private String msgErroCadastro;
	
	def 'deveria retornar status 204 e sem corpo quando não existirem lutadores'() {
		when:
		def resposta = controller.getLutadores()
		
		then:
		resposta.statusCode == HttpStatus.NO_CONTENT
		!resposta.body
		
	}
	
	def 'deveria retornar status 400 e mensagem esperada quando falha em criar Lutador'() {
		when:
		def resposta = controller.criarLutador(new Lutador())
		
		then:
		resposta.statusCode == HttpStatus.BAD_REQUEST
		resposta.body == msgErroCadastro
	}
	
	def 'deveria retornar status 201 com JSON correto quando cria um Lutador'() {
		given:
		def lutador = new Lutador(nome: 'Zé Ruela', peso: 80, altura: 1.9)
		
		when:
		def resposta = controller.criarLutador(lutador)
		
		then:
		resposta.statusCode == HttpStatus.CREATED
		resposta.body.properties == lutador.properties
	}
	
	def 'deveria retornar 200 trazer todos os lutadores e no formato correto quando existirem Lutadores'() {
		given:
		def quantidade = repository.count() 
		
		when:
		def resposta = controller.getLutadores()
		
		then:
		resposta.statusCode == HttpStatus.OK
		resposta.body.size() == quantidade
		
		and:
		for (lutador in resposta.body) {
			lutador instanceof Lutador			
		}
	}
	
}