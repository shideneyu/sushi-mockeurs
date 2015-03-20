package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Benoit on 17/03/2015.
 */

@Service
public class UserService {
    @Autowired
    private BussinessService bussinessService;

    public void say() {
        System.out.println("Hello from User");
    }

    public void sayThing() {
        //bussinessService.sayHello();
        bussinessService.sayThing();

    }
}
