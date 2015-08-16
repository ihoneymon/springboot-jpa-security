package kr.pe.ihoney.springboot.jpandsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;
import kr.pe.ihoney.springboot.jpandsecurity.repository.system.UserRepository;

@Component
public class ExtensibleUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.hasText(username, "security.exception.required.username");
        
        User user = findUser(username);
        return user;
    }

    private User findUser(String username) {
        User user = userRepository.findByUsername(username);
        if(null == user) {
            throw new UsernameNotFoundException("Not found username: " + username);
        }
        return user;
    }

}
