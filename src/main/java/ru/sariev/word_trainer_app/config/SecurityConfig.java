package ru.sariev.word_trainer_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/train/main", "/train/allwords", "/train/trainer", "/train/next", "/train/{id}/showTranslation")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/train/{id}/edit", "/train/{id}", "/train/{id}/editWordOnTraining", "/train/{id}/updateWordOnTraining")
                .hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/train/main")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("user").password("user").roles("USER"))
                .withUser(userBuilder.username("admin").password("admin").roles("ADMIN"));
    }
}
