package web.config.security.securityconfig;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.security.handler.SecuritySuccessHandlerConfig;
import web.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;



    @Autowired
    public SecurityConfig (UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
//        http.csrf().disable();
//                http
//                        .authorizeRequests().antMatchers("/login").anonymous()
//                        .antMatchers("/admin").hasAnyAuthority("ADMIN")
//                        .antMatchers("/user").hasAnyAuthority("USER")
//                        .anyRequest().authenticated()
//                        .and()
//                        .formLogin().successHandler(new SecuritySuccessHandlerConfig())
//                        .and()
//                        .logout().permitAll()
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/logout")
//                        .and()
//                        .csrf().disable();
        http.authorizeRequests().antMatchers("/login").anonymous()
            .antMatchers("/admin").hasAuthority("ADMIN")
            .antMatchers("/user").hasAnyAuthority("ADMIN", "USER")
            .anyRequest().authenticated()
            .and()
            .formLogin().successHandler(new SecuritySuccessHandlerConfig());
        http.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login").and().csrf().disable();


    }
}
