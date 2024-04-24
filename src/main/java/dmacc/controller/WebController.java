/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 16, 2024
 */

package dmacc.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dmacc.beans.CheckedOutMovies;
import dmacc.beans.Movie;
import dmacc.beans.MovieInventory;
import dmacc.beans.Member;

import dmacc.repository.CheckedOutMoviesRepository;
import dmacc.repository.MovieRepository;
import dmacc.repository.MemberRepository;
import dmacc.repository.MovieInventoryRepository;

@Controller
public class WebController {
	@Autowired
	CheckedOutMoviesRepository checkedOutRepo;
	@Autowired
	MovieRepository movieRepo;
	@Autowired
	MemberRepository memberRepo;
	@Autowired
	MovieInventoryRepository movieInventoryRepo;
	
	Sort sortByDate = Sort.by(Sort.Direction.DESC, "checkoutDate");

	@GetMapping({ "/viewAllMovies" })
	public String viewAllMovies(Model model) {
		try{
			if (movieRepo.findAll().isEmpty() ||  movieRepo.findAll() == null) {
		
			return addNewMovie(model);
		}
		}
		catch(NullPointerException npe){
			return addNewMovie(model);
		}
		
		model.addAttribute("movies", movieRepo.findAll());
		return "movie_results";
	}

	@GetMapping("/addNewMovie")
	public String addNewMovie(Model model) {
		Movie m = new Movie();
		model.addAttribute("newMovie", m);

		return "movie_input";
	}

	@PostMapping("/addNewMovie")
	public String addNewMovie(@ModelAttribute Movie m, Model model) {
		movieRepo.save(m);
		if (movieInventoryRepo.findById(m.getMovieId()).orElse(null) != null) {
			addInventory(m.getMovieId());
		} else {
			MovieInventory mi = new MovieInventory(m.getMovieId(), 1, 1, m.getMovieName());
			movieInventoryRepo.save(mi);
		}
		return viewAllMovies(model);
	}

	@PostMapping("/updateMovie/{id}")
	public String reviseMovie(Movie m, Model model) {
		movieRepo.save(m);
		return "movie_results";
	}
	
