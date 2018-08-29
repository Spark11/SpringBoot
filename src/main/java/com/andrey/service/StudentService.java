package com.andrey.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.andrey.dao.StudentDao;
import com.andrey.entity.Student;

@Service
public class StudentService {
	
	@Autowired
	@Qualifier("fakeData")
	private StudentDao studentDao;
	
	public Collection<Student> getAllStudents(){
		return studentDao.getAllStudents();
	}
	
	public Student getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

	public void removeStudentById(int id) {
		studentDao.removeStudentById(id);
	}
	
	
}
