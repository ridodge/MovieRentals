/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 11, 2024
 */

package dmacc.beans;

import org.springframework.beans.factory.annotation.Autowired;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * 
 */
@Entity
public class Movie {
	@Id
	private Long uniqueCheckoutId;// each movie gets a different id
	private String movieName;
	private int movieId; // movies of the same title share this ID

	public Long getUniqueCheckoutId() {
		return uniqueCheckoutId;
	}

	public void setUniqueCheckoutId(Long uniqueCheckoutId) {
		this.uniqueCheckoutId = uniqueCheckoutId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public Movie(Long uniqueCheckoutId, String movieName, int movieId) {
		this.uniqueCheckoutId = uniqueCheckoutId;
		this.movieName = movieName;
		this.movieId = movieId;
	}

	public Movie() {
	}

	@Override
	public String toString() {
		return "Movie [uniqueCheckoutId=" + uniqueCheckoutId + ", movieName=" + movieName + ", movieId=" + movieId
				+ "]";
	}

}
