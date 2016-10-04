package ua.legalist.webapp;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.legalist.model.User;
import ua.legalist.service.UserService;
import ua.legalist.service.util.ComplianceMonitor;

@RestController
@RequestMapping("/api/users")
public class UsersREST {

    @Autowired
    UserService userService;

    @Autowired
    ComplianceMonitor complianceMonitor;

    @GetMapping("/{userId}")
    public User usersGet(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/")
    public void usersPost(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "password", required = true) String password,
            HttpServletRequest request) {
        if (complianceMonitor.isUserPreRegAbuse(request)) {
            //TODO: infrom user about abuse (maybe, using HTTP error code)
            return;
        }
        //TODO: REST API for "email busy" check with 
        userService.prepareNewUser(email, password);
    }

    @GetMapping("/email-confirmation")
    public void usersConfirmationGet(
            @RequestParam(name = "hash", required = true) String hash) {
        userService.confirmNewUser(hash);
        //TODO: than what?
    }
}
