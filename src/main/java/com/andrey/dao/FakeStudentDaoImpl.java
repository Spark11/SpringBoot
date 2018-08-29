package com.andrey.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.andrey.entity.Student;

@SuppressWarnings("serial")
@Repository
@Qualifier("fakeData")
public class FakeStudentDaoImpl implements StudentDao {

	private static Map<Integer, Student> students;
	
	static {
		students = new HashMap<Integer, Student>(){
			{
				put(1, new Student(1, "Andrey", "CS"));
				put(2, new Student(2, "Andrey2", "CS2"));
				put(3, new Student(3, "Andrey3", "CS3"));
			}
		};
	}
	
	/* (non-Javadoc)
	 * @see com.andrey.dao.StudentDao#getAllStudents()
	 */
	@SuppressWarnings("static-access")
	@Override
	public Collection<Student> getAllStudents(){
		return this.students.values();
	}
	
	/* (non-Javadoc)
	 * @see com.andrey.dao.StudentDao#getStudentById(int)
	 */
	@Override
	public Student getStudentById(int id) {
		return students.get(id);
	}

	/* (non-Javadoc)
	 * @see com.andrey.dao.StudentDao#removeStudentById(int)
	 */
	@Override
	public void removeStudentById(int id) {
		students.remove(id);
	}
}
