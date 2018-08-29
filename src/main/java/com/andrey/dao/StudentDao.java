package com.andrey.dao;

import java.util.Collection;

import com.andrey.entity.Student;

public interface StudentDao {

	Collection<Student> getAllStudents();

	Student getStudentById(int id);

	void removeStudentById(int id);

}