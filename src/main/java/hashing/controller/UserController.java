package hashing.controller;

import hashing.model.RegistrationRequest;
import hashing.model.Users;
import hashing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public String login(@RequestBody RegistrationRequest requestParam){
        return userService.login(requestParam);
    }

    @PostMapping("registr")
    public String registr(@RequestBody RegistrationRequest requestParam){
        return userService.registration(requestParam);
    }

    @GetMapping("get")
    public Users getUsers(@RequestParam String email){
        return userService.getUser(email);
    }
}
