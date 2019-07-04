package User;

import application.controller.UserController;
import application.service.Impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @Order(1)
    public void registration() throws Exception {
        String input = " {" +
                "\"email\" : \"registration@test.com\"," +
                "\"password\":\"regTest\"," +
                "\"fullName\" : \"Test User\"} ";

        when(userService.registration(Matchers.any())).thenReturn(1L);

        mockMvc.perform(post("/users").content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void resetPassword() throws Exception {
        String input = " {" +
                "\"email\" : \"registration@test.com\"," +
                "\"oldPassword\":\"reg\"," +
                "\"newPassword\" : \"regTest\"} ";

        doNothing().when(userService).resetPassword(Matchers.any());

        mockMvc.perform(put("/users/reset-password").content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void authentcationSucessfull() throws Exception {
        String input = " {" +
                "\"email\" : \"registration@test.com\"," +
                "\"password\":\"regTest\"} ";

        when(userService.authenticate(Matchers.any())).thenReturn(true);

        mockMvc.perform(post("/users/authenticate").content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void authentcationInvalidPassword() throws Exception {
        String input = " {" +
                "\"email\" : \"registration@test.com\"," +
                "\"password\":\"invalid\"} ";

        when(userService.authenticate(Matchers.any())).thenThrow(Exception.class);

        mockMvc.perform(post("/users/authenticate").content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void authentcationInvalidEmail() throws Exception {
        String input = " {" +
                "\"email\" : \"invalid@test.com\"," +
                "\"password\":\"regTest\"} ";

        when(userService.authenticate(Matchers.any())).thenThrow(Exception.class);

        mockMvc.perform(post("/users/authenticate").content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
}
