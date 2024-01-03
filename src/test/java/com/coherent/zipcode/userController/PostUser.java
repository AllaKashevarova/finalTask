package com.coherent.zipcode.userController;

import com.coherent.user.User;
import com.coherent.zipcode.BasicTestClass;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PostUser extends BasicTestClass {

    @DisplayName("Scenario 1 - check user is added and zip code is removed")
    @Test
    public void shouldCreateUserAndRemoveZip() throws IOException {
        String jsonUser = "src/main/resources/payload/createUserAllFields.json";
        User user = new ObjectMapper().readValue(new File(jsonUser), User.class);

        userController.sendPostUsers(user);
        List<User> listOfUsers = userController.sendGetUsers();
        System.out.println("User in the list: " + listOfUsers.contains(user));

        JsonNode jsonNode = new ObjectMapper().readTree(new File(jsonUser));
        String zipCodeToBeDeleted = jsonNode.get("zipCode").asText();

        List<String> zipCodesResponse = zipCodeController.sendGetZipCodes(201);

        org.assertj.core.api.Assertions.assertThat(zipCodesResponse).doesNotContain(zipCodeToBeDeleted);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(user);

        //BUG:
        //Actual Result: zip code hasn't been removed from the list
        //Expected Result: zip code is removed from the list once User is created
    }

    @DisplayName("Scenario 2 - check user is created with required fields")
    @Test
    public void shouldCreateUserRequiredFields() throws IOException {
        String jsonUser = "src/main/resources/payload/createUserRequiredFields.json";
        User user = new ObjectMapper().readValue(new File(jsonUser), User.class);

        userController.sendPostUsers(user);
        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(user);
        //No bugs
    }

    @DisplayName("Scenario 3 - check response failes when zip code is unavailable")
    @Test
    public void shouldFailWhenZipIncorrect() throws IOException {
        String jsonUser = "src/main/resources/payload/createUserZipIncorrect.json";
        User user = new ObjectMapper().readValue(new File(jsonUser), User.class);

        //Question: Since we assert 201 status code in sendPostUsers() then this test fails. To make it pass
        //the run - should I remove assertion from sendPostUsers()? Other tests won't benefit from this solution...
        userController.sendPostUsers(user);
        List<User> listOfUsers = userController.sendGetUsers();


        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(user);
        //No bugs
    }

    @DisplayName("Scenario 4 - check response failes when adding existed user")
    @Test
    public void shouldFailWhenAddExistingUser() throws IOException {
        String jsonUser = "src/main/resources/payload/createExistingUser.json";
        User user = new ObjectMapper().readValue(new File(jsonUser), User.class);
        userController.sendPostUsers(user);
        userController.sendPostUsers(user);

        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(listOfUsers).containsOnlyOnce(user);
        //BUG
        //Actual Result: Same User can be added multiple times
        //Expected Result: User can be added just once
    }
}
