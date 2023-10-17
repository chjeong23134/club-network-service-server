package com.poogie.cns.room.dao;

import com.poogie.cns.room.domain.ParticipantEntity;
import com.poogie.cns.room.domain.RoomEntity;
import com.poogie.cns.room.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<RoomEntity> findByUserId(Long userId) {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        List<ParticipantEntity> participantEntityList = participantRepository.findByUserId(userId);

        for (ParticipantEntity participantEntity : participantEntityList) {
            roomEntityList.add(roomRepository.findById(participantEntity.getRoomId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "RoomEntity 조회 실패"))
            );
        }

        return roomEntityList;
    }

    public RoomEntity findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoomEntity 조회 실패"));
    }

    public RoomEntity update(RoomRequestDto.update req) {
        RoomEntity roomEntity = roomRepository.findById(req.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RoomEntity 조회 실패"));

        roomEntity.update(req.getName());

        return roomRepository.save(roomEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        participantRepository.deleteByRoomId(id);
        roomRepository.deleteById(id);
    }
}
