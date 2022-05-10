package com.reservation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean end = false;
		Scanner scanner = new Scanner(System.in);

		while(!end) {
			System.out.println();
			System.out.println("Press 1 to book a ticket");
			System.out.println("Press 2 to cancel a ticket");
			System.out.println("Press 3 to display train and ticket details");
			System.out.println("Press 4 to display all tickets for specified train no");
			System.out.println("Press 5 to exit");
			System.out.println("Please enter your choice");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Please enter your name");
					String name = scanner.next();
					System.out.println("Please enter your age");
					int age = scanner.nextInt();
					System.out.println("Gender");
					char gender = scanner.next().charAt(0);
					System.out.println("Please enter the train no you want to book");
					int trainNo = scanner.nextInt();
					System.out.println("Please enter your berth preference");
					String berthPreference = scanner.next();
					Ticket ticket = generateTicket(name, age, gender, trainNo, berthPreference);
					ReservationUtil.bookTicket(ticket);
					break;

				case 2:
					System.out.println("Enter the reserved train no");
					trainNo = scanner.nextInt();
					System.out.println("Enter confirmed PNR");
					int pnr = scanner.nextInt();
					ReservationUtil.cancelTicket(trainNo, pnr);
					break;

				case 3:
					System.out.println("Enter the train no you want to check");
					trainNo = scanner.nextInt();
					System.out.println("Enter confirmed PNR");
					pnr = scanner.nextInt();
					ReservationUtil.displayTrainAndTicketDetails(trainNo, pnr);
					break;

				case 4:
					System.out.println("Enter the train no");
					trainNo = scanner.nextInt();
					ReservationUtil.displayAllTickets(trainNo);
					break;

				case 5:
					end = true;
					break;

				default:
					System.out.println("Please enter a valid choice");

			}
		}
	}

	private static Ticket generateTicket(String name, int age, char gender, int trainNo, String berthPref) {
		if(name.isBlank() || age <= 0 || trainNo <= 0) {
			throw new IllegalArgumentException("Please enter valid details.");
		}
		if(berthPref.isBlank())
			berthPref = "NO_PREF";
		return new Ticket(new Passenger(name, age, gender), trainNo, berthPref);
	}
}
