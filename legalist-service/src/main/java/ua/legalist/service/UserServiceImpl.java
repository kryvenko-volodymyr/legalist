package ua.legalist.service;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.legalist.model.User;
import ua.legalist.persistence.UserDao;
import ua.legalist.service.util.EmailConfirmationManager;
import ua.legalist.service.util.ComplianceMonitor;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    EmailConfirmationManager emailConfirmationManager;

    @Autowired
    ComplianceMonitor complianceMonitor;

    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(int userId) {
        return userDao.read(userId);
    }

    @Override
    public void prepareNewUser(String email, String password) {
        if (complianceMonitor.isProperEmail(email) // TODO: REST API
                && complianceMonitor.isProperPassword(password)) // TODO: REST API
        {
            User newUser = preCreateUser(email, password);
            requestEmailCofirmatin(newUser);
        } else {
            throw new IllegalArgumentException("Improper email or password");
        }
    }

    @Override
    public void confirmNewUser(String hash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    private User preCreateUser(String email, String password) { //TODO exception if email busy
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setEmailConfirmationHash(
                emailConfirmationManager.
                generateEmailConfirmationHash(email, password));
        newUser.setDateCreated(new Date()); //now
        return userDao.create(newUser);
    }

    private void requestEmailCofirmatin(User user) {
        emailConfirmationManager.sendEmailConfirmationRequest(user);
    }

    //TODO
    @Override
    public User getUserByEmail(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
