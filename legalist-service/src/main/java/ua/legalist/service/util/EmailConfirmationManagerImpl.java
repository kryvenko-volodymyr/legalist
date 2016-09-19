package ua.legalist.service.util;

import ua.legalist.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service("emailConfirmationManager")
public class EmailConfirmationManagerImpl implements EmailConfirmationManager {

    private MessageDigest messageDigester;

    {
        try {
            messageDigester = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EmailConfirmationManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String generateEmailConfirmationHash(String email, String password) {
        String stringToEncrypt = email + password;
        messageDigester.update(stringToEncrypt.getBytes());
        return new String(messageDigester.digest());
    }

    @Override
    public void sendEmailConfirmationRequest(User user) {
        // TODO
    }
}
