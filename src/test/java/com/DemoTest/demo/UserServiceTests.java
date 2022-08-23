package com.DemoTest.demo;

import com.DemoTest.demo.model.User;
import com.DemoTest.demo.repository.UserRepository;
import com.DemoTest.demo.service.UserServiceImpl;
import com.flextrade.jfixture.JFixture;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTests {

    private static final JFixture fixture = new JFixture();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void UserService_add_the_user_test() {
        User testUser = fixture.create(User.class);
        when(userRepository.createUser(testUser)).thenAnswer(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                //get the arguments passed to mock
                Object[] args = invocation.getArguments();

                //get the mock
                Object mock = invocation.getMock();
                verify(userRepository).createUser(any(testUser.getClass()));
                //return the result
                return (User) args[0];
            }
        });


        //test the add functionality
        User actual = userService.createUser(testUser);
        verify(userRepository).createUser(any(testUser.getClass()));
        Assert.assertEquals(actual.getUserId(), testUser.getUserId());

    }


    @Test
    public void UserService_add_the_user() {
        User testUser = fixture.create(User.class);
        when(userRepository.createUser(any(User.class))).thenReturn(testUser);

        User actual = userService.createUser(testUser);

        verify(userRepository, times(1)).createUser(any(User.class));
        verify(userRepository).createUser(argThat(someString -> someString.getUserId() == testUser.getUserId()));

        Assert.assertNotNull(actual);
        Assert.assertEquals(testUser, actual);

    }

    @Test
    public void UserService_should_find_the_User_by_userUd() {

        User testUser = fixture.create(User.class);
        when(userRepository.findUser(any(Integer.class))).thenReturn(testUser);


        User actual = userService.findUser(testUser.getUserId());

        verify(userRepository).findUser(any(Integer.class));
        Assert.assertNotNull(actual);
        Assert.assertEquals(testUser, actual);

    }

//    @ParameterizedTest
//    @ValueSource(ints = {1, 2, 2})
//    public void UserService_should_find_the_User_by_userUd(int number) {
//        System.out.println(number);
//
//    }
}
