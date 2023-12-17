package vttp.ssf.assessment.eventmanagement.repositories;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired
	@Qualifier("redis")
	private RedisTemplate<String, Object> template;

	private static final String EVENT_HASH_KEY = "event";

	// all values within hash operations need to be saved as strings
	public void saveRecord(Event event) {
		String key = EVENT_HASH_KEY + ":" + event.getEventId();
		template.opsForHash().put(key, "eventId", event.getEventId().toString());
		template.opsForHash().put(key, "eventName", event.getEventName());
		template.opsForHash().put(key, "eventSize", event.getEventSize().toString());
		template.opsForHash().put(key, "eventDate", Long.toString(event.getEventDate()));
		template.opsForHash().put(key, "participants", event.getParticipants().toString());

	}

	// the "*" acts as a wildcard, so anything after "event:"
	public int getNumberOfEvents() {
		Set<String> hashKeys = template.keys(EVENT_HASH_KEY + ":*");
		return hashKeys.size();
	}

	public Event getEvent(Integer index) {
		String eventKey = EVENT_HASH_KEY + ":" + index;
		Map<Object, Object> eventData = template.opsForHash().entries(eventKey);
		Event event = new Event();
		event.setEventId(Integer.parseInt(eventData.get("eventId").toString()));
		event.setEventName(eventData.get("eventName").toString());
		event.setEventSize(Integer.parseInt(eventData.get("eventSize").toString()));
		event.setEventDate(Long.parseLong(eventData.get("eventDate").toString()));
		event.setParticipants(Integer.parseInt(eventData.get("participants").toString()));

		return event;
	}

	public void updateParticipants(Integer eventId, int newParticipants) {
		String eventKey = EVENT_HASH_KEY + ":" + eventId;
		template.opsForHash().put(eventKey, "participants", String.valueOf(newParticipants));
	}
}