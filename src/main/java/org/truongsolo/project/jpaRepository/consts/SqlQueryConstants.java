package org.truongsolo.project.jpaRepository.consts;

public class SqlQueryConstants {
    public static final String SQL_SELECT_ALL = "select * from %table_name% limit 1000 offset 1;";
    public static final String SQL_INSERT = "insert into %table_name% (id,firstname,lastname,address) values (?,?,?,?)";
    public static final String SQL_DELETE = "delete from %table_name% where id=?";
    public static final String SQL_UPDATE = "update %table_name% set firstname=?,lastname=?,address=? where id=?";
}
