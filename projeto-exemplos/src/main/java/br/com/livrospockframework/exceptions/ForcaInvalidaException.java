package br.com.livrospockframework.exceptions;

public class ForcaInvalidaException extends Exception {

	public ForcaInvalidaException(double forca) {
		super(String.format("A força deve ser maior que zero. Recebido: %s", forca));
	}
	
}
