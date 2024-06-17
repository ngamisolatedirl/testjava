package org.truongsolo.project.jpaRepository.main.impl;





import org.truongsolo.project.entity.Student;
import org.truongsolo.project.jpaRepository.annotation.Entity;
import org.truongsolo.project.jpaRepository.consts.SqlQueryConstants;
import org.truongsolo.project.jpaRepository.main.JpaExecutor;
import org.truongsolo.project.utils.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class JpaExecutorImpl<T> implements JpaExecutor<T> {
    private Class<T> clazz;
    private String  className;
    public String tableName;
    Field[] fields;

    public static void main(String[] args) {

    }
    public JpaExecutorImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        // get mapping of tableName
        this.tableName = clazz.getAnnotation(Entity.class) != null ?
                clazz.getAnnotation(Entity.class).tableName()
                : className;
        this.fields = clazz.getDeclaredFields();
        System.err.println(this.className);
        System.err.println(this.tableName);
        System.err.println(this.fields);
    }
    public abstract List<T> rowMapper(ResultSet rs);


    @Override
    public List<T> findAll() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        String sql  = SqlQueryConstants.SQL_SELECT_ALL.replace("%table_name%", this.tableName);
        PreparedStatement pt = null;
        try {
            pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            return rowMapper(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void add(T entity) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        String sql = SqlQueryConstants.SQL_INSERT.replace("%table_name%", this.tableName);
        PreparedStatement pt = null;
        try {
            pt = con.prepareStatement(sql);
            mapToPreparedStatement(pt, entity);
            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T entity) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        String sql = SqlQueryConstants.SQL_UPDATE.replace("%table_name%", this.tableName);
        PreparedStatement pt = null;
        try {
            pt = con.prepareStatement(sql);

            // Map entity fields to PreparedStatement
            mapToPreparedStatement2(pt, entity);

            // Ensure ID is set correctly
            if (entity instanceof Student) {
                Student student = (Student) entity;
                if (student.getId() != null) {
                    pt.setLong(4, student.getId()); // Correct index based on the SQL query
                } else {
                    throw new IllegalArgumentException("Student ID cannot be null");
                }
            } else {
                throw new IllegalArgumentException("Entity is not an instance of Student");
            }

            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating entity: " + entity, e);
        }
    }
@Override
public void delete(T entity) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        String sql = SqlQueryConstants.SQL_DELETE.replace("%table_name%", this.tableName);
        PreparedStatement pt = null;
        try {
            pt = con.prepareStatement(sql);
            mapToPreparedStatement3(pt,entity);
            pt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error deleting entity: " + entity, e);
        }
}
//    @Override
//    public void addStudent(Student student){
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection con = connectionPool.getConnection();
//        String sql = SqlQueryConstants.SQL_ADD_STUDENT.replace("%table_name%", this.tableName);
//        PreparedStatement pt = null;
//        try{pt.setString(1, );
//            pt.setInt(2, );
//            pt.setString(3, );
//        }
//    }


    @Override
    public Optional<T> findById(Number id) {
        return Optional.empty();
    }

    protected abstract void mapToPreparedStatement(PreparedStatement pt, T entity) throws SQLException;
    protected abstract void mapToPreparedStatement2(PreparedStatement pt, T entity) throws SQLException;
    protected abstract void mapToPreparedStatement3(PreparedStatement pt, T entity) throws SQLException;
}
