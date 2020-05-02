package com.bisaKhadayate.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Samaj {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name" ,nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL ,fetch = FetchType.LAZY)
	@JoinColumn(name="samaj_id")
	List<Gotra> gotraList;
	
	public List<Gotra> getGotraList() {
		return gotraList;
	}

	public void setGotraList(List<Gotra> gotraList) {
		this.gotraList = gotraList;
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
