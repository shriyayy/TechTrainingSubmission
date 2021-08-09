package com.casestudy.additional;

import java.util.Scanner;

public class AdditionalFeatures {
	
	public static void BookTicket() {
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i=0;
		System.out.println("User Menu:");
		System.out.println("Press 1 - To book the ticket ");
		System.out.println("Press 2 - For enquiry");
		System.out.println("Press 3 - To view the number of tickets booked");
		System.out.println("Press 4 - To print all the tickets");
		System.out.println("Press 5 - To Exit");
		i = sc.nextInt();
		
		switch(i) {
		case 1:
			BookTicket();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:	
			break;
		case 5:
			break;
		}
		
		
	}
}
