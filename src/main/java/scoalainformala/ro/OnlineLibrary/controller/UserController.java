package scoalainformala.ro.OnlineLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;
import scoalainformala.ro.OnlineLibrary.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {

        List<UserEditDto> userList = userService.getAll();
        model.addAttribute("users", userList);
        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        UserInsertDto userInsertDto = new UserInsertDto();
        model.addAttribute("userInsertDto", userInsertDto);

        return "users/user-form";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("email") String email, Model model) {

        UserEditDto userEditDto = userService.showUser(email);
        model.addAttribute("userEditDto", userEditDto);
        return "users/update-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("userInsertDto") UserInsertDto userInsertDto) {
        System.out.println(userInsertDto);
        userService.saveNewUser(userInsertDto);

        return "redirect:/users/list";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userEditDto") UserEditDto userEditDto) {
        System.out.println(userEditDto);
        userService.saveUserEdit(userEditDto);

        return "redirect:/users/list";
    }

    @GetMapping("/inactivate")
    public String inactivateUser(@RequestParam("email") String email, Model model) {
        //TODO userService.inactivateUser();
        return "redirect:/users/list";
    }
}
