package com.reservation;

import java.util.Objects;
import java.util.Random;

public class ReservationUtil {

    private static final BinarySearchTree<Train> trains = new BinarySearchTree<>();

    static {
        trains.insertNode(22416, new Train("Andhra Pradesh Express","New Delhi","Vishakapatnam"));
        trains.insertNode(12724, new Train("Andhra Pradesh Express","New Delhi","Hyderabad Decan"));
        trains.insertNode(12707, new Train("Andhra Pradesh Sampark Kranti","Tirupati","H Nizamuddin"));
        trains.insertNode(15909, new Train("Abadh Assam Express","New Tinsukia Junction","Darbhanga Junction"));
        trains.insertNode(18242, new Train("Abkp Durg Passenger E","Ambikapur","Durg"));
        trains.insertNode(11266, new Train("Abkp Jbp Express","Ambikapur","Jabalpur"));
        trains.insertNode(58702, new Train("Abkp Sdl Passenger","Ambikapur","Shahdol"));
        trains.insertNode(54703, new Train("Abs Ju Passengr","Abohar","Jodhpur Junction"));
        trains.insertNode(7509, new Train("Adb Qln Special","Adilabad","Kollam Junction"));
        trains.insertNode(9416, new Train("Adi Madgaon Special","Ahmedabad Junction","Madgaon"));
        trains.insertNode(56136, new Train("Mtp Uam Passenger","Metupalaiyam","Udagamandalam"));
        trains.insertNode(17025, new Train("Mugr Sc Express","Manuguru","Secunderabad Junction"));
        trains.insertNode(15016, new Train("Ypr Gorakpur Express","Yesvantpur Junction","Gorakhpur Junction"));
    }

    public static void bookTicket(Ticket ticket) {
        Train trainToBook = getDetails(trains, ticket.getTrainNo());
        if(Objects.isNull(trainToBook)) {
            System.out.println("Entered train no. is invalid.");
        } else if(trainToBook.availableSeats > 0) {
            int pnr = new Random().nextInt(1000000);
            trainToBook.addTicket(pnr, ticket);
            trainToBook.availableSeats--;
            System.out.println("Ticket booking successful. Please see the ticket details below - ");
            displayTrainAndTicketDetails(ticket.getTrainNo(), pnr);
        } else {
            System.out.println("No seats available!!");
        }
    }

    public static void cancelTicket(int trainNo, int pnr) {
        Train trainDetails = getDetails(trains, trainNo);
        if(Objects.isNull(trainDetails)) {
            System.out.println("Entered train no. is invalid.");
            return;
        }
        BinarySearchTree<Ticket> tickets = trainDetails.getTickets();
        if(Objects.isNull(tickets)) {
            System.out.println("Please enter valid PNR number.");
            return;
        }
        BinarySearchTree.Node<Ticket> deletedNode = tickets.deleteNode(tickets.root, pnr);
        if(tickets.root.key == pnr) {
            tickets.root = deletedNode;
        }
        trainDetails.availableSeats++;
        System.out.println("Ticket with PNR: " + pnr + " cancelled successfully");
    }

    public static void displayTrainAndTicketDetails(int trainNo, int pnr) {
        Train train = getDetails(trains, trainNo);
        if(Objects.isNull(train)) {
            System.out.println("Entered train no. is invalid.");
            return;
        }
        System.out.println("\nTrain No: " + trainNo +
                ", Train Name: " + train.getTrainName() +
                ", Source: " + train.getSource() +
                ", Destination: " + train.getDestination() +
                ", No. of tickets booked: " + (50 - train.availableSeats));

        Ticket ticket = getDetails(train.getTickets(), pnr);
        if(Objects.isNull(ticket)) {
            System.out.println("\nEntered PNR is invalid.");
            return;
        }
        System.out.println("PNR No: " + pnr +
                ", Travelling train: " + trainNo + "-" + train.getTrainName() +
                ", Berth Preference: " + ticket.getBerthPreference());
        System.out.println("Passenger Details - ");
        System.out.println("Name: " + ticket.getPassenger().getName() +
                ", Age: " + ticket.getPassenger().getAge() +
                ", Gender: " + ticket.getPassenger().getGender());
    }

    public static void displayAllTickets(int trainNo) {
        Train train = getDetails(trains, trainNo);
        if(Objects.isNull(train)) {
            System.out.println("Entered train no. is invalid.");
            return;
        } else if(train.getTickets().root == null){
            System.out.println("No tickets booked for this train!!");
            return;
        }
        trains.inOrderTraversal(train.getTickets().root);
    }

    public static void displayTicketDetails(int pnr, Ticket ticket) {
        if(Objects.isNull(ticket)) {
            System.out.println("\nEntered PNR is invalid.");
            return;
        }
        System.out.println("PNR No: " + pnr +
                ", Berth Preference: " + ticket.getBerthPreference());
        System.out.println("Passenger Details - ");
        System.out.println("Name: " + ticket.getPassenger().getName() +
                ", Age: " + ticket.getPassenger().getAge() +
                ", Gender: " + ticket.getPassenger().getGender() + "\n");
    }

    private static <T> T getDetails(BinarySearchTree<T> searchTree, int key) {
        BinarySearchTree.Node<T> node = searchTree.searchNode(searchTree.root, key);
        if(Objects.isNull(node)) {
            return null;
        }
        return node.element;
    }
}
