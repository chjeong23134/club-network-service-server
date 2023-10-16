package com.poogie.cns.user.api;

import com.poogie.cns.security.JwtDto;
import com.poogie.cns.user.dao.AuthService;
import com.poogie.cns.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody UserRequestDto.SignIn req) {
        return new ResponseEntity<>(authService.signIn(req) , HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signUp(@RequestBody UserRequestDto.SignUp req) {
        authService.signUp(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
