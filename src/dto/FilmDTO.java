package dto;

import java.sql.Timestamp;

public class FilmDTO {
	
	protected int film_id;
	protected String title;
	protected String description;
	protected int release_year;
	protected int language_id;
	protected int original_language_id;
	protected int rental_duration;
	protected double rental_rate;
	protected int length;
	protected double replacement_cost;
	protected String rating;
	protected String special_features;
	protected Timestamp last_update;
	
	
	public FilmDTO(int film_id, String title, String description, int release_year, int language_id, int original_language_id,
			int rental_duration, double rental_rate, int length, double replacement_cost, String rating,
			String special_features, Timestamp last_update) {
		super();
		this.film_id = film_id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language_id = language_id;
		this.original_language_id = original_language_id;
		this.rental_duration = rental_duration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacement_cost = replacement_cost;
		this.rating = rating;
		this.special_features = special_features;
		this.last_update = last_update;
	}


	public int getFilm_id() {
		return film_id;
	}


	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getRelease_year() {
		return release_year;
	}


	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}


	public int getLanguage_id() {
		return language_id;
	}


	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}


	public int getOriginal_language_id() {
		return original_language_id;
	}


	public void setOriginal_language_id(int original_language_id) {
		this.original_language_id = original_language_id;
	}


	public int getRental_duration() {
		return rental_duration;
	}


	public void setRental_duration(int rental_duration) {
		this.rental_duration = rental_duration;
	}


	public double getRental_rate() {
		return rental_rate;
	}


	public void setRental_rate(double rental_rate) {
		this.rental_rate = rental_rate;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public double getReplacement_cost() {
		return replacement_cost;
	}


	public void setReplacement_cost(double replacement_cost) {
		this.replacement_cost = replacement_cost;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getSpecial_features() {
		return special_features;
	}


	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
	}


	public Timestamp getLast_update() {
		return last_update;
	}


	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	

}
