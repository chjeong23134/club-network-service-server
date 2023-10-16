package com.poogie.cns.room.dao;

import com.poogie.cns.room.domain.ParticipantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends CrudRepository<ParticipantEntity, Long> {
}
