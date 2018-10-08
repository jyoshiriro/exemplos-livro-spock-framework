package br.com.livrospockframework.capitulo06;

public class CalculadoraImc {

	public double calcularImc(double peso, double altura) {
		if (peso <= 0) {
			throw new IllegalArgumentException("Peso inválido: " + peso);
		}
		if (altura <= 0) {
			throw new IllegalArgumentException("Altura inválida: " + altura);
		}
		return peso / (altura * altura);
	}

}
