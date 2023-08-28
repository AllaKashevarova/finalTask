package helpers;

import com.coherent.token.ReadTokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonParser {
//private String token;

//    public void writeToFile(ReadTokenManager token) {
//        try (FileWriter writer = new FileWriter("src/main/resources/" + cart.getCartName() + ".json")) {
//            writer.write(gson.toJson(cart));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public String readFromJson (String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String token = jsonNode.get("access_token").asText();

        return token;
    }

}
