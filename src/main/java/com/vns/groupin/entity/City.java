package com.vns.groupin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@Column(name = "city_id")
	private int cityId;
	
	@Column(name = "city_name")
	private String cityName;
	
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "state_id", nullable = false)
	    private State state;
	
//	@Column(name = "state_id")
//	private int stateSId;
	
//	public int getStateSId() {
//		return stateSId;
//	}
//
//	public void setStateSId(int stateSId) {
//		this.stateSId = stateSId;
//	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	public String getCityName() {
		return cityName;
	}
//
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

//	public int getStateSId() {
//		return stateSId;
//	}
//
//	public void setStateSId(int stateSId) {
//		this.stateSId = stateSId;
//	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	

}
