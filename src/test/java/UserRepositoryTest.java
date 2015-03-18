import fr.esgi.demo.DemoApplication;
import fr.esgi.demo.domain.Role;
import fr.esgi.demo.domain.User;
import fr.esgi.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static fr.esgi.demo.domain.Role.ADMIN;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_SaveUser_AndGenerateId() {
        //given
        User user = new User();
        String login = "login";
        String password = "1234";
        Role role = ADMIN;

        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);

        //when
        User savedUser = userRepository.save(user);

        //then
        assertThat(savedUser, notNullValue());
        assertThat(savedUser.getId(), notNullValue());
        assertThat(savedUser.getLogin(), is(login));
        assertThat(savedUser.getPassword(), is(password));
        assertThat(savedUser.getRole(), is(role));
    }
}
