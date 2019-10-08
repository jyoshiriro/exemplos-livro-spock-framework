package unit.br.com.livrospockframework.capitulo09;

import groovy.transform.NotYetImplemented
import spock.lang.Ignore
import spock.lang.IgnoreIf
import spock.lang.IgnoreRest
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.PendingFeature
import spock.lang.See
import spock.lang.Specification
import spock.lang.Timeout
import spock.lang.Title
import spock.util.environment.Jvm
import spock.util.environment.OperatingSystem

import java.util.concurrent.TimeUnit

@Title('Testes em cenários de obesidade')
@Narrative("""
Narrativa:
Há muito tempo, era uma vez...
e assim vai
""")
@See("http://spockframework.org/spock/docs/1.1/extensions.html")
class TitleNarrativeSeeIssueTest extends Specification {
	
	@Issue("http://my.issues.org/FOO-1")
	@See(['http://bvsms.saude.gov.br/bvs/dicas/215_obesidade.html', 'http://intranet.sesporte.ce.gov.br/imc/'])
	def 'deve funcionar se fizer certo'() {
		expect: 
		true
	} 
	
}
