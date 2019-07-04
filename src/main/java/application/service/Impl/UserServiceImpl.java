package application.service.Impl;

import application.dto.AuthenticationRequest;
import application.dto.RegistrationRequest;
import application.dto.ResetPasswordRequest;
import application.entity.User;
import application.repository.UserRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public long registration(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFullName(registrationRequest.getFullName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        return userRepository.save(user).getId();
    }

    @Override
    public boolean authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        return isValidUser(authenticationRequest);
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                resetPasswordRequest.getEmail()
                , resetPasswordRequest.getOldPassword());

        if (isValidUser(authenticationRequest)) {
            User user = findUserByEmail(resetPasswordRequest.getEmail()).get();
            user.setPassword(resetPasswordRequest.getNewPassword());
            userRepository.save(user);
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private boolean isValidUser(AuthenticationRequest authenticationRequest) throws Exception {
        Optional<User> user = findUserByEmail(authenticationRequest.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(authenticationRequest.getPassword())) {
            return true;
        }
        throw new Exception("Invalid username or password.");
    }
}
