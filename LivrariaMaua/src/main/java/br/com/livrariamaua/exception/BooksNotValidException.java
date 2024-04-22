package br.com.livrariamaua.exception;

public class BooksNotValidException extends RuntimeException {

	private static final long serialVersionUID = -813929563425145998L;
	
	private final String info;

	public String getInfo() {
		return info;
	}
	
	public BooksNotValidException(String info) {
		super(info);
		this.info = info;
	}
	
	public BooksNotValidException(String info, Throwable throwable) {
		super(info, throwable);
		this.info = info;
	}
	
}
