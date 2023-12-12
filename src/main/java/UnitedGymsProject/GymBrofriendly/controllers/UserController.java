package UnitedGymsProject.GymBrofriendly.controllers;

import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String orderBy) {
        return userService.getUsers(page, size, orderBy);
    }

    @GetMapping("/id")
    public User findById(@PathVariable long id){
        return userService.findById(id);
    }

    @GetMapping("/email")
    public User findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

    @PutMapping("/id")
    public User 
}
