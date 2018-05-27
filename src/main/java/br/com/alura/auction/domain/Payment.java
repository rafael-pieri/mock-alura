package br.com.alura.auction.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MOCK_PAYMENT")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencePayment")
	@SequenceGenerator(name = "sequencePayment", sequenceName = "SMOCK_PAYMENT", allocationSize = 1)
	@Column
	private Long id;

	@Column
	private Double value;

	@Column
	private Calendar date;

	public Payment(Double value, Calendar date) {
		this.value = value;
		this.date = date;
	}

	public Double getValue() {
		return value;
	}

	public Calendar getDate() {
		return date;
	}
}