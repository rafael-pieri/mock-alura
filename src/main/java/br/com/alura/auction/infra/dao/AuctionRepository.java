package br.com.alura.auction.infra.dao;

import br.com.alura.auction.domain.Auction;
import java.util.List;

public interface AuctionRepository {

    void save(Auction auction);

    List<Auction> closed();

    List<Auction> current();

    default void update(Auction auction) {
    }
}