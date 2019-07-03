package controller;

import dto.AuthenticationRequest;
import dto.RegistrationRequest;
import dto.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.Impl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<String> create(@RequestParam RegistrationRequest registrationRequest){
       long userId = userService.create(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered sucessfully for" + userId); //TODO: Send url instead
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestParam AuthenticationRequest authenticationRequest){
        try {
            userService.authenticate(authenticationRequest);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassWord(@Valid @RequestParam ResetPasswordRequest resetPasswordRequest){
        try{
            userService.resetPassword(resetPasswordRequest);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getLocalizedMessage());
        }
    }
}
