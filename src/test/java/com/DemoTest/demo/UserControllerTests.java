package com.DemoTest.demo;

import com.DemoTest.demo.controller.UserController;
import com.DemoTest.demo.exception.RequiredFieldValidationException;
import com.DemoTest.demo.model.User;
import com.DemoTest.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flextrade.jfixture.JFixture;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    private static final JFixture fixture = new JFixture();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final String URL = "/api/users";

    @Test
    public void UserController_should_create_should_throw_the_RequiredFieldValidationException() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        User testUser = new User();

        String testUserJsonString = objectMapper.writeValueAsString(testUser);

        when(userService.createUser(any(User.class))).thenReturn(testUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URL).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(testUserJsonString)).
                andReturn();

        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), status);

        ApiErrorResponse actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ApiErrorResponse.class);

        Assert.assertNotNull(actual);
    }

    @Test
    public void UserController_should_create_the_user() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        User testUser = fixture.create(User.class);

        String testUserJsonString = objectMapper.writeValueAsString(testUser);

        when(userService.createUser(any(User.class))).thenReturn(testUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URL).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(testUserJsonString)).
                andReturn();

        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(HttpStatus.CREATED.value(), status);

        verify(userService).createUser(any(User.class));

        User actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);

        Assert.assertNotNull(actual);
        Assert.assertEquals(testUser, actual);
    }

    @Test
    public void UserController_should_find_the_user_by_userId() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        User testUser = fixture.create(User.class);

        when(userService.findUser(any(Integer.class))).thenReturn(testUser);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(URL + "?userId=" + testUser.getUserId()).
               contentType(MediaType.APPLICATION_JSON)).
                andReturn();

        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(HttpStatus.FOUND.value(), status);

        verify(userService).findUser(any(Integer.class));

        User actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);

        Assert.assertNotNull(actual);
        Assert.assertEquals(testUser, actual);
    }
}
