package org.truongsolo.project.repository;


import org.truongsolo.project.entity.Student;
import org.truongsolo.project.jpaRepository.main.impl.JpaExecutorImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryReflect extends JpaExecutorImpl<Student> {

    public StudentRepositoryReflect(Class<Student> clazz) {
        super(clazz);
    }

    public static void main(String[] args) {
        StudentRepositoryReflect repo = new StudentRepositoryReflect(Student.class);
        repo.findAll();
    }

    @Override
    public List<Student> rowMapper(ResultSet rs) {
        List<Student> students = new ArrayList<>();
        try {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setFirstname(rs.getString("firstName"));
                s.setLastname(rs.getString("lastName"));
                s.setAddress(rs.getString("address"));
                s.setAge(rs.getInt("age"));
                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    protected void mapToPreparedStatement(PreparedStatement pt, Student student) throws SQLException {
        pt.setLong(1, student.getId());
        pt.setString(2, student.getFirstname());
        pt.setString(3, student.getLastname());
        pt.setString(4, student.getAddress());

    }
    @Override
    protected void mapToPreparedStatement2(PreparedStatement pt, Student student) throws SQLException {

            pt.setString(1, student.getFirstname());
            pt.setString(2, student.getLastname());
            pt.setString(3, student.getAddress());
            pt.setLong(4, student.getId());
    }
    @Override
    protected void mapToPreparedStatement3(PreparedStatement pt, Student student) throws SQLException {
        pt.setLong(1, student.getId());
    }
}
