package com.poogie.cns.user.domain;

import com.poogie.cns.common.AutoDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="user")
public class UserEntity extends AutoDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String isDeleted;

    @Builder
    public UserEntity(Long id, String email, String password, String name,
                      LocalDateTime createDate, LocalDateTime updateDate) {
        super(createDate, updateDate);

        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.isDeleted = "N";
    }
}