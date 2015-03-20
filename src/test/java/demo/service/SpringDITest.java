package demo.service;

import demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.OneToOne;

/**
 * Created by Benoit on 17/03/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class SpringDITest {

    @Autowired
    private BussinessService bussinessService;
    @Autowired
    private UserService userService;


    @Test
    public void secondTest(){

        userService.sayThing();
    }

    @Test
    public void test(){
        bussinessService.sayHello();

        System.out.println("End");

    }
}
