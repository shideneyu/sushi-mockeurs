package fr.esgi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BusinessService businessService;

    public void say() {
        System.out.println("Hello from UserService");
    }

    public void sayThing() {
        businessService.say();
    }

}
