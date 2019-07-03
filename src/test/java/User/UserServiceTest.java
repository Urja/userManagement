package User;

import dto.RegistrationRequest;
import dto.ResetPasswordRequest;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.UserRepository;
import service.Impl.UserServiceImpl;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public  void setUp(){
        User user = new User();
        user.setId(1L);
        user.setFullName("Urja Ramanandi");
        user.setEmail("sysadmin@test.com");
        user.setPassword("test");
        Mockito.when(userRepository.findByUserName(Matchers.anyString())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Matchers.any())).thenReturn(user);
    }

    @Test (expected = Exception.class)
    @Order(1)
    public void changePasswordWithInvalidUserName() throws Exception {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("invalidUser");
        resetPasswordRequest.setNewPassWord("test");
        resetPasswordRequest.setOldPassword("testOld");

        userService.resetPassword(resetPasswordRequest);
    }

    @Test
    @Order(2)
    public void create() {
        RegistrationRequest user = new RegistrationRequest();
        user.setFullName("Urja Ramanandi");
        user.setEmail("sysadmin@test.com");
        user.setPassword("test");

        userService.create(user);

        Assert.assertEquals(userService.findUserByUsername(user.getEmail()).get().getEmail(), "sysadmin@test.com");
    }

    @Test
    @Order(3)
    public void changePasswordWithvalidUserNamePassword() throws Exception {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("urja");
        resetPasswordRequest.setNewPassWord("testNew");
        resetPasswordRequest.setOldPassword("test");

        userService.resetPassword(resetPasswordRequest);
    }

    @Test (expected = Exception.class)
    @Order(4)
    public void changePasswordWithvalidOldPassword() throws  Exception{
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("urja");
        resetPasswordRequest.setNewPassWord("test");
        resetPasswordRequest.setOldPassword("invalid");

        userService.resetPassword(resetPasswordRequest);
    }
}
