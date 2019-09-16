package com.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String commment;

	public Review() {
		
	}

	public Review(String commment) {
		super();
		this.commment = commment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommment() {
		return commment;
	}

	public void setCommment(String commment) {
		this.commment = commment;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", commment=" + commment + "]";
	}
	
	
	
	
	
}
