package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt {
	private ArrayList<Customers> customers;
	private ArrayList<Media> media;

	public MediaRentalManager() {
		customers = new ArrayList<Customers>();
		media = new ArrayList<Media>();
	}

	public void addCustomer(String name, String address, String plan) {
		Customers customers = new Customers(name, address, plan);
		this.customers.add(customers);
	}

	public void addMovie(String title, int copiesAvailable, String rating) {
		Movie movie = new Movie(title, copiesAvailable, rating);
		this.media.add(movie);
	}

	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {
		Album album = new Album(title, copiesAvailable, artist, songs);
		this.media.add(album);
	}

	public void setLimitedPlanLimit(int value) {
		Customers.setMediaLimit(value);
	}

	public String getAllCustomersInfo() {
		String customersInfo = "***** Customers' Information *****\n";
		Collections.sort(customers);
		for (Customers currentCustomers : customers) {
			customersInfo += currentCustomers.toString() + "\n";
		}
		return customersInfo;
	}

	public String getAllMediaInfo() {
		String mediaInfo = "***** Media Information *****\n";
		Collections.sort(media);
		for (Media currentMedia : media) {
			mediaInfo += currentMedia.toString() + "\n";
		}
		return mediaInfo;
	}

	public boolean addToQueue(String customerName, String mediaTitle) {
		for (Customers currentMedia : customers) {
			if (currentMedia.getName().equals(customerName)) {
				if (currentMedia.getQueue().contains(mediaTitle)) {
					return false;
				} else {
					currentMedia.addQueue(mediaTitle);
					return true;
				}
			}
		}
		return false;
	}

	public boolean removeFromQueue(String customerName, String mediaTitle) {
		for (Customers currentCustomers : customers) {
			if (currentCustomers.getName().equals(customerName)) {
				if (!currentCustomers.getQueue().contains(mediaTitle)) {
					return false;
				} else {
					currentCustomers.removeQueue(mediaTitle);
					return true;
				}
			}
		}
		return false;
	}

	public String processRequests() {
		ArrayList<String> rentedMedia = new ArrayList<String>();
		String sendingMessage = "";
		Collections.sort(customers);
		for (Customers currentCustomers : customers) {
			if (currentCustomers.getPlan().equals("UNLIMITED")) {
				for (String media : currentCustomers.getQueue()) {
					if (copiesAvaiable(media)) {
						rentedMedia.add(media);
						rentingMedia(currentCustomers, media);
						sendingMessage += "Sending " + media + " to " + currentCustomers.getName() + "\n";
					}
				}
			} else if (currentCustomers.getPlan().equals("LIMITED")
					&& currentCustomers.getRented().size() <= currentCustomers.getMediaLimit()) {
				for (String media : currentCustomers.getQueue()) {
					if (currentCustomers.getRented().size() < currentCustomers.getMediaLimit()
							&& copiesAvaiable(media)) {
						rentedMedia.add(media);
						rentingMedia(currentCustomers, media);
						sendingMessage += "Sending " + media + " to " + currentCustomers.getName() + "\n";
					}
				}
			}
			for (String media : rentedMedia) {
				currentCustomers.removeQueue(media);
			}
			rentedMedia.clear();
		}
		return sendingMessage;
	}

	public boolean returnMedia(String customerName, String mediaTitle) {
		for (Customers currentCustomers : customers) {
			if (currentCustomers.getName().equals(customerName)) {
				currentCustomers.removeRented(mediaTitle);
				Customers.setMediaLimit(currentCustomers.getMediaLimit() + 1);
				for (Media currentMedia : media) {
					if (currentMedia.getTitle().equals(mediaTitle)) {
						currentMedia.increaseCopies();
					}
				}
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		Collections.sort(media);
		ArrayList<String> answer = new ArrayList<String>();
		if (title == null && rating == null && artist == null && songs == null) {
			for (Media currentMedia : media) {
				answer.add(currentMedia.getTitle());
			}
			return answer;
		}

		for (Media currentMedia : media) {
			if (title != null) {
				if (currentMedia.getTitle().equals(title)) {
					answer.add(currentMedia.getTitle());
				}
			}
			if (currentMedia instanceof Movie) {
				if (rating != null) {
					if (((Movie) currentMedia).getRating().equals(rating)) {
						answer.add(currentMedia.getTitle());
					}
				}
			}
			if (currentMedia instanceof Album) {
				if (artist != null) {
					if (((Album) currentMedia).getArtist().equals(artist)) {
						answer.add(currentMedia.getTitle());
					}
				}
				if (songs != null) {
					if (((Album) currentMedia).getSongs().contains(songs)) {
						answer.add(currentMedia.getTitle());
					}
				}
			}
		}
		return answer;
	}
	
	private void rentingMedia(Customers customers, String mediaTitle) {
		customers.addRented(mediaTitle);
		for (Media currentMedia : media) {
			if (currentMedia.getTitle().equals(mediaTitle)) {
				currentMedia.removeCopies();
			}
		}
	}
	
	private boolean copiesAvaiable(String mediaTitle) {
		for (Media currentMedia : media) {
			if (currentMedia.getTitle().equals(mediaTitle)) {
				return (currentMedia.getCopiesAvailable() > 0) ? true : false;
			}
		}
		return false;
	}
}