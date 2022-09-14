package io.dxnet.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "custom_event")
@Getter
@Setter
public class CustomEventEntity implements Serializable {

	private static final long serialVersionUID = 6963486257952175829L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="user_name", nullable=false)
	private String userName;
	
	@Column(name="event_name", nullable=false)
	private String eventName;
	
	@Column(name="creation_date", nullable=true)
	private LocalDate creationDate;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "events", nullable = true)
    private CustomUserEntity user;
}
