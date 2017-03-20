package com.basic.bigdata.model;

import java.sql.Timestamp;

/**
 * TTuplecount entity. @author MyEclipse Persistence Tools
 */

public class THdfsbytecount implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp time;
	private Long bytecount;

	// Constructors

	/** default constructor */
	public THdfsbytecount() {
	}

	/** full constructor */
	public THdfsbytecount(Timestamp time, Long bytecount) {
		this.time = time;
		this.bytecount = bytecount;
	}

	// Property accessors


	public Long getBytecount() {
		return bytecount;
	}

	public void setBytecount(Long bytecount) {
		this.bytecount = bytecount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
}
