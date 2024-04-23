/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 11, 2024
 */

package dmacc.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import dmacc.beans.Member;
import dmacc.beans.Movie;


/**
 * 
 */
@Entity
public class CheckedOutMovies {
	@Id
	@GeneratedValue
	private Long ID;
	@OneToOne
	private Member member;
	@OneToOne
	private Movie movie;
	private LocalDate checkoutDate;
	private LocalDate checkinDate;
	

	public CheckedOutMovies(Member member, Movie movie, String checkoutDate) {
		this.member = member;
		this.movie = movie;
		this.checkoutDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ISO_DATE);
		this.checkinDate = this.checkoutDate.plusWeeks(2);
	}


	public LocalDate getCheckinDate() {
		return checkinDate;
	}


	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}


	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}


	public void setCheckoutDate(LocalDate date) {
		this.checkoutDate = date;
	}


	public CheckedOutMovies() {
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	@Override
	public String toString() {
		return "CheckedOutMovies [ID=" + ID + ", member=" + member + ", movie=" + movie + ", checkoutDate="
				+ checkoutDate + "]";
	}


	
}
