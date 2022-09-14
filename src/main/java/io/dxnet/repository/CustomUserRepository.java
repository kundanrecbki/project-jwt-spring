package io.dxnet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.dxnet.entity.CustomUserEntity;


@Repository
public interface CustomUserRepository extends CrudRepository<CustomUserEntity, Long> {
	
	Optional<CustomUserEntity> findByUserName(String userName);
	
}