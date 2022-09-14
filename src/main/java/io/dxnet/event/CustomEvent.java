package io.dxnet.event;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomEvent {
	private String eventName;
	private String userName;
	private LocalDate creattionDate;
}
