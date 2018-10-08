package br.com.livrospockframework.capitulo07;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.com.livrospockframework.capitulo05.enums.FaixaImc;
import br.com.livrospockframework.capitulo07.model.PedidoImc;
import br.com.livrospockframework.capitulo07.model.ResultadoImc;

public class CalculadoraImc {

	public double calcularImc(double peso, double altura) {
		if (peso <= 0) {
			throw new IllegalArgumentException("Peso inválido: " + peso);
		}
		if (altura <= 0) {
			throw new IllegalArgumentException("Altura inválida: " + altura);
		}
		return BigDecimal.valueOf(peso / (altura * altura)).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}	
	
	public ResultadoImc verificarCondicaoImc(PedidoImc pedido) {
		ResultadoImc resultado = new ResultadoImc();
		
		resultado.setNome(pedido.getNome());
		try {
			double imc = this.calcularImc(pedido.getPeso(), pedido.getAltura());
			resultado.setImc(imc);
			resultado.setCondicao(
					FaixaImc.getFaixa(imc, pedido.getSexo()).getDescricao());
		} catch (IllegalArgumentException e) {
			resultado.setCondicao("Impossível calcular IMC: "+e.getMessage());
		}
		
		return resultado;
	}
	
	public List<ResultadoImc> verificarCondicaoImc(List<PedidoImc> pedidos) {
		List<ResultadoImc> resultados = new ArrayList<>();
		
		for (PedidoImc pedido : pedidos) {
			resultados.add(this.verificarCondicaoImc(pedido));
		}
		
		return resultados;
	}
	
}
