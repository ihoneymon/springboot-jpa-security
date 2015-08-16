package kr.pe.ihoney.springboot.jpandsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import kr.pe.ihoney.springboot.jpandsecurity.security.ExtensibleUserDetailsAuthenticationProvider;
import kr.pe.ihoney.springboot.jpandsecurity.security.ExtensibleUserDetailsService;


/**
 * 웹에 대한 접근설정을 목적으로 함
 * 
 * @author honeymon
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("/errors/**").permitAll()
            .antMatchers(HttpMethod.GET, "/sign-in", "/join").permitAll()
            .antMatchers(HttpMethod.GET, "/find/**").permitAll()
            .antMatchers(HttpMethod.POST, "/find/**").permitAll()
            .antMatchers(HttpMethod.POST, "/users").hasAnyAuthority("ADMIN")
            .antMatchers("/projects/**").hasAnyAuthority("PROJECT_MANAGER")
            .antMatchers("/posts/**").hasAnyAuthority("USER")
            .antMatchers("/h2console/**").hasAnyAuthority("ADMIN")
            .anyRequest().authenticated()
        .and()
            .headers()
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
        .and()
            .formLogin()
            /**
             * 로그인페이지는 인증되지 않은 사용자가 애플리케이션접근시 보게되는 화면
             * 원하는 template를 이용해서 구현하세요.  
             */
            .loginPage("/sign-in")
            .loginProcessingUrl("/system/sign-in")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/")
            .failureUrl("/sign-in?error=failure")
        .and()
            .logout()
            .logoutUrl("/system/sign-out")
            .logoutSuccessUrl("/")
        .and()
            .httpBasic()
        .and()
            .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new ExtensibleUserDetailsService();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        ExtensibleUserDetailsAuthenticationProvider authenticationProvider = new ExtensibleUserDetailsAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }
    
    @Bean
    public SaltSource saltSource() {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("username");
        return saltSource;
    }    
}