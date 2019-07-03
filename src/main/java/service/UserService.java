package service;

import dto.AuthenticationRequest;
import dto.RegistrationRequest;
import dto.ResetPasswordRequest;

public interface UserService {
    long create(RegistrationRequest registrationRequest);
    boolean authenticate(AuthenticationRequest authenticationRequest) throws Exception;
    void resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception;
}
