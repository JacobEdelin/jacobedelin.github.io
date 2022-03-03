package mediaRentalManager;

import java.util.ArrayList;

public class Customers implements Comparable<Customers> {
	private String name;
	private String address;
	private String plan;
	private ArrayList<String> queue;
	private ArrayList<String> rentedMovies;
	private static int maxLimitMedia;

	public Customers(String name, String address, String plan) {
		this.name = name;
		this.address = address;
		this.plan = plan;
		this.queue = new ArrayList<String>();
		this.rentedMovies = new ArrayList<String>();
		if (this.plan.equals("LIMITED")) {
			maxLimitMedia = 2;
		}
	}

	public void addQueue(String mediaTitle) {
		queue.add(mediaTitle);
	}

	public void removeQueue(String mediaTitle) {
		queue.remove(mediaTitle);
	}

	public ArrayList<String> getQueue() {
		return queue;
	}
	
	public void addRented(String mediaTitle) {
		rentedMovies.add(mediaTitle);
	}

	public void removeRented(String mediaTitle) {
		rentedMovies.remove(mediaTitle);
	}

	public ArrayList<String> getRented() {
		return rentedMovies;
	}

	public String getName() {
		return name;
	}

	public String getPlan() {
		return plan;
	}

	public int getMediaLimit() {
		return maxLimitMedia;
	}

	public static void setMediaLimit(int limitAmount) {
		maxLimitMedia = limitAmount;
	}

	public String toString() {
		return "Name: " + name + ", Address: " + address + ", Plan: " + plan + "\nRented: " 
				+ rentedMovies + "\nQueue: " + queue;
	}

	public int compareTo(Customers customers) {
		return this.name.compareTo(customers.name);
	}
}