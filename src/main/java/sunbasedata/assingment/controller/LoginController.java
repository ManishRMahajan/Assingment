package sunbasedata.assingment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        // Check the username and password against the database or any authentication mechanism
        // For example:
        if (username.equals("testuser") && password.equals("testpassword")) {
            // Successful login, redirect to customer list page
            return "redirect:/customerList";
        } else {
            // Failed login, show login page again with an error message
            return "login";
        }
    }
}
