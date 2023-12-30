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

    public User(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
