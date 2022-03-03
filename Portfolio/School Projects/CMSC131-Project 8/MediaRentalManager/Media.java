package mediaRentalManager;

public class Media implements Comparable<Media> {
	private String title;
	private int copiesAvailable;

	public Media(String title, int copiesAvailable) {
		this.title = title;
		this.copiesAvailable = copiesAvailable;
	}

	public String getTitle() {
		return title;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void increaseCopies() {
		copiesAvailable++;
	}

	public void removeCopies() {
		copiesAvailable--;
	}

	public int compareTo(Media media) {
		return this.title.compareTo(media.title);
	}

	public String toString() {
		return "Title: " + getTitle() + ", Copies Available: " + getCopiesAvailable();
	}
}