package application.controller;

import application.dto.AuthenticationRequest;
import application.dto.RegistrationRequest;
import application.dto.ResetPasswordRequest;
import application.service.Impl.UserServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Registered successfully for ${userId}")
    })
    @PostMapping
    public ResponseEntity<String> registration(@RequestBody RegistrationRequest registrationRequest) {
        long userId = userService.registration(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered successfully for" + userId); //TODO: Send url instead
    }


    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            userService.authenticate(authenticationRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassWord(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            userService.resetPassword(resetPasswordRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getLocalizedMessage());
        }
    }
}
