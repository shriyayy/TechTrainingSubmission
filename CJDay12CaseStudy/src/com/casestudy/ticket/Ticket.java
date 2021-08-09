package com.casestudy.ticket;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.casestudy.passenger.Passenger;
import com.casestudy.train.Train;

public class Ticket {
	private int counter = 100;
	private String pnr;
	private LocalDate travelDate;
	private Train train;
	
	private TreeMap<Passenger, Double> passengers = new TreeMap<Passenger, Double>();
	
	public Ticket() {
		
	}
	
	public Ticket(LocalDate dateOfTravel, Train train) {
		super();
		this.travelDate = dateOfTravel;
		this.train = train;
	}

	public String generatePNR() {
		String pnr = null;
		StringBuilder sb = new StringBuilder();
		
		sb.append(train.getSource().charAt(0));
		sb.append(train.getDestination().charAt(0));
		sb.append("_");
		sb.append(String.valueOf(travelDate.getYear()));
		
		DecimalFormat dmFormat= new DecimalFormat("00");
		sb.append(dmFormat.format(Double.valueOf(travelDate.getMonthValue())));
		sb.append(dmFormat.format(Double.valueOf(travelDate.getDayOfMonth())));
		
		sb.append("_");
		sb.append(counter);
		pnr = sb.toString();
		counter = counter + 1;
		return pnr;
	}
	
	public double calcPassengerFare(Passenger p) {
		double fare = 0.0;
		if (p.getAge() <= 12){
				fare = (0.5)*(train.getTicketPrice());
			}	
		else if (p.getAge() >= 60) {
			fare = (0.6)*(train.getTicketPrice());
		}
		else if (p.getGender()=='F' && (((p.getAge())>12) && (p.getAge()<60))) {
			fare = (train.getTicketPrice())*(0.75);
		}
		else {
			fare = train.getTicketPrice();
		}
		System.out.println(fare);
		return (fare);
	}
	
	public void addPassenger(String name, int age, char gender) {
		Passenger p1 = new Passenger(name,age,gender);
		passengers.put(p1,calcPassengerFare(p1));
	}
	
	public double calculateTotalTicketPrice() {
		double total = 0;
		for (Entry<Passenger, Double> e : passengers.entrySet())
            total = total + e.getValue();
		return total;
	}

	public StringBuilder generateTicket() {
		StringBuilder s = new StringBuilder();
		s.append("PNR             : " + this.generatePNR());
		s.append("\nTrain no        : " + this.train.getTrainNo());
		s.append("\nTrain Name      : " + this.train.getTrainName());
		s.append("\nFrom            : " + this.train.getSource());
		s.append("\nTo              : " + this.train.getDestination());
		String dt = this.travelDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		s.append("\nTravel Date     : " + dt);
		s.append("\n\nPassengers:");
		s.append("\nName\t\t\tAge\t\t\tGender\t\t\tFare");
		for (Map.Entry<Passenger, Double> entry : passengers.entrySet()) {
			Passenger p = entry.getKey();
			s.append("\n" + p.getName()+"\t\t\t"+p.getAge()+"\t\t\t"+p.getGender()+"\t\t\t"+entry.getValue());
			
		}
		s.append("\nTotal Price : " + calculateTotalTicketPrice());
		
		System.out.println(s.toString());
		return s;
	}

	public void writeTicket() throws IOException {	
		String string = this.generateTicket().toString();
		File file = new File(this.generatePNR()+".txt");	
		FileOutputStream fos = new FileOutputStream(file, true);
		byte[] data = string.getBytes();
		fos.write(data);
		fos.close();	
	}
	
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public TreeMap<Passenger, Double> getPassengers() {
		return passengers;
	}

	public void setPassengers(TreeMap<Passenger, Double> passengers) {
		this.passengers = passengers;
	}
}
