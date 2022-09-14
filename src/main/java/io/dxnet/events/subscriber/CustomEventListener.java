package io.dxnet.events.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import io.dxnet.event.CustomEvent;
import io.dxnet.service.CustomEventService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomEventListener {
	
	@Autowired
	private CustomEventService eventService; 
	
	@Async
	@EventListener({CustomEvent.class})
	public void listen(CustomEvent event) {
		log.info("Event Type: " + event.getEventName());
		log.info("Username: " + event.getUserName());
		eventService.saveEvent(event);
	}
}
