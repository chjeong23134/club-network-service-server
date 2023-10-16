package com.poogie.cns.room.dao;

import com.poogie.cns.room.domain.ParticipantEntity;
import com.poogie.cns.room.domain.RoomEntity;
import com.poogie.cns.room.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final ParticipantRepository participantRepository;

    public RoomEntity add(RoomRequestDto.create req) {
        RoomEntity roomEntity = roomRepository.save(req.toEntity());
        ParticipantEntity participantEntity = ParticipantEntity.builder()
                .roomId(roomEntity.getId())
                .userId(roomEntity.getCreateBy())
                .build();

        participantRepository.save(participantEntity);

        return roomEntity;
    }
}