	@GetMapping("/editMovie/{id}")
	public String showUpdateStringMovie(@PathVariable("id") long id, Model model) {
		Movie m = movieRepo.findById(id).orElse(null);
		model.addAttribute("newMovie", m);
		return viewAllMovies(model);
	}
	
	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable("id") long id, Model model) {
		Movie m = movieRepo.findById(id).orElse(null);
		movieRepo.delete(m);
		MovieInventory inventory = movieInventoryRepo.findById(m.getMovieId()).orElse(null);
		if (inventory != null) {
			subtractInventory(m.getMovieId());
			inventory = movieInventoryRepo.findById(m.getMovieId()).orElse(null);
			if (inventory.getInventory() == 0) {
				movieInventoryRepo.delete(inventory);
			}
		}
		return viewAllMovies(model);
	}
	
	@GetMapping({ "/viewAllMembers" })
	public String viewAllMembers(Model model) {
		try{
			if (memberRepo.findAll().isEmpty() ||  memberRepo.findAll() == null) {
		
			return addNewMember(model);
		}
		}
		catch(NullPointerException npe){
			return addNewMember(model);
		}
		model.addAttribute("members", memberRepo.findAll());
		return "member_results";
	}

	@GetMapping("/addNewMember")
	public String addNewMember(Model model) {
		Member m = new Member();
		model.addAttribute("newMember", m);
		return "member_input";
	}

	@PostMapping("/addNewMember")
	public String addNewMember(@ModelAttribute Member m, Model model) {
		memberRepo.save(m);
		return viewAllMembers(model);
	}

	@PostMapping("/updateMember/{id}")
	public String reviseMember(Member m, Model model) {
		memberRepo.save(m);
		return "member_results";
	}
	
	@GetMapping("/editMember/{id}")
	public String showUpdateStringMembers(@PathVariable("id") long id, Model model) {
		Member m = memberRepo.findById(id).orElse(null);
		model.addAttribute("newMember", m);
		return "member_input";
	}
	
	@GetMapping("/deleteMember/{id}")
	public String deleteMember(@PathVariable("id") long id, Model model) {
		Member m = memberRepo.findById(id).orElse(null);
		memberRepo.delete(m);
		return "member_results";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		if (movieRepo.findAll() != null && memberRepo.findAll() != null) {
			model.addAttribute("members", memberRepo.findAll());
			model.addAttribute("movies", movieRepo.findAll());
		}
		CheckedOutMovies chm = new CheckedOutMovies();
		model.addAttribute("checkedOutMovie", chm);
		return "checkout";
	}
	
	@GetMapping("/checkin")
	public String checkout() {
		return "checkin";
	}
	
	@PostMapping("/checkout")
	public String checkout(@RequestParam("movies") String movieID, @RequestParam("members") String memberID, @RequestParam("checkoutDate") String inputDate, Model model) {
		Movie movie = movieRepo.findById(Long.parseLong(movieID)).get();
		Member member = memberRepo.findById(Long.parseLong(memberID)).get();
		CheckedOutMovies chm = new CheckedOutMovies(member, movie, inputDate);
		checkedOutRepo.save(chm);
		if (movieInventoryRepo.findById(movie.getMovieId()).orElse(null) != null) {
			subtractStock(movie.getMovieId());
		}
		return "index";
	}
	
	
	@PostMapping("/checkin")
	public String checkIn(@RequestParam("movieId") String movieID, Model model) {
		Movie searchMovie = movieRepo.findById(Long.parseLong(movieID)).get();
		CheckedOutMovies checkIn = checkedOutRepo.findByMovie(searchMovie).get(0);
		checkedOutRepo.delete(checkIn);
		model.addAttribute("movie", searchMovie);
		if (movieInventoryRepo.findById(searchMovie.getMovieId()).orElse(null) != null) {
			addStock(searchMovie.getMovieId());
		}
		return "checkinsuccessful";
	}
	

	@GetMapping("/overdue")
	public String overdueMovies(Model model) {
		if (checkedOutRepo.findAll(sortByDate) != null) {
			model.addAttribute("checkedOut", checkedOutRepo.findByCheckoutDateLessThan(LocalDate.now()));
			return "overdue";
		}
		return "index";
	}
	
	
	@GetMapping("/checkedoutmovies")
	public String checkedOut(Model model) {
		if (checkedOutRepo.findAll(sortByDate) != null) {
			model.addAttribute("checkedOut", checkedOutRepo.findAll());
			return "checkedoutmovies";
		}
		return "index";
	}
	
	@GetMapping("/currentinventory")
	public String inventory(Model model) {
		if (movieInventoryRepo.findAll() != null) {
			model.addAttribute("currentInventory", movieInventoryRepo.findAll());
			return "currentinventory";
		}
		return "index";
	}
	
	public void addInventory(Long movieID) {
		MovieInventory mi = movieInventoryRepo.findById(movieID).get();
		mi.setInventory(mi.getInventory() + 1);
		mi.setInStock(mi.getInStock() + 1);
		movieInventoryRepo.save(mi);
	}
	
	public void subtractInventory(Long movieID) {
		MovieInventory mi = movieInventoryRepo.findById(movieID).get();
		mi.setInventory(mi.getInventory() - 1);
		mi.setInStock(mi.getInStock() - 1);
		movieInventoryRepo.save(mi);
	}
	
	public void addStock(Long movieID) {
		MovieInventory mi = movieInventoryRepo.findById(movieID).get();
		mi.setInStock(mi.getInStock() + 1);
		movieInventoryRepo.save(mi);
	}
	
	public void subtractStock(Long movieID) {
		MovieInventory mi = movieInventoryRepo.findById(movieID).get();
		mi.setInStock(mi.getInStock() - 1);
		movieInventoryRepo.save(mi);
	}
}
