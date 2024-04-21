package br.com.livrariamaua.exception;

public class AwsSecretsException extends RuntimeException {
	
	private static final long serialVersionUID = 415588310091707302L;
	
	private final String info;

	public String getInfo() {
		return info;
	}
	
	public AwsSecretsException(String info, Throwable throwable) {
		super(info, throwable);
		this.info = info;
	}

}
