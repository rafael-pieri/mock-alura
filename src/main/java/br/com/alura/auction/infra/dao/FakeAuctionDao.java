package br.com.alura.auction.infra.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.auction.domain.Auction;

public class FakeAuctionDao implements AuctionRepository {

	private static List<Auction> auctions = new ArrayList<>();

	public void save(Auction auction) {
		auctions.add(auction);
	}

	public List<Auction> closed() {

		List<Auction> filtered = new ArrayList<>();

		for (Auction auction : auctions) {
			if (auction.isClosed())
				filtered.add(auction);
		}

		return filtered;
	}

	public List<Auction> current() {

		List<Auction> filtered = new ArrayList<>();

		for (Auction auction : auctions) {
			if (!auction.isClosed())
				filtered.add(auction);
		}

		return filtered;
	}

}
