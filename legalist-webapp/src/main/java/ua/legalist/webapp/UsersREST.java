package ua.legalist.webapp;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public User userGet(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("")
    public ResponseEntity<Void> usersPost(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "password", required = true) String password,
            HttpServletRequest request) {
        if (complianceMonitor.isUserPreRegAbuse(request)) {
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        } 
        //TODO: REST API for "email busy" check
        userService.prepareNewUser(email, password);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Accessed through link in Please-confirm-your-email letter
     * during user registration.
     * @param hash 
     */
    @GetMapping("/email-confirmation")
    public void usersConfirmationGet(
            @RequestParam(name = "hash", required = true) String hash) {
        userService.confirmNewUser(hash);
        //TODO: than what?
    }
}
