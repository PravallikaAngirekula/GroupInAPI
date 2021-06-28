package com.vns.groupin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State {
	
	@Id
	@Column(name= "state_id")
	private int stateId;
	
	@Column(name = "state_name")
	private String stateName;



	
	
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;
	
	
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade=CascadeType.ALL )
	 private List<City> city;


	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public List<City> getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(List<City> city) {
		this.city = city;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
	
//	public List<City> getCities() {
//		return cities;
//	}
//
//	public void setCities(List<City> cities) {
//		this.cities = cities;
//	}

//	public Country getCountry() {
//		return country;
//	}
//
//	public void setCountry(Country country) {
//		this.country = country;
//	}

	

//	@OneToMany(targetEntity=City.class, cascade = CascadeType.ALL )
//	@JoinColumn(name = "state_id", referencedColumnName = "state_id")
////	@JoinTable(name = "state_cities", joinColumns = @JoinColumn (name = "state_id"), inverseJoinColumns= @JoinColumn(name="city_id"))
//	private List<City> cities;
//	public List<City> getCities() {
//		return cities;
//	}
//
//	public void setCities(List<City> cities) {
//		this.cities = cities;
//	}

	


