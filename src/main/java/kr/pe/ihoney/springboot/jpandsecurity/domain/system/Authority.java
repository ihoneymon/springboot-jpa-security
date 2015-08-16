package kr.pe.ihoney.springboot.jpandsecurity.domain.system;

import org.springframework.security.core.GrantedAuthority;

/**
 * 사용자 권한 설정
 * 
 * 스프링시큐리티의 권한은 대체적으로 사전에 권한을 정의하고, 이를 이용하여 웹에 접근을 정의하는 형식...
 * 
 * @author honeymo
 *
 */
public enum Authority implements GrantedAuthority {
    /**
     * 시스템관리자
     */
    ADMIN,
    /**
     * 프로젝트 관리자
     */
    PROJECT_MANAGER,
    /**
     * 기본적인 데이터 입출력권한을 가진 사용자에게 할당
     */
    USER;

    @Override
    public String getAuthority() {
        return this.toString();
    }

}
