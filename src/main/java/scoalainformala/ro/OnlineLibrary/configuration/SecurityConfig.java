package scoalainformala.ro.OnlineLibrary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static scoalainformala.ro.OnlineLibrary.domain.Role.ADMIN;
import static scoalainformala.ro.OnlineLibrary.domain.Role.CLIENT;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "book", "/users/showFormForAdd").permitAll()
                .antMatchers("/users/list").hasRole(ADMIN.name())
                .antMatchers("/users/showUpdateForm").hasAnyRole(ADMIN.name(), CLIENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/book", true);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails clientUser = User.builder()
                .username("ClientTest")
                .password(passwordEncoder.encode("password"))
                .roles(CLIENT.name())
                .build();

        UserDetails adminUser = User.builder()
                .username("AdminTest")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                clientUser,
                adminUser
        );
    }
}