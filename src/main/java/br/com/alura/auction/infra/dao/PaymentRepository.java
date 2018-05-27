package br.com.alura.auction.infra.dao;

import br.com.alura.auction.domain.Payment;

public interface PaymentRepository {

    void save(Payment payment);
}