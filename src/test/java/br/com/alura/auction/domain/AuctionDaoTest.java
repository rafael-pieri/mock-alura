package br.com.alura.auction.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.alura.auction.config.JPAConfiguration;
import br.com.alura.auction.infra.dao.AuctionDao;
import br.com.alura.auction.infra.dao.AuctionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, AuctionDao.class})
public class AuctionDaoTest {
	
	@Autowired
	private AuctionRepository auctionDao;
	
	@Test
	public void saveAuction() {
		Auction auction = new Auction("BMW 320");
		
		auctionDao.save(auction);
	}

}
