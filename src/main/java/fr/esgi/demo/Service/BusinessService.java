package fr.esgi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private UserService userService;

    public void sayHello() {
        userService.say();
    }

    public void say() {
        System.out.println("Hello from BusinessService");
    }
}
