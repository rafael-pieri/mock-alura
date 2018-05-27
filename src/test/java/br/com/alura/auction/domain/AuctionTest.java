package br.com.alura.auction.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AuctionTest {

    @Test
    public void shouldReceiveABid() {
        Auction auction = new Auction("Macbook Pro 15");
        assertEquals(0, auction.getBids().size());

        auction.propose(new Bid(new User("Steve Jobs"), 2000d));

        assertEquals(1, auction.getBids().size());
        assertEquals(2000, auction.getBids().get(0).getValue(), 0.00001);
    }

    @Test
    public void shouldReceiveSeveralBids() {
        Auction auction = new Auction("Macbook Pro 15");
        auction.propose(new Bid(new User("Steve Jobs"), 2000d));
        auction.propose(new Bid(new User("Steve Wozniak"), 3000d));

        assertEquals(2, auction.getBids().size());
        assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.00001);
        assertEquals(3000.0, auction.getBids().get(1).getValue(), 0.00001);
    }

    @Test
    public void shouldNotAcceptTwoBidsInARowFromTheSameUser() {
        User steveJobs = new User("Steve Jobs");
        Auction auction = new Auction("Macbook Pro 15");
        auction.propose(new Bid(steveJobs, 2000d));
        auction.propose(new Bid(steveJobs, 3000d));

        assertEquals(1, auction.getBids().size());
        assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.00001);
    }

    @Test
    public void shouldNotAcceptMoreThanFiveBidsFromTheSameUser() {
        User steveJobs = new User("Steve Jobs");
        User billGates = new User("Bill Gates");

        Auction auction = new Auction("Macbook Pro 15");
        auction.propose(new Bid(steveJobs, 2000d));
        auction.propose(new Bid(billGates, 3000d));
        auction.propose(new Bid(steveJobs, 4000d));
        auction.propose(new Bid(billGates, 5000d));
        auction.propose(new Bid(steveJobs, 6000d));
        auction.propose(new Bid(billGates, 7000d));
        auction.propose(new Bid(steveJobs, 8000d));
        auction.propose(new Bid(billGates, 9000d));
        auction.propose(new Bid(steveJobs, 10000d));
        auction.propose(new Bid(billGates, 11000d));
        auction.propose(new Bid(steveJobs, 12000d));

        assertEquals(10, auction.getBids().size());
        assertEquals(11000.0, auction.getBids().get(auction.getBids().size() - 1).getValue(), 0.00001);
    }
}