package br.com.alura.auction.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.alura.auction.builder.AuctionCreator;
import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.domain.Clock;
import br.com.alura.auction.domain.Payment;
import br.com.alura.auction.domain.User;
import br.com.alura.auction.repository.AuctionRepository;
import br.com.alura.auction.repository.PaymentRepository;
import java.util.Calendar;
import java.util.Collections;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class PaymentGeneratorTest {

    @Test
    public void shouldGeneratePaymentForAClosedAuction() {
        AuctionRepository auctionRepository = mock(AuctionRepository.class);
        PaymentRepository paymentRepository = mock(PaymentRepository.class);

        Auction auction = new AuctionCreator().to("Playstation")
            .bid(new User("John"), 2000.0)
            .bid(new User("Mary"), 2500.0)
            .build();

        when(auctionRepository.closed()).thenReturn(Collections.singletonList(auction));

        PaymentGenerator paymentGenerator = new PaymentGenerator(auctionRepository, paymentRepository, new Appraiser());
        paymentGenerator.generate();

        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        verify(paymentRepository).save(argumentCaptor.capture());

        Payment generatedPayment = argumentCaptor.getValue();

        assertEquals(2500.0, generatedPayment.getValue(), 0.00001);
    }

    @Test
    public void shouldPutPaymentsOnSaturdayToTheNextUsefulDay() {
        AuctionRepository auctionRepository = mock(AuctionRepository.class);
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        Clock clock = mock(Clock.class);

        Calendar saturday = Calendar.getInstance();
        saturday.set(2012, Calendar.APRIL, 7);

        when(clock.today()).thenReturn(saturday);

        Auction auction = new AuctionCreator().to("Playstation")
            .bid(new User("John"), 2000.0)
            .bid(new User("Mary"), 2500.0)
            .build();

        when(auctionRepository.closed()).thenReturn(Collections.singletonList(auction));

        PaymentGenerator paymentGenerator =
            new PaymentGenerator(auctionRepository, paymentRepository, new Appraiser(), clock);
        paymentGenerator.generate();

        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        verify(paymentRepository).save(argumentCaptor.capture());

        Payment generatedPayment = argumentCaptor.getValue();

        assertEquals(Calendar.MONDAY, generatedPayment.getDate().get(Calendar.DAY_OF_WEEK));
        assertEquals(9, generatedPayment.getDate().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void shouldPutPaymentsOnSundayToTheNextUsefulDay() {
        AuctionRepository auctionRepository = mock(AuctionRepository.class);
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        Clock clock = mock(Clock.class);

        Calendar saturday = Calendar.getInstance();
        saturday.set(2012, Calendar.APRIL, 8);

        when(clock.today()).thenReturn(saturday);

        Auction auction = new AuctionCreator().to("Playstation")
            .bid(new User("John"), 2000.0)
            .bid(new User("Mary"), 2500.0)
            .build();

        when(auctionRepository.closed()).thenReturn(Collections.singletonList(auction));

        PaymentGenerator paymentGenerator =
            new PaymentGenerator(auctionRepository, paymentRepository, new Appraiser(), clock);
        paymentGenerator.generate();

        ArgumentCaptor<Payment> argumentCaptor = ArgumentCaptor.forClass(Payment.class);

        verify(paymentRepository).save(argumentCaptor.capture());

        Payment generatedPayment = argumentCaptor.getValue();

        assertEquals(Calendar.MONDAY, generatedPayment.getDate().get(Calendar.DAY_OF_WEEK));
        assertEquals(9, generatedPayment.getDate().get(Calendar.DAY_OF_MONTH));
    }
}