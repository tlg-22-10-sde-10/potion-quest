
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonDemo {
    public static void main(String[] args) {
//        writeCharacterJson("character.json");
        readCharacterJson("character.json");
//        writeLocationJson("location.json");
        readLocationJson("location.json");
    }

    public static void readLocationJson(String file) {
        JSONParser parser = new JSONParser();

        try {
            FileReader fileReader = new FileReader(file);
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
            Iterator i = jsonArray.iterator();
            String locationObject = null;

            while (i.hasNext()) {
                locationObject = i.next().toString();
                System.out.println("locationObject: " + locationObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeLocationJson(String file) {
        JSONObject locationJson = new JSONObject();

        JSONArray listOfLocations = new JSONArray();
        JSONObject aLocation = new JSONObject();

        JSONArray listOfItemsInVillage1 = new JSONArray();
        JSONObject dagger = new JSONObject();
        JSONObject trinket = new JSONObject();
        JSONObject bag = new JSONObject();
        dagger.put("name", "dagger");
        trinket.put("name", "trinket");
        bag.put("name", "bag");

        List<Object> listOfItems = Arrays.asList(dagger, trinket, bag);
        listOfItemsInVillage1.add(listOfItems);
        aLocation.put("name", "Village");
        aLocation.put("description", "A quaint village");
        aLocation.put("items", listOfItemsInVillage1);

        listOfLocations.add(aLocation);
        locationJson.put("characters", listOfLocations);

        try {
            FileWriter jsonFileWriter = new FileWriter(file);
            jsonFileWriter.write(listOfLocations.toJSONString());
            jsonFileWriter.flush();
            jsonFileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCharacterJson(String file) {
        JSONObject character = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject aCharacter = new JSONObject();
        aCharacter.put("name", "Stranger");
        aCharacter.put("class", "Warrior");
        jsonArray.add(aCharacter);
        character.put("characters", jsonArray);

        try {
            FileWriter jsonFileWriter = new FileWriter(file);
            jsonFileWriter.write(jsonArray.toJSONString());
            jsonFileWriter.flush();
            jsonFileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCharacterJson(String file) {
        JSONParser parser = new JSONParser();

        try {
            FileReader fileReader = new FileReader(file);
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
            Iterator i = jsonArray.iterator();
            String characterObject = null;

            while (i.hasNext()) {
                characterObject = i.next().toString();
                System.out.println("characterObject: " + characterObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
