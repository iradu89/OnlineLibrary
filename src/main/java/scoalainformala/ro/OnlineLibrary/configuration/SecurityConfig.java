package scoalainformala.ro.OnlineLibrary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import scoalainformala.ro.OnlineLibrary.security.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //Admin
                .antMatchers("/users/list").hasAuthority("ADMIN")
//                .antMatchers("/users/showFormForAdd").hasAnyAuthority("ADMIN", "CLIENT") //probably has to be deleted
                //Book_keeper
                .antMatchers("/listBooks").hasAnyAuthority("ADMIN", "BOOK_KEEPER")
                .antMatchers("/showFormForAddBook").hasAnyAuthority("ADMIN", "BOOK_KEEPER")
                .antMatchers("/addBook").hasAnyAuthority("ADMIN", "BOOK_KEEPER")
                .antMatchers("/deleteBook").hasAnyAuthority("ADMIN", "BOOK_KEEPER")
                .antMatchers("/showFormForUpdateBook").hasAnyAuthority("ADMIN", "BOOK_KEEPER")
                //Client
                .antMatchers("/addReview").hasAnyAuthority("ADMIN", "CLIENT")
                .antMatchers("/buyBook").hasAnyAuthority("ADMIN", "CLIENT")
                .antMatchers("/confirmPurchase").hasAnyAuthority("ADMIN", "CLIENT")
                .antMatchers("/").permitAll()
                .and().formLogin()
                .loginPage("/login").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
