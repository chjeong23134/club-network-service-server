package com.poogie.cns.room.domain;

import com.poogie.cns.common.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name="room")
public class RoomEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long createBy;

    public void update(String name) {
        if(!Objects.equals(this.name, name) && name != null) {
            this.name = name;
        }
    }

    @Builder
    public RoomEntity(Long id, String name, Long createBy,
                      LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.name = name;
        this.createBy = createBy;
    }
}