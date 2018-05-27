package br.com.alura.auction.repository;

import br.com.alura.auction.domain.Payment;

public interface PaymentRepository {

    void save(Payment payment);
}