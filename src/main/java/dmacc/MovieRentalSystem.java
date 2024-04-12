/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 11, 2024
 */

package dmacc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import dmacc.beans.Member;
import dmacc.beans.Movie;
import dmacc.repository.CheckedOutMoviesRepository;

@SpringBootApplication
public class MovieRentalSystem {
	public static void main(String[] args) {
		SpringApplication.run(MovieRentalSystem.class, args);
	}

	@Autowired
	CheckedOutMoviesRepository repo;
}