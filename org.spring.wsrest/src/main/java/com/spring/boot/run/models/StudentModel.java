package com.spring.boot.run.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class StudentModel implements Serializable {

	private static final long serialVersionUID = 1234L;

	@NotNull
	private long id;
	@NotNull
	private String name;
	private int testScore;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTestScore() {
		return testScore;
	}

	public void setTestScore(int testScore) {
		this.testScore = testScore;
	}

}
