package ua.legalist.webapp.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)

//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig {

    
    @Autowired
    @Qualifier("securityDataSource")
    private DataSource dataSource;

    //@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").authorities("ROLE_USER").and()
//                .withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, true "
                        + "from user where email = ?")
                .authoritiesByUsernameQuery("select u.email, r.title "
                        + "from user u, role r, user_role ur "
                        + "where u.email = ? "
                        + "and u.id = ur.user "
                        + "and r.id = ur.role") //.passwordEncoder(new StandardPasswordEncoder("53cr3t"))
                ;
    }

    //@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().anyRequest().permitAll();
//                http.formLogin()
//                .loginPage("/login")
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds(3600)
//                .key("legalistKey") 
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/index")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //for CSRF. uses GET - not recommended
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/users/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//                .anyRequest().permitAll();
    }
}
