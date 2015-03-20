package demo.repository;

import demo.DemoApplication;
import demo.domain.Role;
import demo.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static demo.domain.Role.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Benoit on 18/03/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class UserRepositoryTest {
/*
    @Autowired
    private  UserRepository userRepository;



    @Before
    public void setUp() {

   }

    @Test
    public void should_SaveUser_AndGenerateId(){
        //Given

        String login = "login";
        String password = "1234";
        Role role = ADMIN;

        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setRole(role);

        //When
        User savedUser = userRepository.save(user);



        //Then
        assertThat(savedUser, notNullValue());
        assertThat(savedUser.getId(),notNullValue());
        assertThat(savedUser.getLogin(), is(login));

    }
    @Test
    public void should_FindById_SavedUser(){
        //Given
        String login = "login";
        String password = "1234";
        Role role = ADMIN;

        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setRole(role);

        User savedUser = userRepository.save(user);

        //When
        User retrivedUser = userRepository.findOne(savedUser.getId());

        //Then
        assertThat(savedUser, notNullValue());
        assertThat(retrivedUser.getId(),is(savedUser.getId()));


    }
    @Test
    public void should_FindByLogin_SavedUser(){
        //Given
        String login = "login";
        String password = "1234";
        Role role = ADMIN;

        User user = new User();
        user.setPassword(password);
        user.setLogin(login);
        user.setRole(role);

        User savedUser = userRepository.save(user);
        //when
        User retrivedUser = userRepository.findByRole(savedUser.getRole());

        assertThat(retrivedUser.getId(),is(savedUser.getId()));
    }
*/
}
