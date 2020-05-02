package com.bisaKhadayate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Gotra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name" ,nullable = false)
	private String name;
	
	@ManyToOne
	private Samaj samaj;
	
	public Samaj getSamaj() {
		return samaj;
	}

	public void setSamaj(Samaj samaj) {
		this.samaj = samaj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
