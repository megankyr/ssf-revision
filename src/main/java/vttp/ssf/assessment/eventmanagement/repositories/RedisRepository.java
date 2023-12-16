package vttp.ssf.assessment.eventmanagement.repositories;

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

	public void saveRecord(Event event) {
		String key = EVENT_HASH_KEY + ":" + event.getEventId();
		template.opsForHash().put(key, "eventId", event.getEventId());
		template.opsForHash().put(key, "eventName", event.getEventName());
		template.opsForHash().put(key, "eventSize", event.getEventSize());
		template.opsForHash().put(key, "eventDate", event.getEventDate());
		template.opsForHash().put(key, "participants", event.getParticipants());

	}

	}

	// TODO: Task 3
	

// TODO: Task 4
