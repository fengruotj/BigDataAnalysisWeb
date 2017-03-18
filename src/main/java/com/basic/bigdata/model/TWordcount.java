package com.basic.bigdata.model;

import java.sql.Timestamp;

/**
 * TWordcount entity. @author MyEclipse Persistence Tools
 */

public class TWordcount implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp time;
	private String word;
	private Long count;

	// Constructors

	/** default constructor */
	public TWordcount() {
	}

	/** full constructor */
	public TWordcount(Timestamp time, String word, Long count) {
		this.time = time;
		this.word = word;
		this.count = count;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
