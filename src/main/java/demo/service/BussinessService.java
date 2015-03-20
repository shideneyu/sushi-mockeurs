package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Benoit on 17/03/2015.
 */

@Service
public class BussinessService {

    @Autowired
    private UserService userService;
    public void sayHello(){

        userService.say();
    }

    public void sayThing() {
        System.out.printf("Hello from Bussiness");
    }
}
