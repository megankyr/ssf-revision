package vttp.ssf.assessment.eventmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner {

	@Autowired
	RedisRepository repo;

	@Autowired
	DatabaseService databaseService;

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String fileName = "events.json";
		List<Event> events = databaseService.readFile(fileName);
		System.out.println("List of Events:");
		for (Event event : events) {
			System.out.println(event.toString());
			repo.saveRecord(event);
		}
		System.out.println("Event size: " + repo.getNumberOfEvents());
		System.out.println("Get event: " + repo.getEvent(1));

	}
}