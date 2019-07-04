package application.service;

import application.dto.AuthenticationRequest;
import application.dto.RegistrationRequest;
import application.dto.ResetPasswordRequest;

public interface UserService {
    long registration(RegistrationRequest registrationRequest);
    boolean authenticate(AuthenticationRequest authenticationRequest) throws Exception;
    void resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception;
}
