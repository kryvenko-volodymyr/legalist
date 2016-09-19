package ua.legalist.service.util;

import ua.legalist.model.User;

public interface EmailConfirmationManager {

    public String generateEmailConfirmationHash(String email, String password);

    public void sendEmailConfirmationRequest(User user);
    
}
