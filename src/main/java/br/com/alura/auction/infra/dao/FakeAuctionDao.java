package br.com.alura.auction.infra.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.auction.domain.Auction;
import java.util.stream.Collectors;

public class FakeAuctionDao implements AuctionRepository {

	private static List<Auction> auctions = new ArrayList<>();

	public void save(Auction auction) {
		auctions.add(auction);
	}

	public List<Auction> closed() {
		return auctions.stream().filter(Auction::isClosed).collect(Collectors.toList());
	}

	public List<Auction> current() {
		return auctions.stream().filter(auction -> !auction.isClosed()).collect(Collectors.toList());
	}
}