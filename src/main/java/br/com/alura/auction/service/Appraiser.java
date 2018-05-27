package br.com.alura.auction.service;

import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Bid;
import br.com.alura.auction.exception.AuctionException;
import java.util.ArrayList;
import java.util.List;

public class Appraiser {

    private double highestOfAll = Double.NEGATIVE_INFINITY;
    private double lowestOfAll = Double.POSITIVE_INFINITY;
    private List<Bid> highest;

    public void evaluate(Auction auction) {
        if (auction.getBids().isEmpty()) {
            throw new AuctionException("It's not possible to evaluate an auction without bids.");
        }

        auction.getBids().forEach((Bid bid) -> {
            if (bid.getValue() > highestOfAll) {
                highestOfAll = bid.getValue();
            }
            if (bid.getValue() < lowestOfAll) {
                lowestOfAll = bid.getValue();
            }
        });

        threeHighest(auction);
    }

    private void threeHighest(Auction auction) {
        highest = new ArrayList<>(auction.getBids());

        highest.sort((firstBid, secondBid) -> secondBid.getValue().compareTo(firstBid.getValue()));

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