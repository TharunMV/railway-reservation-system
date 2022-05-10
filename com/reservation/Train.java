package com.reservation;

public class Train {
    private final String trainName;
    private final String source;
    private final String destination;
    public int availableSeats = 50;
    private BinarySearchTree<Ticket> tickets;

    public Train(String trainName, String source, String destination) {
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.tickets = new BinarySearchTree<>();
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public BinarySearchTree<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(int pnr, Ticket ticket) {
        tickets.insertNode(pnr, ticket);
    }
}
