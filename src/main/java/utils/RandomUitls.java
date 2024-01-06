package utils;

import com.coherent.user.User;
import com.coherent.zipcode.ZipCodeController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomUitls {
    private final Faker faker = new Faker();

    ZipCodeController zipCodeController = new ZipCodeController();

    public String randomZip() {
        String zip = RandomStringUtils.randomAlphanumeric(6);
        return zip;
    }

    public void sendPostWithRandomZipCodes(String oldZip, String newZip) {

        List<String> requestBody = new ArrayList<>(Arrays.asList(oldZip, newZip));
        zipCodeController.sendPostZipCodes(requestBody, 201);
    }

    @SneakyThrows
    public User getRandomUser() {
        String newZip = randomZip();
        String name = faker.name().firstName();
        List<String> requestBody = new ArrayList<String>();
        requestBody.add(newZip);
        zipCodeController.sendPostZipCodes(requestBody, 201);

        String jsonUser = "src/main/resources/updateUser/user.json";
        User user = new ObjectMapper().readValue(new File(jsonUser), User.class);
        user.setZipCode(newZip);
        user.setName(name);

        return user;
    }
}
