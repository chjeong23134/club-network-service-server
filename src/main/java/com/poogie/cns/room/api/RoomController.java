package com.poogie.cns.room.api;

import com.poogie.cns.room.dao.RoomService;
import com.poogie.cns.room.domain.RoomEntity;
import com.poogie.cns.room.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomEntity> create(@RequestBody RoomRequestDto.create req) {
        return new ResponseEntity<>(roomService.add(req), HttpStatus.OK);
    }
}
