package com.coherent.zipcode.userController;

import com.coherent.user.PatchRequestBody;
import com.coherent.user.User;
import com.coherent.zipcode.BasicTestClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UpdateUser extends BasicTestClass {

    @DisplayName("Scenario 1 - User is updated")
    @Test
    public void shouldUpdateUser() throws IOException {
        //TODO for future: try some randomizer class
        String jsonUser1 = "src/main/resources/payload/createUserAllFields.json";
        User userToChange = new ObjectMapper().readValue(new File(jsonUser1), User.class);

        String jsonUser2 = "src/main/resources/updateUser/userNewValues.json";
        User userNewValues = new ObjectMapper().readValue(new File(jsonUser2), User.class);

        userController.sendPostUsers(userToChange);

        PatchRequestBody patchRequestBody = new PatchRequestBody(userNewValues, userToChange);

        userController.sendPatchUsers(patchRequestBody);

        List<User> listOfUsers = userController.sendGetUsers();
        System.out.println("Updated User is on the server: " + listOfUsers.contains(userToChange));

        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(userNewValues);
    }

    @DisplayName("Scenario 2 - User is not updated if Zip is incorrect")
    @Test
    public void shouldNotUpdateUserIfInvalidZip() throws IOException {
        String jsonUser1 = "src/main/resources/payload/createUserAllFields.json";
        User userToChange = new ObjectMapper().readValue(new File(jsonUser1), User.class);

        String jsonUser2 = "src/main/resources/updateUser/userWrongZip.json";
        User userNewValues = new ObjectMapper().readValue(new File(jsonUser2), User.class);

        userController.sendPostUsers(userToChange);

        PatchRequestBody patchRequestBody = new PatchRequestBody(userNewValues, userToChange);

        int actualStatusCode = userController.sendPatchUsers(patchRequestBody);
        int expectedStatusCode = 424;

        List<User> listOfUsers = userController.sendGetUsers();
        System.out.println("Updated User is on the server: " + listOfUsers.contains(userToChange));

        Assertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(userNewValues);
    }

    @DisplayName("Scenario 3 - User is not updated if Required fields are missed")
    @Test
    public void shouldNotUpdateUserIfRequiredFieldsMissed() throws IOException {
        String jsonUser1 = "src/main/resources/payload/createUserAllFields.json";
        User userToChange = new ObjectMapper().readValue(new File(jsonUser1), User.class);

        String jsonUser2 = "src/main/resources/updateUser/userRequiredFieldsMissed.json";
        User userNewValues = new ObjectMapper().readValue(new File(jsonUser2), User.class);

        userController.sendPostUsers(userToChange);

        PatchRequestBody patchRequestBody = new PatchRequestBody(userNewValues, userToChange);

        int actualStatusCode = userController.sendPatchUsers(patchRequestBody);
        int expectedStatusCode = 409;

        List<User> listOfUsers = userController.sendGetUsers();
        System.out.println("Updated User is on the server: " + listOfUsers.contains(userToChange));

        Assertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(userNewValues);
    }
}
