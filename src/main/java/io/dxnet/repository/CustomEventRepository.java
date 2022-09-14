package io.dxnet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.dxnet.entity.CustomEventEntity;

@Repository
public interface CustomEventRepository extends CrudRepository<CustomEventEntity, Long> {
	public List<CustomEventEntity> findAllByEventName(String eventName);
	public List<CustomEventEntity> findAllByUserName(String userName);
}
