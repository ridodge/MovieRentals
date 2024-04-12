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
import java.time.LocalDate;

/**
 * 
 */
@Entity
public class Member {
	@Id
	private Long id;
	private String name;
	private LocalDate memberSince;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
	}

	public Member(Long id, String name, LocalDate memberSince) {
		this.id = id;
		this.name = name;
		this.memberSince = memberSince;
	}

	public Member() {
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", memberSince=" + memberSince + "]";
	}

}
