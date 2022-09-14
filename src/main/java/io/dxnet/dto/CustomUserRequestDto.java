package io.dxnet.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserRequestDto {
	
	@NotBlank(message = "Username can't be Blank")
	private String userName;
	
	@NotBlank(message = "Password can't be Blank")
	private String password;
}