package com.zufar.requestsystem.uicontroller;

import com.zufar.requestsystem.dto.UserDTO;
import com.zufar.requestsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserUIController {

    private final UserService userService;

    @Autowired
    public UserUIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String home(ModelMap modelMap) {
        List<UserDTO> users = userService.getAll();
        modelMap.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/addUser")
    public String addUser(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("nickname") String nickname,
            @RequestParam("login") String login,
            @RequestParam("password") String password
    ) {
        UserDTO user = new UserDTO(null, firstName, lastName, nickname, login, password, null);
        userService.save(user);
        return "users";
    }

}