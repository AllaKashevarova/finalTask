package com.coherent.zipcode.userController;

import com.coherent.user.User;
import com.coherent.zipcode.BasicTestClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.GenerateUsersToJson;

import java.io.File;
import java.util.List;

public class UploadUser extends BasicTestClass {
    private GenerateUsersToJson userGenerator = new GenerateUsersToJson();


    @DisplayName("Scenario 1 - All users are replaced with users from file And Response contains number of uploaded users")
    @Test
    public void sendPOSTWithUpload(){
        int expectedNumberOfUsers = 5;
        List<User> userList = userGenerator.generateUserList(expectedNumberOfUsers);
        File usersFile = userGenerator.saveToJson(userList);
        String responseUsers = userController.sendPostUsersWithUpload(usersFile);

        Assertions.assertThat(responseUsers).isEqualTo("Number of users = " + expectedNumberOfUsers);
    }

    @DisplayName("Scenario 2 - If incorrect Zip Code get 424 response code And Users are not uploaded")
    @Test
    public void sendPOSTWithUploadFailsIfIncorrectZipCode(){
        User dummyUser  = new User(30, "Anna", "FEMALE","00000");
        List<User> userList = userGenerator.generateUserList(5);
        userList.add(dummyUser);
        File usersFile = userGenerator.saveToJson(userList);
        userController.sendPostUsersWithUpload(usersFile);

        //BUG
        //Getting 500 error instead of 424
        //Question:
        //Should I assert a status code here in this test as well? I have this assert already in .sendPostUsersWithUpload() method.
    }

    @DisplayName("Scenario 3 - If required field is missing then Users are not uploaded")
    @Test
    public void sendPOSTWithUploadFailsIfMissedRequiredField(){
        User dummyUser  = new User(39, null, "FEMALE","123456");
        List<User> userList = userGenerator.generateUserList(5);
        userList.add(dummyUser);
        File usersFile = userGenerator.saveToJson(userList);
        userController.sendPostUsersWithUpload(usersFile);

        //BUG
        //Getting 500 error instead of 409
    }
}
