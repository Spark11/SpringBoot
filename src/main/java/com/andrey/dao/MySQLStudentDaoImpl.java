package com.andrey.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.andrey.entity.Student;

@Repository
@Qualifier("mysqlData")
public class MySQLStudentDaoImpl implements StudentDao {

	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeStudentById(int id) {
		// TODO Auto-generated method stub

	}

}
