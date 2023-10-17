package com.poogie.cns.room.api;

import com.poogie.cns.room.dao.RoomService;
import com.poogie.cns.room.domain.RoomEntity;
import com.poogie.cns.room.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomEntity> create(@RequestBody RoomRequestDto.create req) {
        return new ResponseEntity<>(roomService.add(req), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomEntity>> list(@RequestParam Long userId) {
        return new ResponseEntity<>(roomService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomEntity> detail(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RoomEntity> update(@RequestBody RoomRequestDto.update req) {
        return new ResponseEntity<>(roomService.update(req), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        roomService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
