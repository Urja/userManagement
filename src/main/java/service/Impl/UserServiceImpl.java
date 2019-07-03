package service.Impl;

import dto.AuthenticationRequest;
import dto.RegistrationRequest;
import dto.ResetPasswordRequest;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public long create(RegistrationRequest registrationRequest) {
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

        if(isValidUser(authenticationRequest)) {
            User user = findUserByUsername(resetPasswordRequest.getEmail()).get();
            user.setPassword(resetPasswordRequest.getNewPassWord());
            userRepository.save(user);
        }
    }

    public Optional<User> findUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    private boolean isValidUser(AuthenticationRequest authenticationRequest) throws Exception {
        Optional<User> user = findUserByUsername(authenticationRequest.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(authenticationRequest.getPassword())) {
            return true;
        }
        throw new Exception("Invalid username or password.");
    }
}
