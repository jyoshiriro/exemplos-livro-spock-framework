package br.com.livrospockframework.capitulo02.model;

import br.com.livrospockframework.exceptions.ForcaInvalidaException;

public class Lutador {

	private double vida = 100;
	private double forca;

	public Lutador(double forca) throws ForcaInvalidaException {
		this.validarForca(forca);
		this.forca = forca;
	}

	public double aplicarGolpe() {
		return this.forca;
	}

	public void receberGolpe(double forca) throws ForcaInvalidaException {
		this.validarForca(forca);
		this.vida = forca >= this.vida ? 0 : this.vida - forca;
	}

	public double getVida() {
		return this.vida;
	}

	public boolean isNoucateado() {
		return this.vida == 0;
	}
	
	private void validarForca(double forca) throws ForcaInvalidaException {
		if (forca <= 0) {
			throw new ForcaInvalidaException(forca); 
		}
	}

}
