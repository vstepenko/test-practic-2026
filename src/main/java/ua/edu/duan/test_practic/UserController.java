package ua.edu.duan.test_practic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.edu.duan.test_practic.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestParam String login,
                           @RequestParam String name,
                           @RequestParam String email) {
        userService.addUser(login, name, email);
    }
}