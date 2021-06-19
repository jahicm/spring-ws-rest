package com.spring.boot.run.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.run.models.Student;
import com.spring.boot.run.models.StudentModel;
import com.spring.boot.run.models.UserModel;
import com.spring.boot.run.repos.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private StudentRepository repository;
	
	private static String OBJECT_FILE="myObjects";

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_STUDENT')")
	public List<Student> getStudents() {

		return repository.findAll();

	}

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_STUDENT')")
	public StudentModel getStudents(@PathVariable("id") long id) throws IOException, ClassNotFoundException {

		Student s = repository.findById(id).get();
		byte[] byteModel = s.getStudentBytes();
		ByteArrayInputStream bytes = new ByteArrayInputStream(byteModel);
		ObjectInputStream ois = new ObjectInputStream(bytes);

		return (StudentModel) ois.readObject();

	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Student updateStudent(@RequestBody Student student) {

		return repository.save(student);

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public Student saveStudent(@RequestBody final StudentModel studentModel) throws IOException {

		Student student = new Student();
		Set<StudentModel> modelSet = new HashSet<StudentModel>();

		student.setId(studentModel.getId());
		student.setName(studentModel.getName());
		student.setTestScore(studentModel.getTestScore());

		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArray);
		objectOutputStream.writeObject(studentModel);
		student.setStudentBytes(byteArray.toByteArray());

		modelSet.add(studentModel);
		File f = new File(OBJECT_FILE);
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fos);
		objectOutputStream2.writeObject(modelSet);
		objectOutputStream2.close();
		return repository.save(student);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteStudent(@PathVariable("id") long id) {

		repository.deleteById(id);

	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void saveUser(@RequestBody final UserModel userModel) throws IOException {

		GrantedAuthority authority = new SimpleGrantedAuthority(userModel.getAuthorities());

		Set<GrantedAuthority> authoritiesSet = new HashSet<>();
		authoritiesSet.add(authority);

		UserDetails user = User.builder().username(userModel.getUsername())
				.password(passwordEncoder.encode(userModel.getPassword())).authorities(authoritiesSet)
				.disabled(!userModel.isEnabled()).build();

		jdbcUserDetailsManager.createUser(user);

	}
}
