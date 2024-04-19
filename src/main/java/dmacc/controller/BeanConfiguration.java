/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 16, 2024
 */

package dmacc.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dmacc.beans.Movie;
import dmacc.beans.CheckedOutMovies;
import dmacc.beans.Member;

@Configuration
public class BeanConfiguration {
	@Bean
	public Movie movie() {
		Movie bean = new Movie();
		return bean;
	}

	@Bean
	public Member member() {
		Member bean = new Member();
		return bean;
	}
	
	@Bean
	public CheckedOutMovies checkedOutMovies() {
		CheckedOutMovies checkedOutMovies = new CheckedOutMovies();
		return checkedOutMovies;
	}
}