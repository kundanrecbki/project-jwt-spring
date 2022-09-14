package io.dxnet.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "custom_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserEntity implements Serializable {

	private static final long serialVersionUID = -6988538334389142936L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long id;
	
	@Column(unique = true)
	private String userName;
	
	@Column
	@JsonIgnore
	private String password;
	
	//Not implementing Right Now
	//private Set<GrantedAuthority> authorities;

	@Column
	@Builder.Default
	private  boolean accountNonExpired = true;

	@Column
	@Builder.Default
	private boolean accountNonLocked = true;

	@Column
	@Builder.Default
	private boolean credentialsNonExpired = true;

	@Column
	@Builder.Default
	private boolean enabled = true;
	
	@Column(name="creation_date", nullable=true)
	private LocalDate  creationDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CustomEventEntity> events;

}