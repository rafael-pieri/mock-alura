package br.com.alura.auction.service;

import br.com.alura.auction.domain.Auction;

public interface EmailSender {
    void send(Auction auction);
}
