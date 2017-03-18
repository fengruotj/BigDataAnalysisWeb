package com.basic.bigdata.model;

import java.sql.Timestamp;

/**
 * TTuplelatency entity. @author MyEclipse Persistence Tools
 */

public class TTuplelatency implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long tuplelatency;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public TTuplelatency() {
	}

	/** full constructor */
	public TTuplelatency(Long tuplelatency, Timestamp time) {
		this.tuplelatency = tuplelatency;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTuplelatency() {
		return this.tuplelatency;
	}

	public void setTuplelatency(Long tuplelatency) {
		this.tuplelatency = tuplelatency;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
