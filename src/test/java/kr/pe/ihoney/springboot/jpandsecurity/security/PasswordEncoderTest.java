package kr.pe.ihoney.springboot.jpandsecurity.security;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.pe.ihoney.springboot.jpandsecurity.SpringbootJpaSecurityApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootJpaSecurityApplication.class)
@WebAppConfiguration
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Test
    public void 비밀번호인코딩테스트() {
        String password = "testPassword";
        String encodedPassword = passwordEncoder.encode(password);
        assertTrue(passwordEncoder.matches(password, encodedPassword));
    }
}
