package fr.esgi.demo;

import fr.esgi.demo.domain.UserWithBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserWithBuilderTest {

    @Test
    public void test() {

        long id = 1;
        String login = "login";
        String name = "name";
        String password = "";

        UserWithBuilder user = UserWithBuilder.newUser(id, login) //non optional parameters
                .withName(name)
                .withPassword(password)
                .build();

        assertThat(user, notNullValue());
        assertThat(user.getId(), is(id));
        assertThat(user.getLogin(), is(login));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
