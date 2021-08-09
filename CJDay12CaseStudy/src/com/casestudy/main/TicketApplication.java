package com.casestudy.main;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

import com.casestudy.passenger.Passenger;
import com.casestudy.ticket.Ticket;
import com.casestudy.train.Train;
import com.casestudy.traindao.TrainDAO;
public class TicketApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException {
		
		Train newTrain = new Train();
		TrainDAO t = new TrainDAO();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the train number");
		int train_number = scanner.nextInt();
		
		if (t.findTrain(train_number) == null) {
			System.out.println("Train with given number does not exist");
		}
		else {
			newTrain = t.findTrain(train_number);
			System.out.println("Enter Travel Date");
			String tDate = scanner.next();
			
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dateOfTravel = LocalDate.parse(tDate, formatter1);
			
			LocalDate currentDate = LocalDate.now();  

		    //System.out.println(formatter.format(currentDate) + " " + formatter.format(dateOfTravel)); 
		    
			if (dateOfTravel.compareTo(currentDate) < 0) {
				System.out.println("Travel date is before current date");
			}
			else {
				System.out.println("Enter Number of Passengers");
				int num = scanner.nextInt();
				if (num == 0) {
					System.out.println();
				}
				else {
					Ticket travelTicket = new Ticket(dateOfTravel,t.findTrain(train_number));
					for (int i=0; i < num; i++) {
						
						System.out.println("Enter Passenger Name");
						String pname = scanner.next();
						System.out.println("Enter Age");
						int page = scanner.nextInt();
						System.out.println("Enter Gender(M/F)");
						char pgender = scanner.next().charAt(0);
						Passenger p = new Passenger();
						p.setName(pname);
						p.setAge(page);
						p.setGender(pgender);
						travelTicket.addPassenger(pname, page, pgender);
					}
					System.out.print("Ticket Booked with PNR : " + travelTicket.generatePNR());
					travelTicket.writeTicket();
				}				
			}			
		}
		scanner.close();
	}
}
