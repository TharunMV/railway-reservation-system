package com.reservation;

public class Ticket {
	private final Passenger passenger;
	private final int trainNo;
	private final String berthPreference;

	public Ticket(Passenger passenger, int trainNo, String berthPreference) {
		this.passenger = passenger;
		this.trainNo = trainNo;
		this.berthPreference = berthPreference;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public String getBerthPreference() {
		return berthPreference;
	}

}
