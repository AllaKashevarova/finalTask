package com.coherent.zipcode.userController;

import com.coherent.User;
import com.coherent.zipcode.BasicTestClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

public class GetUsers extends BasicTestClass {
    @DisplayName("Scenario 1 - Get all users stored in the application for now")
    @Test
    public void shouldGetUsersWithGET() {
        List<User> userList = userController.sendGetUsers();
        userList.forEach(System.out::println);

        Assertions.assertThat(userList).isNotEmpty();
    }

    @DisplayName("Scenario 2 - get all users older than value of parameter")
    @Test
    public void shouldGetUsersOlderThan() {
        final Integer comparedAge = 10;
        List<NameValuePair> queryParams = new ArrayList<>();

        queryParams.add(new BasicNameValuePair("olderThan", String.valueOf(comparedAge)));

        List<User> userList = userController.sendGetUsersWithParams(queryParams);
        userList.forEach(System.out::println);

        List<User> expectedUsersList = userList.stream()
                .filter(user -> user.getAge() > comparedAge)
                .toList();

        Assertions.assertThat(userList).isEqualTo(expectedUsersList);

    }

    @DisplayName("Scenario 3 - get all users younger than value of parameter")
    @Test
    public void shouldGetUsersYoungerThan() {
        final Integer comparedAge = 50;
        List<NameValuePair> queryParams = new ArrayList<>();

        queryParams.add(new BasicNameValuePair("youngerThan", String.valueOf(comparedAge)));

        List<User> userList = userController.sendGetUsersWithParams(queryParams);
        userList.forEach(System.out::println);

        List<User> expectedUsersList = userList.stream()
                .filter(user -> user.getAge() < comparedAge)
                .toList();

        Assertions.assertThat(userList).isEqualTo(expectedUsersList);
    }

    @DisplayName("Scenario 4 - all users with sex value of parameter")
    @Test
    public void shouldGetUsersWithSex() {
        final String sex = "FEMALE";
        List<NameValuePair> queryParams = new ArrayList<>();

        queryParams.add(new BasicNameValuePair("sex", String.valueOf(sex)));

        List<User> userList = userController.sendGetUsersWithParams(queryParams);
        userList.forEach(System.out::println);

        List<User> expectedUsersList = userList.stream()
                .filter(user -> Objects.equals(user.getSex(), sex))
                .toList();

        Assertions.assertThat(userList).isEqualTo(expectedUsersList);
    }
}
