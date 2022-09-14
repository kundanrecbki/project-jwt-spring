package io.dxnet.mapper;

import org.mapstruct.Mapper;

import io.dxnet.dto.CustomUserResponseDto;
import io.dxnet.entity.CustomUserEntity;

@Mapper
public interface CustomUserEntityToResponseDtoMapper {
	CustomUserResponseDto customUserEntityToCustomUserDto(CustomUserEntity cue);
}
