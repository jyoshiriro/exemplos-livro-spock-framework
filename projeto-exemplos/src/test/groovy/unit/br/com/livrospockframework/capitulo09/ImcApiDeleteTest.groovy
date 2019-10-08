package unit.br.com.livrospockframework.capitulo09;

import static spock.lang.Retry.Mode.ITERATION

import spock.lang.Retry
import spock.lang.Retry$Mode
import spock.lang.Specification;

@Retry(count=1, delay=0, mode=ITERATION, condition = { failure.message.contains('error') }, exceptions=[IOException])
class ImcApiDeleteTest extends Specification {
	 
    
    def 'deve excluir um registro de IMC'() {
       expect:
	   1==2
    }
 
    // demais métodos de teste
}