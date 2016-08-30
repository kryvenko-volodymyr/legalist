package ua.legalist.webapp.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("securityDataSource")
    private DataSource dataSource;
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").authorities("ROLE_USER").and()
//                .withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
        
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, true " +
                        "from user where email = ?")
                .authoritiesByUsernameQuery("select u.email, r.title " +
                        "from user u, role r, user_role ur " +
                        "where u.email = ? " +
                        "and u.id = ur.user " +
                        "and r.id = ur.role");
    }
}
