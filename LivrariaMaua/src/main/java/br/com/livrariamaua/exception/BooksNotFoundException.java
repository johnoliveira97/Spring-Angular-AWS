package br.com.livrariamaua.exception;

public class BooksNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5322887478378677644L;
	
	private final String info;

	public String getInfo() {
		return info;
	}
	
	public BooksNotFoundException(String info) {
		super(info);
		this.info = info;
	}
	
	public BooksNotFoundException(String info, Throwable throwable) {
		super(info, throwable);
		this.info = info;
	}
	
}
