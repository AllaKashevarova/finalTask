package com.coherent.zipcode.userController;

import com.coherent.user.PatchRequestBody;
import com.coherent.user.User;
import com.coherent.zipcode.BasicTestClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UpdateUser extends BasicTestClass {
    private String oldZip;
    private String newZip;
    private String name;
    private String oldJsonUser;
    private String newJsonUser;
    private String incorrectZipJsonUser;
    private String missedRequiredFieldsJsonUser;
    private User oldUser;
    private User newUser;
    private User incorrectZipUser;
    private User missedRequiredFieldsUser;


    @SneakyThrows
    @BeforeEach
    public void beforeEach() {
        oldZip = randomUitls.randomZip();
        newZip = randomUitls.randomZip();
        randomUitls.sendPostWithRandomZipCodes(oldZip, newZip);
        name = faker.name().firstName();

        oldJsonUser = "src/main/resources/updateUser/user.json";
        oldUser = new ObjectMapper().readValue(new File(oldJsonUser), User.class);
        oldUser.setZipCode(oldZip);
        oldUser.setName(name);

        newJsonUser = "src/main/resources/updateUser/user.json";
        newUser = new ObjectMapper().readValue(new File(newJsonUser), User.class);
        newUser.setZipCode(newZip);
        newUser.setName(name);

        incorrectZipJsonUser = "src/main/resources/updateUser/userWrongZip.json";
        incorrectZipUser = new ObjectMapper().readValue(new File(incorrectZipJsonUser), User.class);
        incorrectZipUser.setName(name);

        missedRequiredFieldsJsonUser = "src/main/resources/updateUser/userRequiredFieldsMissed.json";
        missedRequiredFieldsUser = new ObjectMapper().readValue(new File(missedRequiredFieldsJsonUser), User.class);
    }

    @DisplayName("Scenario 1 - PATCH - User is updated")
    @Test
    public void shouldUpdateUser() {
        userController.sendPostUsers(oldUser);

        PatchRequestBody patchRequestBody = new PatchRequestBody(newUser, oldUser);

        userController.sendPatchUsers(patchRequestBody);
        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(newUser);
    }

    @DisplayName("Scenario 2 - PATCH - User is not updated if Zip is incorrect")
    @Test
    public void shouldNotUpdateUserIfInvalidZip() throws IOException {
        userController.sendPostUsers(oldUser);

        PatchRequestBody patchRequestBody = new PatchRequestBody(incorrectZipUser, oldUser);

        int actualStatusCode = userController.sendPatchUsers(patchRequestBody);
        int expectedStatusCode = 424;

        List<User> listOfUsers = userController.sendGetUsers();

        Assertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(incorrectZipUser);
    }

    @DisplayName("Scenario 3 - PATCH - User is not updated if Required fields are missed")
    @Test
    public void shouldNotUpdateUserIfRequiredFieldsMissed() throws IOException {
        userController.sendPostUsers(oldUser);

        PatchRequestBody patchRequestBody = new PatchRequestBody(missedRequiredFieldsUser, oldUser);

        int actualStatusCode = userController.sendPatchUsers(patchRequestBody);
        int expectedStatusCode = 409;

        List<User> listOfUsers = userController.sendGetUsers();

        Assertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(missedRequiredFieldsUser);
    }

    @DisplayName("Scenario 1 - PUT - User is updated")
    @Test
    public void shouldUpdateUserWithPUT() throws IOException {
        userController.sendPostUsers(oldUser);

        userController.sendPostUsers(newUser);

        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(oldUser);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).contains(newUser);
        //BUG
        //User hasn't been updated but created a new record
    }

    @DisplayName("Scenario 2 - PUT - User is not updated if Zip is incorrect")
    @Test
    public void shouldNotUpdateUserWithPUTIfZipIncorrect() throws IOException {
        userController.sendPostUsers(oldUser);

        int actualStatusCode = userController.sendPostUsers(incorrectZipUser);
        int expectedStatusCode = 424;

        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(incorrectZipUser);
    }

    @DisplayName("Scenario 3 - PUT - User is not updated if Required fields are missed")
    @Test
    public void shouldNotUpdateUserWithPUTIfRequiredFieldsMissed() throws IOException {
        userController.sendPostUsers(oldUser);

        int actualStatusCode = userController.sendPostUsers(missedRequiredFieldsUser);
        int expectedStatusCode = 409;

        List<User> listOfUsers = userController.sendGetUsers();

        org.assertj.core.api.Assertions.assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
        org.assertj.core.api.Assertions.assertThat(listOfUsers).doesNotContain(missedRequiredFieldsUser);
    }

}
