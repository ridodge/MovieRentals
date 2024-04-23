/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 11, 2024
 */


package dmacc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dmacc.beans.CheckedOutMovies;
import dmacc.beans.Movie;

@Repository
public interface CheckedOutMoviesRepository extends JpaRepository<CheckedOutMovies, Long> {
	
	List<CheckedOutMovies> findByCheckoutDateLessThan(LocalDate date);
	List<CheckedOutMovies> findByMovie(Movie movie);
	
}
