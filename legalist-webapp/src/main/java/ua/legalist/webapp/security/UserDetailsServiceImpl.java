package ua.legalist.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.legalist.service.UserService;

/*
* This class is used to decouple org.springframework.security.core.userdetails.UserDetailsService
* and ua.legalist.service.UserService
*/

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserByEmail(username);
    }

}
