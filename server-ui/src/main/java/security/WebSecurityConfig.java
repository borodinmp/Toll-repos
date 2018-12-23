package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers( "/css", "/img", "/login").permitAll()
                .antMatchers("/", "/home", "/error").authenticated()
                .antMatchers("/routes/**", "/payments/**").hasAnyRole("CLIENT", "MANAGER","ROOT")
                .antMatchers("/registerClient/**").hasAnyRole("MANAGER","ROOT")
                .antMatchers("/registerManager/**").hasRole("ROOT")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("client").password("{noop}pas").roles("CLIENT")
                .and()
                .withUser("manager").password("{noop}pas").roles("MANAGER")
                .and()
                .withUser("admin").password("{noop}pas").roles("ROOT");
    }
}
