package io.dxnet.events.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import io.dxnet.event.CustomEvent;

@Component
public class CustomEventPublisher {
	private final ApplicationEventPublisher publisher; 
	
	CustomEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	public void publishEvent(CustomEvent event) {
	    publisher.publishEvent(event);
	  }
}
