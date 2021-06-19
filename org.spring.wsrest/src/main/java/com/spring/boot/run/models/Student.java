package com.spring.boot.run.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Student {

	@Id
	private long id;
	@NotNull
	private String name;

	private int testScore;

	private byte[] studentBytes;

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

	public byte[] getStudentBytes() {
		return studentBytes;
	}

	public void setStudentBytes(byte[] studentBytes) {
		this.studentBytes = studentBytes;
	}

}
