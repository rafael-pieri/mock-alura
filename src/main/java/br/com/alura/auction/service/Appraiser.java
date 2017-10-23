package br.com.alura.auction.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Bid;
import br.com.alura.auction.exception.AuctionException;

public class Appraiser {

	private double highestOfAll = Double.NEGATIVE_INFINITY;
	private double lowestOfAll = Double.POSITIVE_INFINITY;
	private List<Bid> highest;

	public void evaluate(Auction auction) {

		if (auction.getBids().isEmpty()) {
			throw new AuctionException("It's not possible to evaluate an auction without bids.");
		}

		for (Bid bid : auction.getBids()) {
			if (bid.getValue() > highestOfAll)
				highestOfAll = bid.getValue();
			if (bid.getValue() < lowestOfAll)
				lowestOfAll = bid.getValue();
		}

		threeHighest(auction);
	}

	private void threeHighest(Auction auction) {
		highest = new ArrayList<>(auction.getBids());

		Collections.sort(highest, (firstBid, secondBid) -> secondBid.getValue().compareTo(firstBid.getValue()));

		highest = highest.subList(0, highest.size() > 3 ? 3 : highest.size());
	}

	public List<Bid> getThreeHighest() {
		return highest;
	}

	public double getHighestBid() {
		return highestOfAll;
	}

	public double getLowestBid() {
		return lowestOfAll;
	}
}
