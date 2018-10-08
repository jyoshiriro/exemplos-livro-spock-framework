package br.com.livrospockframework.capitulo01.model;

public class Lutador {

	private double vida = 100;
	private double forca;

	public Lutador(double forca) {
		this.forca = forca;
	}

	public double aplicarGolpe() {
		return this.forca;
	}
	
	public void receberGolpe(double forca) {
		this.vida = forca >= this.vida ? 0 : this.vida - forca;
	}

	public double getVida() {
		return this.vida;
	}

	public boolean isNocauteado() {
		return this.vida == 0;
	}

}
