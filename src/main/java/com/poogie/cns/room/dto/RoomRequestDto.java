package com.poogie.cns.room.dto;

import com.poogie.cns.room.domain.RoomEntity;
import lombok.Getter;

public class RoomRequestDto {
    @Getter
    public static class create {
        private String name;
        private Long createBy;

        public RoomEntity toEntity() {
            return RoomEntity.builder()
                    .name(this.name)
                    .createBy(this.createBy)
                    .build();
        }
    }
}
