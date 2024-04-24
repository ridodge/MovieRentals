/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 23, 2024
 */

package dmacc.beans;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MovieInventory {
	@Id
	private Long movieID;
	private int inventory;
	private int inStock;
	private String name;

	public MovieInventory() {
	}


	public MovieInventory(Long movieID, int inventory, int inStock, String name) {
		this.movieID = movieID;
		this.inventory = inventory;
		this.inStock = inStock;
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getInStock() {
		return inStock;
	}


	public void setInStock(int inStock) {
		this.inStock = inStock;
	}


	public Long getMovieID() {
		return movieID;
	}

	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "MovieInventory [movieID=" + movieID + ", inventory=" + inventory + ", inStock=" + inStock + ", name="
				+ name + "]";
	}

}
