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

@Title('título e tal')
@Narrative("""
Narrativa:
Há muito tempo, era uma vez...
e assim vai
""")
@See("http://spockframework.org/spock/docs/1.1/extensions.html")
class TitleNarrativeSeeIssueTest extends Specification {
	
	@Issue("http://my.issues.org/FOO-1")
//	@IgnoreRest
	def 'teste qualquer coisa'() {
		expect: 
		true
	} 
	
	@Issue("http://my.issues.org/FOO-2")
//	@IgnoreRest
	@Ignore
	def 'teste e falhe qualquer coisa'() {
		expect: 
		false
	} 
	
	@Issue("http://my.issues.org/FOO-2")
	@Ignore
	def 'teste e tal'() {
		expect: 
			false
	} 
	
	@PendingFeature
	def 'teste PendingFeature passando'() {
		expect:
		true
	}
	
	@PendingFeature
	def 'teste PendingFeature não passando'() {
		expect:
			false
	}
	
	@NotYetImplemented
	def 'teste NotYetImplemented passando'() {
		expect:
		true
	}
	
	@NotYetImplemented
	def 'teste NotYetImplemented não passando'() {
		expect:
			false
	}

	@Timeout(1)
	def 'teste de 1 até segundo'() {

		expect:
		Thread.sleep(1005)
		true
	}

	@Timeout(value = 1, unit = TimeUnit.MINUTES)
	def 'teste de 1 até minuto'() {

		expect:
//		Thread.sleep(60005)
		true
	}
	
	
	@IgnoreIf({sys["os.name"].toLowerCase().contains("windows")})
	def 'não teste se Windows'() {
		def v = System.getProperties()
		def z = v['os.version']
		println(v.keySet())
		println(v['user.country'])
		println(System.getenv().keySet())
		println(System.getenv('SHELL'))
		expect:
		true
	}
	
	@IgnoreIf({System.getProperty("os.name").toLowerCase().contains("linux")})
	def 'não teste se Linux'() {
		expect:
			true
	}
	
	@IgnoreIf({System.getProperty("os.name").toLowerCase().contains("linux") || System.getProperty("os.name").toLowerCase().contains("windows")})
	def 'não teste se Windows ou Linux'() {
		System.getenv(name)
		expect:
			true
	}
	

}
