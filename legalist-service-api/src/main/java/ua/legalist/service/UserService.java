package ua.legalist.service;

import ua.legalist.model.User;

public interface UserService {

    public User getUserById(int userId);

    public void prepareNewUser(String username, String password);

    public void confirmNewUser(String hash);

}
