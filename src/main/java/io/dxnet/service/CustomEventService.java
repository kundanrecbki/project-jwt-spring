package io.dxnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dxnet.entity.CustomEventEntity;
import io.dxnet.event.CustomEvent;
import io.dxnet.repository.CustomEventRepository;

@Service
public class CustomEventService {
	

	@Autowired
	private CustomEventRepository eventRepo;
	
	public void saveEvent(CustomEvent event) {
		CustomEventEntity newEvent = new CustomEventEntity();
		newEvent.setEventName(event.getEventName());
		newEvent.setUserName(event.getUserName());
		newEvent.setCreationDate(event.getCreattionDate());
		eventRepo.save(newEvent);
	}

	public Iterable<CustomEventEntity> getAllEvents() {
		return eventRepo.findAll();
	}

	public Iterable<CustomEventEntity> getAllEventsByUserName(String userName) {
		// Exception to be thrown if User event is not found
		return eventRepo.findAllByUserName(userName);
	}
}
