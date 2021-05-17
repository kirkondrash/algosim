package hse.algosim.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] ANONYMOUS_AUTH_LIST = {
            // -- swagger ui
            "/",
            "/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            // -- envoy routing
            "/repo/**",
            "/compiler/**",
            "/executor/**",
            "/api/ready",
    };

    private static final String[] USER_AUTH_LIST = {
            "/algoCode",
            "/getTop",
            "/recommendation",
    };

    private static final String[] ADMIN_AUTH_LIST = {
            "/api/**"
    };

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ANONYMOUS_AUTH_LIST).permitAll()
                .antMatchers(USER_AUTH_LIST).hasAnyAuthority("user","admin")
                .antMatchers(ADMIN_AUTH_LIST).hasAuthority("admin")
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login, password, 1 from bhacklogins where login=?")
                .authoritiesByUsernameQuery("select login, permit from bhacklogins where login=?");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }
}
