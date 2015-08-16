package kr.pe.ihoney.springboot.jpandsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;
import kr.pe.ihoney.springboot.jpandsecurity.repository.system.UserRepository;

@Service
@Transactional(readOnly = true)
public class DefaultUserService implements UserService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
        userRepository.flush();
    }
}
