package exemplos.junit5;

import static org.junit.Assert.assertTrue;


/**
 * Teste em JUnit 5
 */
public class TesteJUnit5Test {

	@org.junit.jupiter.api.Test
	public void vaiDarBom() {
		assertTrue(1==2);
	}
}
