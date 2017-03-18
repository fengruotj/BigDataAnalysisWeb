package com.basic.bigdata.model;

/**
 * Administrator entity. @author MyEclipse Persistence Tools
 */

public class Administrator implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String username;
	private String password;

	// Constructors

	/** default constructor */
	public Administrator() {
	}

	/** minimal constructor */
	public Administrator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public Administrator(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
