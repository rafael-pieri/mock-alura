package br.com.alura.auction.service;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.alura.auction.domain.Auction;
import br.com.alura.auction.infra.dao.AuctionRepository;

public class AuctionCloser {

	private final Logger logger = Logger.getLogger(AuctionCloser.class);
	
	private int total = 0;
    private final AuctionRepository auctionRepository;
    private final MailMan mailMan;

    public AuctionCloser(AuctionRepository auctionRepository, MailMan mailMan) {
        this.auctionRepository = auctionRepository;
        this.mailMan = mailMan;
    }
    
    public void close() {
        List<Auction> allCurrentAuctions = auctionRepository.current();

        for (Auction auction : allCurrentAuctions) {
            try {
                if (startedInTheLastWeek(auction)) {
                    auction.close();
                    total++;
                    auctionRepository.update(auction);
                    mailMan.send(auction);
                }
            }
            catch(Exception exception) {
            	logger.error("Error to close auction " + auction.getDescription() + ". Error [%s]", exception);
            }
        }
    }

    private boolean startedInTheLastWeek(Auction auction) {
        return daysBetween(auction.getDate(), Calendar.getInstance()) >= 7;
    }

    private int daysBetween(Calendar firstDate, Calendar lastDate) {
        Calendar date = (Calendar) firstDate.clone();
        
        int daysInInterval = 0;
        
        while (date.before(lastDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysInInterval++;
        }
        
        return daysInInterval;
    }

    public int getTotalClosed() {
        return total;
    }
}
