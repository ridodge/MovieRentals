/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 11, 2024
 */


package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dmacc.beans.CheckedOutMovies;

@Repository
public interface CheckedOutMoviesRepository extends JpaRepository<CheckedOutMovies, Long> { }
