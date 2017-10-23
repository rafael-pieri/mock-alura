package br.com.alura.auction.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Bid;
import br.com.alura.auction.domain.User;

public class AuctionTest {

	@Test
	public void shouldReceiveABid() {
		Auction auction = new Auction("Macbook Pro 15");
		assertEquals(0, auction.getBids().size());
		
		auction.propose(new Bid(new User("Steve Jobs"), Double.valueOf(2000)));
		
		assertEquals(1, auction.getBids().size());
		assertEquals(2000, auction.getBids().get(0).getValue(), 0.00001);
	}
	
	@Test
	public void shouldReceiveSeveralBids() {
		Auction auction = new Auction("Macbook Pro 15");
		auction.propose(new Bid(new User("Steve Jobs"), Double.valueOf(2000)));
		auction.propose(new Bid(new User("Steve Wozniak"), Double.valueOf(3000)));
		
		assertEquals(2, auction.getBids().size());
		assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.00001);
		assertEquals(3000.0, auction.getBids().get(1).getValue(), 0.00001);
	}
	
	@Test
	public void shouldNotAcceptTwoBidsInARowFromTheSameUser() {
		User steveJobs = new User("Steve Jobs");
		Auction auction = new Auction("Macbook Pro 15");
		auction.propose(new Bid(steveJobs, Double.valueOf(2000)));
		auction.propose(new Bid(steveJobs, Double.valueOf(3000)));
		
		assertEquals(1, auction.getBids().size());
		assertEquals(2000.0, auction.getBids().get(0).getValue(), 0.00001);
	}
	
	@Test
	public void shouldNotAcceptMoreThanFiveBidsFromTheSameUser() {
		User steveJobs = new User("Steve Jobs");
		User billGates = new User("Bill Gates");

		Auction auction = new Auction("Macbook Pro 15");
		auction.propose(new Bid(steveJobs, Double.valueOf(2000)));
		auction.propose(new Bid(billGates, Double.valueOf(3000)));
		auction.propose(new Bid(steveJobs, Double.valueOf(4000)));
		auction.propose(new Bid(billGates, Double.valueOf(5000)));
		auction.propose(new Bid(steveJobs, Double.valueOf(6000)));
		auction.propose(new Bid(billGates, Double.valueOf(7000)));
		auction.propose(new Bid(steveJobs, Double.valueOf(8000)));
		auction.propose(new Bid(billGates, Double.valueOf(9000)));
		auction.propose(new Bid(steveJobs, Double.valueOf(10000)));
		auction.propose(new Bid(billGates, Double.valueOf(11000)));
		auction.propose(new Bid(steveJobs, Double.valueOf(12000)));
		
		assertEquals(10, auction.getBids().size());
		assertEquals(11000.0, auction.getBids().get(auction.getBids().size()-1).getValue(), 0.00001);
	}	
}
