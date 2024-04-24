/**
 * Riley Dodge - tjrace
 * CIS175 - Fall 2023
 * Apr 23, 2024
 */


package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dmacc.beans.MovieInventory;

@Repository
public interface MovieInventoryRepository extends JpaRepository<MovieInventory, Long> { }
