package br.com.alura.auction.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import br.com.alura.auction.builder.AuctionCreator;
import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Bid;
import br.com.alura.auction.domain.User;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AppraiserTest {

	private Appraiser appraiser;
	private User mary;
	private User peter;
	private User john;

	@Before
	public void createAppraiser() {
		this.appraiser = new Appraiser();
		this.john = new User("John");
		this.peter = new User("Peter");
		this.mary = new User("Mary");
	}

	@Test(expected=RuntimeException.class)
	public void shouldNotEvaluateAuctionsWithoutBid() {
		Auction auction = new AuctionCreator().to("New Playstation 3")
				                              .build();

		appraiser.evaluate(auction);
	}

    @Test
    public void shouldUnderstandBidsInDescendingOrder() {
        Auction auction = new Auction("New Playstation 3");

        auction.propose(new Bid(john, 250.0));
        auction.propose(new Bid(peter, 300.0));
        auction.propose(new Bid(mary, 400.0));

        appraiser.evaluate(auction);

        assertThat(appraiser.getHighestBid(), equalTo(400.0));
        assertThat(appraiser.getLowestBid(), equalTo(250.0));
    }

    @Test
    public void shouldUnderstandAuctionWithOnlyBid() {
    	User john = new User("John");
        Auction auction = new Auction("New Playstation 3");

        auction.propose(new Bid(john, 1000.0));

        appraiser.evaluate(auction);

        assertEquals(1000.0, appraiser.getHighestBid(), 0.00001);
        assertEquals(1000.0, appraiser.getLowestBid(), 0.00001);
    }

    @Test
    public void shouldFindTheThreeHighestBids() {
        Auction auction = new AuctionCreator().to("New Playstation 3")
							        		  .bid(john, 100.0)
							        		  .bid(mary, 200.0)
							        		  .bid(john, 300.0)
							        		  .bid(mary, 400.0)
							        		  .build();

        appraiser.evaluate(auction);

        List<Bid> highest = appraiser.getThreeHighest();
        assertEquals(3, highest.size());

        assertThat(highest, hasItems(new Bid(mary, 400d),
        							 new Bid(john, 300d),
        							 new Bid(mary, 200d)
        ));
    }
}