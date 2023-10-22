package com.poogie.cns.security;

import com.poogie.cns.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtDto {
    private String accessJwt;
    private UserEntity user;
}