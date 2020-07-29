package scoalainformala.ro.OnlineLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import scoalainformala.ro.OnlineLibrary.domain.Address;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/list")
    public String listUsers(Model model) {

        UserEditDto user1 = new UserEditDto("Jim Beam", "jim@beam.com", "password1", new Address("Boom", 15, "Baltimore", "USA", 90210));
        UserEditDto user2 = new UserEditDto("Johnnie Walker", "john@beam.com", "password2", new Address("Zoom", 12, "Baltimore", "USA", 90210));

        List<UserEditDto> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        model.addAttribute("users", userList);
        return "list-users";

    }

}
