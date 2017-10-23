package br.com.alura.auction.exception;

public class AuctionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionException() {
		super();
	}

	public AuctionException(String message) {
		super(message);
	}

	public AuctionException(String message, Throwable cause) {
		super(message, cause);
	}

}
