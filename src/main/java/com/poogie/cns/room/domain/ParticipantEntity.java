package com.poogie.cns.room.domain;

import com.poogie.cns.common.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="participant")
public class ParticipantEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private Long userId;

    @Builder
    public ParticipantEntity(Long id, Long roomId, Long userId,
                      LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
    }
}
