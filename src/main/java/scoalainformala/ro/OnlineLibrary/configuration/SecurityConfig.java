package scoalainformala.ro.OnlineLibrary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import scoalainformala.ro.OnlineLibrary.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Lazy MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //Admin
                .antMatchers("/users/list").hasAuthority("ADMIN")
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
                .antMatchers("/users/showFormForAdd").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
