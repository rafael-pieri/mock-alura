package br.com.alura.auction.infra.dao;

import java.util.List;

import br.com.alura.auction.domain.Auction;

public interface AuctionRepository {
	
	void save(Auction auction);

	List<Auction> closed();

	List<Auction> current();

	default void update(Auction auction) {}
}