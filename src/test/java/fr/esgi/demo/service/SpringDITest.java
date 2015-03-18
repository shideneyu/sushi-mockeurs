package fr.esgi.demo.service;

import fr.esgi.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class SpringDITest {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        businessService.sayHello();

        System.out.println("End");
    }

    @Test
    public void secondTest() {
        userService.sayThing();

        System.out.println("End");
    }
}
