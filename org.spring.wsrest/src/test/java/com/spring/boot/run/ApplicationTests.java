package com.spring.boot.run;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.spring.boot.run.models.Student;
import com.spring.boot.run.repos.StudentRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	StudentRepository repository;

	@Test
	public void testSaveStudent() {
		Student student = new Student();
		student.setId(1L);
		student.setName("Mirza");
		student.setTestScore(11);
		repository.save(student);

		Student student2 = repository.findById(1L).get();
		assertNotNull(student2);
	}

	@Test
	public void testUpdateStudent() {
		Student student = new Student();
		student.setId(1L);
		student.setName("Mirza");
		student.setTestScore(123);
		repository.save(student);

		Student student2 = repository.findById(1L).get();
		assertEquals(123, student2.getTestScore());
	}

	@Test
	public void testDeleteStudent() {
		Student student = repository.findById(1L).get();
		repository.delete(student);
		List<Student> student2 = repository.findAll();
		assertEquals(student2.size(),0);
		
	}
	
}
