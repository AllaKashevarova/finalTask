package com.coherent.zipcode.userController;

import com.coherent.User;
import com.coherent.zipcode.BasicTestClass;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PostUsers extends BasicTestClass {

    @Test
    public void shouldCreateUsersWithPOST() throws IOException {
        String jsonUser = "src/main/resources/payload/createUserAllFields.json";
        User user = new ObjectMapper().readValue(new File(jsonUser), User.class);
        
        userController.sendPostUsers(user);
        List<User> listOfUsers = userController.sendGetUsers();
        System.out.println("User in the list: " + listOfUsers.contains(user));

        Assertions.assertTrue(listOfUsers.contains(user));
    }

    //TODO create test#2 using @JsonFilter("myFilter") https://www.baeldung.com/jackson-ignore-properties-on-serialization
}
