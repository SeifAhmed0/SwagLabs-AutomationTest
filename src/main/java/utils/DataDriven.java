package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataDriven {

    public static JsonNode jsonReader(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            File file = new File("testData.json");
            return mapper.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

}
