package br.com.alura.auction.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MOCK_AUCTION")
public class Auction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceAuction")
	@SequenceGenerator(name = "sequenceAuction", sequenceName = "SMOCK_AUCTION", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "AUCTION_DATE")
	private Calendar date;

	@OneToMany(mappedBy = "AUCTION")
	private List<Bid> bids;

	@Column(name = "CLOSED")
	private Boolean closed;

	public Auction(String description) {
		this(description, Calendar.getInstance());
	}

	public Auction(String description, Calendar date) {
		this.description = description;
		this.date = date;
		this.bids = new ArrayList<>();
		this.closed = false;
	}

	public void propose(Bid bid) {
		if (bids.isEmpty() || canBid(bid.getUser())) {
			bids.add(bid);
		}
	}

	private boolean canBid(User user) {
		return !lastBid().getUser().equals(user) && numberOfBids(user) < 5;
	}

	private int numberOfBids(User user) {
		return (int) bids.stream().filter(bid -> bid.getUser().equals(user)).count();
	}

	private Bid lastBid() {
		return bids.get(bids.size() - 1);
	}

	public String getDescription() {
		return description;
	}

	public List<Bid> getBids() {
		return Collections.unmodifiableList(bids);
	}

	public Calendar getDate() {
		return (Calendar) date.clone();
	}

	public void close() {
		this.closed = true;
	}

	public Boolean isClosed() {
		return closed;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auction other = (Auction) obj;
		if (description == null) {
			return other.description == null;
		} else return description.equals(other.description);
	}
}