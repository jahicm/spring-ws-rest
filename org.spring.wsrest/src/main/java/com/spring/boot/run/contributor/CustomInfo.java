package com.spring.boot.run.contributor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.spring.boot.run.repos.StudentRepository;

@Component
public class CustomInfo implements InfoContributor {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public void contribute(Builder builder) {
		Map<String, Integer> studentDetails = new HashMap<>();
		studentDetails.put("active", (int) studentRepository.count());
		studentDetails.put("inactive", (int) studentRepository.count());

		builder.withDetail("users", studentDetails);

	}
}