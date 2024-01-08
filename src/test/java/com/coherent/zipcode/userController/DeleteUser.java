package com.coherent.zipcode.userController;

import com.coherent.user.User;
import com.coherent.zipcode.BasicTestClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Base64;
import java.util.List;

public class DeleteUser extends BasicTestClass {
    User randomUser;

    @SneakyThrows
    @BeforeEach
    public void beforeEach() {
        randomUser = randomUitls.getRandomUser();
    }

    @DisplayName("Scenario 1 - User is deleted and Zip code is returned in list of available zip codes")
    @Test
    public void deleteUser() {
        userController.sendPostUsers(randomUser);
        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(randomUser);

        int expectedResponseCode = 204;
        int actualStatusCode = userController.sendDeleteUsers(randomUser);

        List<User> finalListOfUsers = userController.sendGetUsers();
        List<String> zipCodesResponse = zipCodeController.sendGetZipCodes(201);

        Assertions.assertThat(actualStatusCode).isEqualTo(expectedResponseCode);
        Assertions.assertThat(finalListOfUsers).doesNotContain(randomUser);
        Assertions.assertThat(zipCodesResponse).contains(randomUser.getZipCode());
    }

    @DisplayName("Scenario 2 - User with required fields only is deleted and Zip code is returned in list of available zip codes")
    @Test
    public void deleteUserWithRequiredFields() {
        randomUser.setZipCode(null);
        randomUser.setAge(null);

        userController.sendPostUsers(randomUser);
        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(randomUser);

        int expectedResponseCode = 204;
        int actualStatusCode = userController.sendDeleteUsers(randomUser);

        List<User> finalListOfUsers = userController.sendGetUsers();

        Assertions.assertThat(actualStatusCode).isEqualTo(expectedResponseCode);
        Assertions.assertThat(finalListOfUsers).doesNotContain(randomUser);
        //Assertions.assertThat(zipCodesResponse).contains(randomUser.getZipCode());
        //NOTE: zipCode assert is useless here since it's not a required field
    }

    @DisplayName("Scenario 3 - User is not deleted when required field is missing")
    @Test
    public void deleteUserRequiredFieldMissing() {
        userController.sendPostUsers(randomUser);
        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(randomUser);
        String userName = randomUser.getName();
        randomUser.setName(null);

        int expectedResponseCode = 409;
        int actualStatusCode = userController.sendDeleteUsers(randomUser);
        randomUser.setName(userName);

        List<User> finalListOfUsers = userController.sendGetUsers();

        Assertions.assertThat(actualStatusCode).isEqualTo(expectedResponseCode);
        Assertions.assertThat(finalListOfUsers).contains(randomUser);

    }
}
