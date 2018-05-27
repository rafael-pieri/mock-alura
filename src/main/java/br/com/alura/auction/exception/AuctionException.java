package br.com.alura.auction.exception;

public class AuctionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuctionException(String message) {
		super(message);
	}
}