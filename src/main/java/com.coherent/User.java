package com.coherent;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

//@JsonPropertyOrder({ "age", "name", "sex", "zipCode" })
@Data

public class User {
    private Integer age;
    private String name;
    private String sex;
    private String zipCode;

    public User() {
    }

    public User(Integer age, String name, String sex, String zipCode) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.zipCode = zipCode;
    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//
//    public String getZipCode() {
//        return zipCode;
//    }
//
//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "age=" + age +
//                ", name='" + name + '\'' +
//                ", sex='" + sex + '\'' +
//                ", zipCode='" + zipCode + '\'' +
//                '}';
//    }
}
