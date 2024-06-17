package org.truongsolo.project.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.truongsolo.project.jpaRepository.annotation.Column;
import org.truongsolo.project.jpaRepository.annotation.Entity;
import org.truongsolo.project.jpaRepository.annotation.Id;


@Getter
@Setter
@Builder
//@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "student")
public class Student {

    @Id(name =  "id") // meaning id : primary key
    private Long id;
    @Column(columnName = "firstname")
    private String firstname;
    @Column(columnName = "lastname")
    private String lastname;
    @Column(columnName = "address")
    private String address;
    @Column(columnName = "age")
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
