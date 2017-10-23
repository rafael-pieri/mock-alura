package br.com.alura.auction.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Bid;

@Repository
@Transactional
public class AuctionDao implements AuctionRepository{

	@PersistenceContext
	private EntityManager manager;

	public void save(Auction auction) {

		manager.persist(auction);

		List<Bid> bids = auction.getBids();

		for (Bid bid : bids) {
			manager.persist(bid);
		}
	}

	public List<Auction> closed() {
		return putAsClosed(true);
	}

	public List<Auction> current() {
		return putAsClosed(false);
	}

	private List<Auction> putAsClosed(boolean status) {

		return manager.createQuery("select * from auction where closed = :status ", Auction.class)
				.setParameter("status", status).getResultList();
	}

	@Override
	public void update(Auction auction) {
		manager.merge(auction);
	}

}
