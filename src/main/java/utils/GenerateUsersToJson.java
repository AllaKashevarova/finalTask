package utils;

import com.coherent.user.User;
import com.coherent.user.UserController;
import com.coherent.zipcode.ZipCodeController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class GenerateUsersToJson {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GenerateUsersToJson.class);

    public List<User> generateUserList(int count) {
        ZipCodeController zipCodeController = new ZipCodeController();
        Faker faker = new Faker();
        List<User> userList = new ArrayList<>();
        List<String> zipCodesListToPublish = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String sex = faker.options().option("MALE", "FEMALE");
            String zip = faker.number().digits(6);
            User user = new User(faker.number().numberBetween(1, 99), faker.name().firstName(), sex, zip);
            userList.add(user);
            zipCodesListToPublish.add(zip);
        }

        zipCodeController.sendPostZipCodes(zipCodesListToPublish, 201);
        logger.info("List of Users: " + userList);
        return userList;
    }

    public File saveToJson(List<User> userList) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String folderPath = "src/main/resources/generatedUsers/";
            String fileName = folderPath + "user_list_" + timestamp + ".json";
            File outputFile = new File(fileName);

            objectMapper.writeValue(outputFile, userList);
            System.out.println("Data saved to: " + fileName);

            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
