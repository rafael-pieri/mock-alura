package br.com.alura.auction.builder;

import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Bid;
import br.com.alura.auction.domain.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AuctionCreator {

    private String description;
    private Calendar date;
    private List<Bid> bids;

    public AuctionCreator() {
        this.date = Calendar.getInstance();
        bids = new ArrayList<>();
    }

    public AuctionCreator to(String description) {
        this.description = description;
        return this;
    }

    public AuctionCreator onDate(Calendar date) {
        this.date = date;
        return this;
    }

    public AuctionCreator bid(User user, Double value) {
        bids.add(new Bid(user, value));
        return this;
    }

    public Auction build() {
        Auction auction = new Auction(description, date);

        bids.forEach(auction::propose);

        return auction;
    }
}