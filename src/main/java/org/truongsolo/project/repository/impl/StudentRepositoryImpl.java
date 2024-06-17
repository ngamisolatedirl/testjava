package org.truongsolo.project.repository.impl;



import org.truongsolo.project.entity.Student;
import org.truongsolo.project.jpaRepository.consts.SqlQueryConstants;
import org.truongsolo.project.repository.StudentRepository;
import org.truongsolo.project.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl  implements StudentRepository {
    private static final String SQL_QUERY_STUDENT_BY_ID = "Select * from student where id  = ?";
    public static final String SQL_ADD_STUDENT = "insert into %table_name% (id,firstname,lastname,address) values (?,?,?,?)";
    public static final String SQL_UPDATE_STUDENT = "update %table_name% set id = ? where id = ?";
    public static final String SQL_DELETE_STUDENT = "delete from %table_name% where id = ?";
    @Override
    public Optional<List<Student>> getById(String id) {
//        Connection connection = DatabaseHelper.getConnection();
        List<Student> students = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        try {
            // fixme  : sql injection
            PreparedStatement pt = con.prepareStatement(SQL_QUERY_STUDENT_BY_ID);
            pt.setString(1,String.valueOf(id));
            ResultSet rs = pt.executeQuery();
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(SQL_QUERY_STUDENT_BY_ID.replace("?"
//                    ,String.valueOf(id)));
            while (rs.next()){
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setFirstname(rs.getString("firstname"));
                s.setLastname(rs.getString("lastname"));
                s.setAddress(rs.getString("address"));

                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  Optional.of(students);
    }

    @Override
    public void addStudent(Student student) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pt = con.prepareStatement(SQL_ADD_STUDENT)) {

            pt.setLong(1, student.getId()); // Assuming ID is a Long
            pt.setString(2, student.getFirstname());
            pt.setString(3, student.getLastname());
            pt.setString(4, student.getAddress());

            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding student: " + student, e);
        }
    }

    @Override
    public void updateStudent(Student student) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (Connection con = connectionPool.getConnection();
             PreparedStatement pt = con.prepareStatement(SQL_UPDATE_STUDENT)) {

            pt.setString(1, student.getFirstname());
            pt.setString(2, student.getLastname());
            pt.setString(3, student.getAddress());
            pt.setLong(4, student.getId());
            int rowsAffected = pt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating student failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student: " + student, e);
        }

    }
    @Override
    public void deleteStudent(Student student) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (Connection con = connectionPool.getConnection();
            PreparedStatement pt = con.prepareStatement(SQL_DELETE_STUDENT)){
                pt.setLong(1,student.getId());
               int rowsAffected =  pt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating student failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student: " + student, e);
        }
            }



    @Override
    public List<Student> getAll() {
        return List.of();
    }
}
    // extend vs implement

