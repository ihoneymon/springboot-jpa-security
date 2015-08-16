package kr.pe.ihoney.springboot.jpandsecurity.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;

/**
 * 스프링시큐리티에서 인증된 사용자정보를 가져온다.
 * 
 * @author honeymon
 *
 */
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
            return null;
        }
        return (User) authentication.getPrincipal();
    }

}
