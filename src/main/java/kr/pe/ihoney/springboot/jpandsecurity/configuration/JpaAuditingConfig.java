package kr.pe.ihoney.springboot.jpandsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import kr.pe.ihoney.springboot.jpandsecurity.domain.system.User;
import kr.pe.ihoney.springboot.jpandsecurity.security.SpringSecurityAuditorAware;

/**
 * Entity 생성, 변경시 생성자(CreatdBy), 생성일(CreatedDate) 최근변경자(LastModifiedBy),
 * 최근변경일(LastModifiedDate) 정보에 대한 주입을 JPA에서 자동으로 처리하도록 설정
 * 
 * 생성자, 최근변경자 정보는 스프링시큐리티를 통해서 현재사용자({@link User}) 정보를 전달받도록
 * {@link AuditorAware} 인터페이스를 구현한 {@link SpringSecurityAuditorAware}를 사용한다.
 * 
 * @author honeymon
 *
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Bean
    SpringSecurityAuditorAware auditorAware() {
        return new SpringSecurityAuditorAware();
    }

}
