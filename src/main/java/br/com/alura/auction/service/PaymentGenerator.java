package br.com.alura.auction.service;

import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Clock;
import br.com.alura.auction.domain.Payment;
import br.com.alura.auction.domain.SystemClock;
import br.com.alura.auction.infra.dao.AuctionRepository;
import br.com.alura.auction.infra.dao.PaymentRepository;
import java.util.Calendar;
import java.util.List;

public class PaymentGenerator {

    private final PaymentRepository paymentRepository;
    private final AuctionRepository auctions;
    private final Appraiser appraiser;
    private Clock clock;

    PaymentGenerator(AuctionRepository auctionRepository, PaymentRepository paymentRepository,
                     Appraiser appraiser, Clock clock) {
        this.auctions = auctionRepository;
        this.paymentRepository = paymentRepository;
        this.appraiser = appraiser;
        this.clock = clock;
    }

    PaymentGenerator(AuctionRepository auctionRepository, PaymentRepository paymentRepository,
                     Appraiser appraiser) {
        this(auctionRepository, paymentRepository, appraiser, new SystemClock());
    }

    public void generate() {
        List<Auction> closedAuctions = auctions.closed();

        closedAuctions.forEach(auction -> {
            appraiser.evaluate(auction);
            Payment newPayment = new Payment(appraiser.getHighestBid(), firstUtilDay());
            paymentRepository.save(newPayment);
        });
    }

    private Calendar firstUtilDay() {
        Calendar date = clock.today();

        int weekDay = date.get(Calendar.DAY_OF_WEEK);

        if (weekDay == Calendar.SATURDAY) {
            date.add(Calendar.DAY_OF_MONTH, 2);
        } else if (weekDay == Calendar.SUNDAY) {
            date.add(Calendar.DAY_OF_MONTH, 1);
        }

        return date;
    }
}