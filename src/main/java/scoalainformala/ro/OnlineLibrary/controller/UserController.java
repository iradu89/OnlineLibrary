package scoalainformala.ro.OnlineLibrary.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;
import scoalainformala.ro.OnlineLibrary.exceptions.InvalidUserException;
import scoalainformala.ro.OnlineLibrary.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashboard() {

        return "users/user-welcome";
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

    public String saveUser(@Valid @ModelAttribute("userInsertDto") UserInsertDto userInsertDto,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userInsertDto", userInsertDto);
            return "users/user-form";
        }
        log.info(userInsertDto);
        try {
            userService.saveNewUser(userInsertDto);
        } catch (InvalidUserException ex) {
            log.info("User " + userInsertDto.getEmail() + " is already in database.");
            model.addAttribute("email", userInsertDto.getEmail());
            return "users/register-error";
        }
        model.addAttribute("email", userInsertDto.getEmail());
        return "users/register-success";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("userEditDto") UserEditDto userEditDto,
                             BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("userEditDto", userEditDto);
            return "users/update-form";
        }
        userService.saveUserEdit(userEditDto);
        model.addAttribute("email", userEditDto.getEmail());
        return "users/register-success";
    }

    @GetMapping("/inactivate")
    public String inactivateUser(@RequestParam("email") String email, Model model) {

        userService.inactivateUser(email);
        return "redirect:/users/list";
    }
}
