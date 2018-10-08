package br.com.livrospockframework.capitulo07.model;

public class GoogleUrlConfig {

	private String urlBase;
	private String apiKey;

	public GoogleUrlConfig() {
	}
	
	public GoogleUrlConfig(String urlBase, String apiKey) {
		super();
		this.urlBase = urlBase;
		this.apiKey = apiKey;
	}

	public String getUrl() {
		return String.format("%s%s", urlBase, apiKey);
	}

	public String getUrlBase() {
		return urlBase;
	}

	public void setUrlBase(String baseUrl) {
		this.urlBase = baseUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
