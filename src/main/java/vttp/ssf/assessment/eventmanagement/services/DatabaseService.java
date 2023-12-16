package vttp.ssf.assessment.eventmanagement.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

import org.springframework.stereotype.Service;

import vttp.ssf.assessment.eventmanagement.models.Event;

@Service
public class DatabaseService {

    public List<Event> readFile(String fileName) {
        List<Event> events = new ArrayList<>();

        try (InputStream is = new FileInputStream(new File(fileName))) {
            JsonReader jsonReader = Json.createReader(is);
            JsonArray jsonArray = jsonReader.readArray();

            for (JsonValue eventValue : jsonArray) {
                JsonObject eventObject = (JsonObject) eventValue;

                Event event = new Event();
                event.setEventId(eventObject.getInt("eventId"));
                event.setEventName(eventObject.getString("eventName"));
                event.setEventSize(eventObject.getInt("eventSize"));
                event.setEventDate(eventObject.getJsonNumber("eventDate").longValue());
                event.setParticipants(eventObject.getInt("participants"));

                events.add(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

}