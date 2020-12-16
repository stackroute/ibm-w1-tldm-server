package com.stackroute.tldm.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.tldm.controller.UserController;

import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.UserService;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private User user;
    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
       // user = new User(, null, null, null, null);
        user = new User();
        user.setUserId("swedha12");
        user.setPhoneNum("56528769987");
        user.setUserName("swetha");
        user.setUserMail("swedha87@gmail.com");
        user.setPassword("123456");
        user.setCreatedAt(new Date());
    }


    @Test
    public void deleteUserSuccess() throws Exception {
        when(userService.deleteUser("swedha12")).thenReturn(true);
        mockMvc.perform(delete("/api/v1/user/swedha12").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserFailure() throws Exception {
        when(userService.deleteUser("swedha12")).thenThrow(UserNotFoundException.class);
        mockMvc.perform(delete("/api/v1/user/swedha12").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateUserSuccess() throws Exception {
        user.setUserMail("swedha87@gmail.com");
        when(userService.updateUser(eq(user.getUserId()), any())).thenReturn(user);
        mockMvc.perform(put("/api/v1/user/swedha12")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserFailure() throws Exception {
        user.setUserMail("swedha87@gmail.com");
        when(userService.updateUser(eq(user.getUserId()), any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(put("/api/v1/user/swedha12")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getByUserIdSuccess() throws Exception {
        when(userService.getUserById("swedha12")).thenReturn(user);
        mockMvc.perform(get("/api/v1/user/swedha12")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getByUserIdFailure() throws Exception {
        when(userService.getUserById("swedha12")).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/api/v1/user/swedha12")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}